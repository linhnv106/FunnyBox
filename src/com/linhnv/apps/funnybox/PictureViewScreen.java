package com.linhnv.apps.funnybox;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.adapter.PictureViewAdapter;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.utils.ImageCache;
import com.linhnv.apps.funnybox.utils.ImageCache.ImageCacheParams;
import com.linhnv.apps.funnybox.utils.ImageFetcher;

public class PictureViewScreen extends SherlockFragmentActivity {
	private static final String IMAGE_CACHE_DIR="images";
	private static final String EXTRA_IMAGE="extra_image";
	
	private ImageFetcher mImageFetcher;
	private ViewPager mViewPager;
	private Context mContext;
	private TextView mTextView;
	private PictureViewAdapter mAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.picture_view);
		mContext=this;
		mViewPager=(ViewPager) findViewById(R.id.picture_viewpager);
		mTextView=(TextView)findViewById(R.id.picture_view_title);
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int height = displayMetrics.heightPixels;
		final int width = displayMetrics.widthPixels;
		
		final int longest=(height>width?height:width)/2;
		ImageCache.ImageCacheParams cacheParams= new ImageCacheParams(this,IMAGE_CACHE_DIR);		
		cacheParams.setMemCacheSizePercent(0.25f);
		mImageFetcher=new ImageFetcher(mContext, longest);
		mImageFetcher.addImageCache(getSupportFragmentManager(), cacheParams);
		mImageFetcher.setImageFadeIn(false);
		final List<PictureEntry> objects = new ArrayList<PictureEntry>();
		for(int i =0 ;i <Images.funnyImageUrls.length;i++){
			PictureEntry entry = new PictureEntry(i, "image " + i , Images.funnyImageUrls[i]);
			objects.add(entry);
		}
		mTextView.setText("1/"+ objects.size());
		mAdapter = new PictureViewAdapter(this, 0,objects, mImageFetcher);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(final int arg0) {
				runOnUiThread( new Runnable() {
					
					@Override
					public void run() {
						mTextView.setText(""+(arg0+1)+"/"+objects.size());
						
					}
				});
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
	}
	
}

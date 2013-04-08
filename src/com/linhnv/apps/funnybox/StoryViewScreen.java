package com.linhnv.apps.funnybox;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.utils.ImageCache;
import com.linhnv.apps.funnybox.utils.ImageFetcher;
import com.linhnv.apps.funnybox.utils.ImageCache.ImageCacheParams;

public class StoryViewScreen extends SherlockFragmentActivity{
	private static final String IMAGE_CACHE_DIR="images";
	private static final String EXTRA_IMAGE="extra_image";	
	private ImageFetcher mImageFetcher;
	private Context mContext;
	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mContext=this;
		setContentView(R.layout.story_view_layout);
		mImageView=(ImageView)findViewById(R.id.story_cover);
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
		mImageFetcher.loadImage(Images.imageUrls[2], mImageView);
	}

}

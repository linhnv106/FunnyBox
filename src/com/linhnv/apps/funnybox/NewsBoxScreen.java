package com.linhnv.apps.funnybox;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.model.NewsEntry;
import com.linhnv.apps.funnybox.utils.ImageCache.ImageCacheParams;
import com.linhnv.apps.funnybox.utils.ImageFetcher;

public class NewsBoxScreen extends SherlockFragmentActivity{
	private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;
    private Context mContext;
    private ViewPager mHotNews;
    private ViewPager mEconomy;
    private ViewPager mEntertaiment;
    private ViewPager mSport;
    private TopNewsPagerAdapter mHotNewsAdapter;
    private TopNewsPagerAdapter mEconomyAdapter;
    private TopNewsPagerAdapter mEntertaimentAdapter;
    private TopNewsPagerAdapter mSportAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext=this;
		setContentView(R.layout.breaking_news_layout);
		mImageThumbSize = mContext.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = mContext.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        ImageCacheParams cacheParams = new ImageCacheParams(this,IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f);
        mImageFetcher = new ImageFetcher(this, mImageThumbSize);
//        mImageWorker.setAdapter(Images.imageWorkerUrlsAdapter);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(this.getSupportFragmentManager(), cacheParams);
        mHotNews=(ViewPager)findViewById(R.id.top_news_pager);
        mEconomy=(ViewPager)findViewById(R.id.top_economy_pager);
        mEntertaiment=(ViewPager)findViewById(R.id.top_enter_pager);
        mSport=(ViewPager)findViewById(R.id.top_sport_pager);
        
        ArrayList<NewsEntry> objects = new ArrayList<NewsEntry>();
		for(int i =0 ;i <Images.imageUrls.length;i++){
			NewsEntry entry = new NewsEntry(i, "News " + i , Images.imageUrls[i],"");
			objects.add(entry);
		}
		mHotNewsAdapter= new TopNewsPagerAdapter(getSupportFragmentManager(), objects);
		mEconomyAdapter= new TopNewsPagerAdapter(getSupportFragmentManager(), objects);
		mEntertaimentAdapter= new TopNewsPagerAdapter(getSupportFragmentManager(), objects);
		mSportAdapter= new TopNewsPagerAdapter(getSupportFragmentManager(), objects);
        
		mHotNews.setPageMargin(20);
		mEconomy.setPageMargin(20);
		mEntertaiment.setPageMargin(20);
		mSport.setPageMargin(20);
		
		
		mHotNews.setAdapter(mHotNewsAdapter);
		mEconomy.setAdapter(mEconomyAdapter);
		mEntertaiment.setAdapter(mEntertaimentAdapter);
		mSport.setAdapter(mSportAdapter);
        
	}
	class TopNewsPagerAdapter extends FragmentPagerAdapter {
		private List<NewsEntry> mData;
		
		public TopNewsPagerAdapter( FragmentManager fm,ArrayList<NewsEntry> data) {
			super(fm);
			mData= new ArrayList<NewsEntry>();
			mData.addAll(data);
			Log.i("Linhnv", "TopPicPagerAdapter size :" + mData.size());
		}
		
		@Override
		public Fragment getItem(int arg0) {			
			return new TopNewsFragment(mData.get(arg0));
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public float getPageWidth(int position) {
			
			return 0.4f;
		}
		
		
	}
	class TopNewsFragment extends Fragment{
		private ImageView imageView;
		private TextView textView;
		private NewsEntry entry;
		public TopNewsFragment(NewsEntry entry) {
			super();
			this.entry=entry;
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.picture_box_entry,container,false);
			imageView=(ImageView)v.findViewById(R.id.box_image);
			textView=(TextView)v.findViewById(R.id.box_title);
		SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener(){

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				Log.i("Linhnv", "onSingleTap");
				Intent intent = new Intent(getActivity(), StoryViewScreen.class);
				startActivity(intent);
				return super.onSingleTapUp(e);
			}

			@Override
			public void onLongPress(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				super.onLongPress(e);
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				if(Math.abs(e1.getY()-e2.getY())>=20){
					mHotNews.getParent().requestDisallowInterceptTouchEvent(false);
					mEconomy.getParent().requestDisallowInterceptTouchEvent(false);
					mEntertaiment.getParent().requestDisallowInterceptTouchEvent(false);
					mSport.getParent().requestDisallowInterceptTouchEvent(false);
					return super.onScroll(e1, e2, distanceX, distanceY);
				}
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onScroll(e1, e2, distanceX, distanceY);
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				if(Math.abs(e1.getY()-e2.getY())>=20){
					mHotNews.getParent().requestDisallowInterceptTouchEvent(false);
					mEconomy.getParent().requestDisallowInterceptTouchEvent(false);
					mEntertaiment.getParent().requestDisallowInterceptTouchEvent(false);
					mSport.getParent().requestDisallowInterceptTouchEvent(false);
					return super.onFling(e1, e2, velocityX, velocityY);
				}
				
				
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onFling(e1, e2, velocityX, velocityY);
				
			}

			@Override
			public void onShowPress(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				super.onShowPress(e);
			}

			@Override
			public boolean onDown(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return true;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onDoubleTap(e);
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onDoubleTapEvent(e);
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				mHotNews.getParent().requestDisallowInterceptTouchEvent(true);
				mEconomy.getParent().requestDisallowInterceptTouchEvent(true);
				mEntertaiment.getParent().requestDisallowInterceptTouchEvent(true);
				mSport.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onSingleTapConfirmed(e);
			}
			
		};
			
		final GestureDetector detector = new GestureDetector(simpleOnGestureListener);
		v.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
//				Log.i("Linhnv", "ontouch");
				return detector.onTouchEvent(event);
			}
		});
		
			return v;
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			if(mImageFetcher!=null){
			mImageFetcher.loadImage(entry.getImage(), imageView);
			}
			textView.setText(entry.getTitle());
		}
		
		
	}
}

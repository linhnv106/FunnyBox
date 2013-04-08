package com.linhnv.apps.funnybox.fragment;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.PictureBoxScreen;
import com.linhnv.apps.funnybox.PictureViewScreen;
import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.StoryViewScreen;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.model.StoryEntry;
import com.linhnv.apps.funnybox.utils.ImageFetcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class TopHotFragment  extends Fragment{
	private ViewPager mPicViewPager;
	private ViewPager mStoryViewPager;
	private ImageFetcher mImageFetcher;
	private TopPicPagerAdapter mTopPicPagerAdapter;
	private TopStoryPagerAdapter mStoryPagerAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View v =inflater.inflate(R.layout.top_hot_layout, container, false);
			mStoryViewPager=(ViewPager)v.findViewById(R.id.top_story_pager);
			mPicViewPager=(ViewPager)v.findViewById(R.id.top_pic_pager);
			mPicViewPager.setPageMargin(20);
			mStoryViewPager.setPageMargin(20);

		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<PictureEntry> objects = new ArrayList<PictureEntry>();
		for(int i =0 ;i <Images.imageUrls.length;i++){
			PictureEntry entry = new PictureEntry(i, "image " + i , Images.imageUrls[i]);
			objects.add(entry);
		}
		if(PictureBoxScreen.class.isInstance(getActivity())){
			mImageFetcher=(ImageFetcher)((PictureBoxScreen)getActivity()).getImageFetcher();
		}
		mTopPicPagerAdapter= new TopPicPagerAdapter(this, objects);
		mPicViewPager.setAdapter(mTopPicPagerAdapter);
		
		ArrayList<StoryEntry> object = new ArrayList<StoryEntry>();
		for(int i =0 ;i <Images.imageUrls.length;i++){
			StoryEntry entry = new StoryEntry(i, "image " + i , Images.imageUrls[i],"");
			object.add(entry);
		}
		mStoryPagerAdapter= new TopStoryPagerAdapter(this, object);
		mStoryViewPager.setAdapter(mStoryPagerAdapter);
	}
	
	
	
	
	
	class TopPicPagerAdapter extends FragmentPagerAdapter {
		private List<PictureEntry> mData;
		
		public TopPicPagerAdapter( Fragment activity,ArrayList<PictureEntry> data) {
			super(activity.getChildFragmentManager());
			mData= new ArrayList<PictureEntry>();
			mData.addAll(data);
			Log.i("Linhnv", "TopPicPagerAdapter size :" + mData.size());
		}
		
		@Override
		public Fragment getItem(int arg0) {			
			return new TopPicFragment(mData.get(arg0));
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
	class TopStoryPagerAdapter extends FragmentPagerAdapter {
		private List<StoryEntry> mData;
		
		public TopStoryPagerAdapter( Fragment activity,ArrayList<StoryEntry> data) {
			super(activity.getChildFragmentManager());
			mData= new ArrayList<StoryEntry>();
			mData.addAll(data);
			Log.i("Linhnv", "TopStoryPagerAdapter size :" + mData.size());
		}
		
		@Override
		public Fragment getItem(int arg0) {			
			return new TopStoryFragment(mData.get(arg0));
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
	class TopStoryFragment extends Fragment{
		private ImageView imageView;
		private TextView textView;
		private StoryEntry entry;
		public TopStoryFragment(StoryEntry entry) {
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
				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				super.onLongPress(e);
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				Log.i("Linhnv", "onScroll");
				return super.onScroll(e1, e2, distanceX, distanceY);
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				Log.i("Linhnv", "onFling");
				return super.onFling(e1, e2, velocityX, velocityY);
				
			}

			@Override
			public void onShowPress(MotionEvent e) {
				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				super.onShowPress(e);
			}

			@Override
			public boolean onDown(MotionEvent e) {
				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("Linhnv", "onDown");
				return true;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
//				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("Linhnv", "onDoubleTap");
				return super.onDoubleTap(e);
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
//				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("Linhnv", "onDoubleTapEvent");
				return super.onDoubleTapEvent(e);
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				Log.i("Linhnv", "onSingleTapConfirmed");
//					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				
				return super.onSingleTapConfirmed(e);
			}
			
		};
			
		final GestureDetector detector = new GestureDetector(simpleOnGestureListener);
		v.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("Linhnv", "ontouch");
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

	class TopPicFragment extends Fragment{
		private ImageView imageView;
		private TextView textView;
		private PictureEntry entry;
		public TopPicFragment(PictureEntry entry) {
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
					Intent intent = new Intent(getActivity(), PictureViewScreen.class);
					startActivity(intent);
					return super.onSingleTapUp(e);
				}

				@Override
				public void onLongPress(MotionEvent e) {
					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					super.onLongPress(e);
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					Log.i("Linhnv", "onScroll");
					return super.onScroll(e1, e2, distanceX, distanceY);
				}

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					Log.i("Linhnv", "onFling");
					return super.onFling(e1, e2, velocityX, velocityY);
					
				}

				@Override
				public void onShowPress(MotionEvent e) {
					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					super.onShowPress(e);
				}

				@Override
				public boolean onDown(MotionEvent e) {
					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					Log.i("Linhnv", "onDown");
					return true;
				}

				@Override
				public boolean onDoubleTap(MotionEvent e) {
//					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					Log.i("Linhnv", "onDoubleTap");
					return super.onDoubleTap(e);
				}

				@Override
				public boolean onDoubleTapEvent(MotionEvent e) {
//					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					Log.i("Linhnv", "onDoubleTapEvent");
					return super.onDoubleTapEvent(e);
				}

				@Override
				public boolean onSingleTapConfirmed(MotionEvent e) {
					Log.i("Linhnv", "onSingleTapConfirmed");
//						mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
					
					return super.onSingleTapConfirmed(e);
				}
				
			};
				
			final GestureDetector detector = new GestureDetector(simpleOnGestureListener);
			v.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					Log.i("Linhnv", "ontouch");
					return detector.onTouchEvent(event);
				}
			});
//			v.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Intent intent = new Intent(getActivity(), PictureViewScreen.class);
//					startActivity(intent);
//				}
//			});
			return v;
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			if(mImageFetcher!=null){
			mImageFetcher.loadImage(entry.getImgUrl(), imageView);
			}
			textView.setText(entry.getTitle());
		}
		
		
	}
	
}

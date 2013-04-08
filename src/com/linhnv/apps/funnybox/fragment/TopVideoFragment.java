package com.linhnv.apps.funnybox.fragment;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.PictureBoxScreen;
import com.linhnv.apps.funnybox.PictureViewScreen;
import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.StoryViewScreen;
import com.linhnv.apps.funnybox.VideoBoxScreen;
import com.linhnv.apps.funnybox.VideoViewScreen;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.model.StoryEntry;
import com.linhnv.apps.funnybox.model.VideoEntry;
import com.linhnv.apps.funnybox.provider.LoadDataListener;
import com.linhnv.apps.funnybox.tasks.LoadVideoTask;
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
import android.widget.ProgressBar;
import android.widget.TextView;

public class TopVideoFragment  extends Fragment{
	private ViewPager mFunViewPager;
	private ViewPager mAldultViewPager;
	private ImageFetcher mImageFetcher;
	private TopVideoPagerAdapter mTopFunAdapter;
	private TopVideoPagerAdapter mTopAldultAdapter;
	private ProgressBar mFunBar;
	private ProgressBar mTopBar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View v =inflater.inflate(R.layout.top_hot_video_layout, container, false);
			

		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i("Linhnv->","onActivityCreated");
		mFunViewPager=(ViewPager)getActivity().findViewById(R.id.top_funny_pager);
		mAldultViewPager=(ViewPager)getActivity().findViewById(R.id.top_18_pager);
		mFunBar=(ProgressBar)getActivity().findViewById(R.id.top_funny_progress);
		mTopBar=(ProgressBar)getActivity().findViewById(R.id.top_18_progress);
		mAldultViewPager.setPageMargin(20);
		mFunViewPager.setPageMargin(20);		
		LoadVideoTask mFunTask=new LoadVideoTask(new LoadDataListener<ArrayList<VideoEntry>>() {
			
			@Override
			public void onLoadError(String message) {
				
			}
			
			@Override
			public void onLoadComplete(ArrayList<VideoEntry> t) {
				Log.i("Linhnv", "onLoadComplete");
				mTopFunAdapter= new TopVideoPagerAdapter(TopVideoFragment.this, t);
				mFunViewPager.setAdapter(mTopFunAdapter);
				mFunViewPager.getAdapter().notifyDataSetChanged();
				mFunBar.setVisibility(View.INVISIBLE);
			}
		});
		
		mFunTask.execute("");
		
		LoadVideoTask mTopTask=new LoadVideoTask(new LoadDataListener<ArrayList<VideoEntry>>() {
			
			@Override
			public void onLoadError(String message) {
				
			}
			
			@Override
			public void onLoadComplete(ArrayList<VideoEntry> t) {
				
				mTopAldultAdapter= new TopVideoPagerAdapter(TopVideoFragment.this, t);
				mAldultViewPager.setAdapter(mTopAldultAdapter);
				mTopBar.setVisibility(View.INVISIBLE);
				mAldultViewPager.getAdapter().notifyDataSetChanged();
			}
		});
		
		mTopTask.execute("");
		
		if(VideoBoxScreen.class.isInstance(getActivity())){
			mImageFetcher=(ImageFetcher)((VideoBoxScreen)getActivity()).getImageFetcher();
		}
		
		
	}
	
				
	@Override
	public void onResume() {
		super.onResume();
	}


	class TopVideoPagerAdapter extends FragmentPagerAdapter {
		private List<VideoEntry> mData;
		
		public TopVideoPagerAdapter( Fragment activity,ArrayList<VideoEntry> data) {
			super(activity.getChildFragmentManager());
			mData= new ArrayList<VideoEntry>();
			mData.addAll(data);
//			Log.i("Linhnv", "TopPicPagerAdapter size :" + mData.size());
		}
		
		@Override
		public Fragment getItem(int arg0) {			
			return new VideoFragment(mData.get(arg0));
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public float getPageWidth(int position) {
			
			return 0.4f;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
		
		
	}
	
	class VideoFragment extends Fragment{
		private ImageView imageView;
		private TextView textView;
		private VideoEntry entry;
		public VideoFragment(VideoEntry entry) {
			super();
			this.entry=entry;
			Log.i("Linhnv", "VideoFragment");
			
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
				
				Intent intent = new Intent(getActivity(), VideoViewScreen.class);
				startActivity(intent);
				return super.onSingleTapUp(e);
			}

			@Override
			public void onLongPress(MotionEvent e) {
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				super.onLongPress(e);
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				Log.i("Linhnv", "onScroll");
				if(Math.abs(e1.getY()-e2.getY())>=20){
					mFunViewPager.getParent().requestDisallowInterceptTouchEvent(false);
					mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(false);
					
					return super.onScroll(e1, e2, distanceX, distanceY);
				}
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onScroll(e1, e2, distanceX, distanceY);
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				if(Math.abs(e1.getY()-e2.getY())>=20){
					mFunViewPager.getParent().requestDisallowInterceptTouchEvent(false);
					mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(false);
					
					return super.onScroll(e1, e2,velocityX, velocityY);
				}
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onFling(e1, e2, velocityX, velocityY);
				
			}

			@Override
			public void onShowPress(MotionEvent e) {
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				super.onShowPress(e);
			}

			@Override
			public boolean onDown(MotionEvent e) {
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("Linhnv", "onDown");
				return true;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
//				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("Linhnv", "onDoubleTap");
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onDoubleTap(e);
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
//				mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				return super.onDoubleTapEvent(e);
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				Log.i("Linhnv", "onSingleTapConfirmed");
//					mStoryViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mFunViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				mAldultViewPager.getParent().requestDisallowInterceptTouchEvent(true);
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
				mImageFetcher.loadImage(entry.getImgUrl(), imageView);
			}
			textView.setText(entry.getTitle());
		}
		
		
	}	
	
}

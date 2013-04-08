package com.linhnv.apps.funnybox.adapter;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.utils.ImageFetcher;
import com.linhnv.apps.funnybox.utils.ImageWorker;

public class PictureViewAdapter extends FragmentPagerAdapter{
	private static final String IMAGE_DATA_EXTRA="extra_image_data";
	private ImageFetcher mImageFetcher;
	private Context mContext;
	private LayoutInflater mInflater;
	private List<PictureEntry> mDatas;
	
	public PictureViewAdapter(FragmentActivity	 activity, int textViewResourceId,
			List<PictureEntry> objects,ImageFetcher fetcher) {
		super(activity.getSupportFragmentManager());
		mContext=activity;
		mImageFetcher=fetcher;
		mDatas=objects;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public Fragment getItem(int arg0) {
		return new PictureView(mDatas.get(arg0));
	}
	@Override
	public int getCount() {
		return mDatas.size();
	}

	class PictureView extends Fragment{
		private ImageView mView;
		private final PictureEntry entry;
		
		
		public PictureView(PictureEntry entry) {
			super();
			this.entry=entry;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View convertView=mInflater.inflate(R.layout.picture_page,container,false);
			mView=(ImageView)convertView.findViewById(R.id.picture_page_image);
			return convertView ;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			mImageFetcher.loadImage(entry.getImgUrl(), mView);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			if(mView!=null){
				ImageWorker.cancelWork(mView);
				mView.setImageDrawable(null);
			}
		}

		
		
	}
	
}

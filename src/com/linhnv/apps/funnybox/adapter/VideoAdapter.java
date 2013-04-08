package com.linhnv.apps.funnybox.adapter;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.model.VideoEntry;
import com.linhnv.apps.funnybox.utils.ImageFetcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends ArrayAdapter<VideoEntry> {
	private Context mContext;
	private LayoutInflater mInflater;
	private final ImageFetcher mImageFetcher;
	public VideoAdapter(Context context, int textViewResourceId,
			ArrayList<VideoEntry> objects,ImageFetcher imageFetcher) {
		super(context, textViewResourceId, objects);
		mImageFetcher=imageFetcher;
		mContext=context;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;
		final VideoEntry entry =getItem(position);
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.video_entry,null);
			holder=new Holder();
			holder.image=(ImageView)convertView.findViewById(R.id.video_cover);
			holder.textView=(TextView)convertView.findViewById(R.id.video_title);
			convertView.setTag(holder);
		}else{
			holder=(Holder)convertView.getTag();
		}
		holder.textView.setText(entry.getTitle());
		mImageFetcher.loadImage(entry.getImgUrl(), holder.image);		
		return convertView;
	}

	class Holder {
		ImageView image;
		TextView textView;
	}
}

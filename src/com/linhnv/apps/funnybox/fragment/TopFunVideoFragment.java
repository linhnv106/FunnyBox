package com.linhnv.apps.funnybox.fragment;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.PictureViewScreen;
import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.VideoBoxScreen;
import com.linhnv.apps.funnybox.VideoViewScreen;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.adapter.PictureHotAdapter;
import com.linhnv.apps.funnybox.adapter.VideoAdapter;
import com.linhnv.apps.funnybox.fragment.TopVideoFragment.TopVideoPagerAdapter;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.model.VideoEntry;
import com.linhnv.apps.funnybox.provider.LoadDataListener;
import com.linhnv.apps.funnybox.tasks.LoadVideoTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class TopFunVideoFragment extends Fragment {
	private ListView mListView;
	private ProgressBar mProgressBar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v =inflater.inflate(R.layout.video_list_layout,container,false);
		mListView=(ListView)v.findViewById(R.id.list_video);
		mProgressBar=(ProgressBar)v.findViewById(R.id.lv_progressBar);
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		
		LoadVideoTask mTopTask=new LoadVideoTask(new LoadDataListener<ArrayList<VideoEntry>>() {
			
			@Override
			public void onLoadError(String message) {
				mProgressBar.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadComplete(ArrayList<VideoEntry> t) {
				
				VideoAdapter adapter = new VideoAdapter(getActivity(), 0, t,VideoBoxScreen.getImageFetcher());
				mListView.setAdapter(adapter);
				mProgressBar.setVisibility(View.GONE);
			}
		});
		mTopTask.execute("");
		
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				Intent intent = new Intent(getActivity(), VideoViewScreen.class);
				startActivity(intent);
			}
			
		});
	}
	

}

package com.linhnv.apps.funnybox.fragment;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.PictureViewScreen;
import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.StoryViewScreen;
import com.linhnv.apps.funnybox.adapter.HotStoryAdapter;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.adapter.PictureHotAdapter;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.model.StoryEntry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class HotStoryBoxFragment extends Fragment {
	private GridView mGridView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v =inflater.inflate(R.layout.picture_hot_layout,container,false);
		mGridView=(GridView)v.findViewById(R.id.hot_pic_grid);
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		List<StoryEntry> objects = new ArrayList<StoryEntry>();
		for(int i =0 ;i <Images.imageUrls.length;i++){
			StoryEntry entry = new StoryEntry(i, "image " + i , Images.imageUrls[i],"");
			objects.add(entry);
		}
		HotStoryAdapter adapter = new HotStoryAdapter(getActivity(), 0, objects);
		mGridView.setAdapter(adapter);
		
		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				Intent intent = new Intent(getActivity(), StoryViewScreen.class);
				startActivity(intent);
			}
			
		});
	}
	

}

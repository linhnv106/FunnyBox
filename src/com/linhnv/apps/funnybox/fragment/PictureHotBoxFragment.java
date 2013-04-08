package com.linhnv.apps.funnybox.fragment;

import java.util.ArrayList;
import java.util.List;

import com.linhnv.apps.funnybox.PictureViewScreen;
import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.adapter.Images;
import com.linhnv.apps.funnybox.adapter.PictureHotAdapter;
import com.linhnv.apps.funnybox.model.PictureEntry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class PictureHotBoxFragment extends Fragment {
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
		List<PictureEntry> objects = new ArrayList<PictureEntry>();
		for(int i =0 ;i <Images.imageUrls.length;i++){
			PictureEntry entry = new PictureEntry(i, "image " + i , Images.imageUrls[i]);
			objects.add(entry);
		}
		PictureHotAdapter adapter = new PictureHotAdapter(getActivity(), 0, objects);
		mGridView.setAdapter(adapter);
		
		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				Intent intent = new Intent(getActivity(), PictureViewScreen.class);
				startActivity(intent);
			}
			
		});
	}
	

}

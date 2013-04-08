package com.linhnv.apps.funnybox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imageView;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		setContentView(R.layout.activity_main);
		imageView=(ImageView)findViewById(R.id.pic_image);
		imageView.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, PictureBoxScreen.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void OnClick(View v){
		if(v==null){
			return;
		}
		int id =v.getId();
		Intent intent =null;
		switch(id){
		case R.id.pic_image:
			 intent = new Intent(mContext, StoryBoxScreen.class);
			 break;
		case R.id.story_img:
			intent = new Intent(mContext, NewsBoxScreen.class);
			 break;
		case R.id.video_img:
			intent = new Intent(mContext, VideoBoxScreen.class);
			break;
	    default:
	    	break;
			
		}
		if(intent!=null){
		startActivity(intent);
		}
	}

}

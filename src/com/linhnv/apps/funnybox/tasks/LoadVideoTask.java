package com.linhnv.apps.funnybox.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.linhnv.apps.funnybox.model.TempData;
import com.linhnv.apps.funnybox.model.VideoEntry;
import com.linhnv.apps.funnybox.provider.LoadDataListener;

import android.os.AsyncTask;

public class LoadVideoTask extends AsyncTask<String, Void, ArrayList<VideoEntry>> {
	private LoadDataListener<ArrayList<VideoEntry>> loadDataListener;
	
	public LoadVideoTask(LoadDataListener<ArrayList<VideoEntry>> loadDataListener){
		super();
		this.loadDataListener=loadDataListener;
	}
	
	@Override
	protected ArrayList<VideoEntry> doInBackground(String... params) {
		/**
		 * test data
		 */
		ArrayList<VideoEntry> result = null;
		try{
			
			JSONObject ob = new JSONObject(TempData.video);
			JSONArray data = ob.getJSONArray("data");
			int count = data.length();
			 String videoId="";
			 String title="";
			 String imgUrl="";
			 String source="";
			 String category="";
			 String type="";
			 JSONObject obj=null;
			 result=new ArrayList<VideoEntry>();
			for(int i=0;i<count;i++){
				obj=data.getJSONObject(i);
				videoId=obj.getString("video_id");
				title=obj.getString("title");
				imgUrl=obj.getString("image");
				imgUrl="http://img.youtube.com/vi/" + videoId+"/0.jpg";
				source=obj.getString("source");
				category=obj.getString("category");
				type=obj.getString("type");
				VideoEntry entry = new VideoEntry(videoId, title, imgUrl, source, category, type);
				result.add(entry);
			}
			return result;
		
		}catch(JSONException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	protected void onPostExecute(ArrayList<VideoEntry> result) {
		if(result==null||result.size()<=0){
			loadDataListener.onLoadError("Load Data Error !");
		}else{
			loadDataListener.onLoadComplete(result);
		}
	}
	

}

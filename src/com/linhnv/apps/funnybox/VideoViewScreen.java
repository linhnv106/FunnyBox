package com.linhnv.apps.funnybox;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.linhnv.apps.funnybox.adapter.VideoAdapter;
import com.linhnv.apps.funnybox.model.VideoEntry;
import com.linhnv.apps.funnybox.provider.LoadDataListener;
import com.linhnv.apps.funnybox.tasks.LoadVideoTask;
import com.linhnv.apps.funnybox.utils.ImageFetcher;
public class VideoViewScreen extends YouTubeFailureRecoveryActivity {
	 public static final String DEVELOPER_KEY="AIzaSyBLsVNMzPauCBTSbMESP9cTeQuL3SBRd4E";
	private ListView mRelatedView;
	private ProgressBar mProgressBar;
	private static final String IMAGE_CACHE_DIR = "thumbs";
    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.video_fragment);
//		mImageThumbSize =getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
//        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
//        ImageCacheParams cacheParams = new ImageCacheParams(this,IMAGE_CACHE_DIR);
//        cacheParams.setMemCacheSizePercent(0.25f);

        // Allocate a third of the per-app memory limit to the bitmap memory cache. This value
        // should be chosen carefully based on a number of factors. Refer to the corresponding
        // Android Training class for more discussion:
        // http://developer.android.com/training/displaying-bitmaps/
        // In this case, we aren't using memory for much else other than this activity and the
        // ImageDetailActivity so a third lets us keep all our sample image thumbnails in memory
        // at once.
//        cacheParams.memCacheSize = 1024 * 1024 * Utils.getMemoryClass(activity) / 3;

        // The ImageWorker takes care of loading images into our ImageView children asynchronously
        mImageFetcher = VideoBoxScreen.getImageFetcher();//lack
//        mImageWorker.setAdapter(Images.imageWorkerUrlsAdapter);
//        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
//        mImageFetcher.addImageCache(null, cacheParams);
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);    
        mRelatedView=(ListView)findViewById(R.id.list_video);
		mProgressBar=(ProgressBar)findViewById(R.id.progressBar);	
        
		LoadVideoTask mTopTask=new LoadVideoTask(new LoadDataListener<ArrayList<VideoEntry>>() {
			
			@Override
			public void onLoadError(String message) {
				mProgressBar.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadComplete(ArrayList<VideoEntry> t) {
				
				VideoAdapter adapter = new VideoAdapter(VideoViewScreen.this, 0, t, mImageFetcher);
				mRelatedView.setAdapter(adapter);
				mProgressBar.setVisibility(View.GONE);
			}
		});
		mTopTask.execute("");
		
	}
	
	

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		if (!wasRestored) {
			player.cueVideo("X9TnkMOPhXc");
		}
	}

	@Override
	protected Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

}

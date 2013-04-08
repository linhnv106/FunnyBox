package com.linhnv.apps.funnybox.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linhnv.apps.funnybox.R;
import com.linhnv.apps.funnybox.model.PictureEntry;
import com.linhnv.apps.funnybox.utils.ImageCache;
import com.linhnv.apps.funnybox.utils.ImageCache.ImageCacheParams;
import com.linhnv.apps.funnybox.utils.ImageFetcher;
import com.linhnv.apps.funnybox.utils.ImageResizer;
import com.linhnv.apps.funnybox.utils.Utils;

public class PictureHotAdapter extends ArrayAdapter<PictureEntry> {
	private Context mContext;
	private LayoutInflater mInflater;
	private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;
	public PictureHotAdapter(FragmentActivity activity, int textViewResourceId,
			List<PictureEntry> objects) {
		
		super(activity, textViewResourceId, objects);
		mContext=activity;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageThumbSize = mContext.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = mContext.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        ImageCacheParams cacheParams = new ImageCacheParams(activity,IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f);

        // Allocate a third of the per-app memory limit to the bitmap memory cache. This value
        // should be chosen carefully based on a number of factors. Refer to the corresponding
        // Android Training class for more discussion:
        // http://developer.android.com/training/displaying-bitmaps/
        // In this case, we aren't using memory for much else other than this activity and the
        // ImageDetailActivity so a third lets us keep all our sample image thumbnails in memory
        // at once.
//        cacheParams.memCacheSize = 1024 * 1024 * Utils.getMemoryClass(activity) / 3;

        // The ImageWorker takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(activity, mImageThumbSize);
//        mImageWorker.setAdapter(Images.imageWorkerUrlsAdapter);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(activity.getSupportFragmentManager(), cacheParams);

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;
		final PictureEntry entry =getItem(position);
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.picture_box_entry, null);
			holder = new Holder();
			holder.image =(ImageView)convertView.findViewById(R.id.box_image);
			holder.textView=(TextView)convertView.findViewById(R.id.box_title);
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

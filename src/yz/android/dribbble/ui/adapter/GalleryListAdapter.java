package yz.android.dribbble.ui.adapter;


import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.ui.PlayerActivity;
import yz.android.dribbble.ui.ShotActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class GalleryListAdapter extends BaseAdapter {

	public List<Shot> dataList;
	private Context context;;
	private int[] to;
	private Item item;
	private int resource;

	private LayoutInflater mInflater;
	private ImageLoadingListener animateFirstListener;

	private DisplayImageOptions optionsGallery;
	private DisplayImageOptions optionsPlayAvatar;

	private class Item {
		ImageView imageViewGallery;
		ImageView imageViewPlayer;
		TextView textViewTitle;
		TextView textViewPalyUsername;
		TextView textViewLikeCount;
		TextView textViewCommentsCount;
	}

	public GalleryListAdapter(Context context, List<Shot> dataList,
			int resource) {
		this.context = context;
		this.dataList = dataList;
		this.resource = resource;
		this.to = new int[]{
				R.id.imageViewGallery, R.id.imageViewPlayer, 
				R.id.textViewTitle, R.id.textViewPlayerUsername,
				R.id.textViewLikeCount,R.id.textViewCommentsCount
		};
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		optionsGallery = new DisplayImageOptions.Builder()
//			.showImageOnLoading(R.drawable.loading)
			.showImageForEmptyUri(R.drawable.loading)
			.showImageOnFail(R.drawable.loading)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.displayer(new RoundedBitmapDisplayer(20))
			.build();
		optionsPlayAvatar = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.icon)
			.showImageForEmptyUri(R.drawable.loading)
			.showImageOnFail(R.drawable.loading)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.displayer(new RoundedBitmapDisplayer(20))
			.build();
		animateFirstListener = new AnimateFirstDisplayListener();
	}

	public GalleryListAdapter(Context context, List<Shot> dataList) {
		this(context, dataList, R.layout.gallery_item);
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		dataList.remove(position);
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			item = (Item) convertView.getTag();
		} else {
			convertView = mInflater.inflate(resource, null);
			item = new Item();
			item.imageViewGallery = (ImageView) convertView.findViewById(to[0]);
			item.imageViewPlayer = (ImageView) convertView.findViewById(to[1]);
			item.textViewTitle = (TextView) convertView.findViewById(to[2]);
			item.textViewPalyUsername = (TextView) convertView.findViewById(to[3]);
			item.textViewLikeCount = (TextView) convertView.findViewById(to[4]);
			item.textViewCommentsCount = (TextView) convertView.findViewById(to[5]);
			convertView.setTag(item);
		}
		item.imageViewGallery.setImageResource(R.drawable.loading);

		final Shot shot = dataList.get(position);
		if (shot != null) {
			ImageLoader.getInstance().displayImage(shot.getImageUrl(), item.imageViewGallery, optionsGallery,animateFirstListener);
			ImageLoader.getInstance().displayImage(shot.getPalyer().getAvatarUrl(), item.imageViewPlayer, optionsPlayAvatar ,animateFirstListener);
			item.textViewTitle.setText(shot.getTitle());
			item.textViewPalyUsername.setText(shot.getPalyer().getName());
			item.textViewLikeCount.setText(String.valueOf(shot.getLikeCount()));
			item.textViewCommentsCount.setText(String.valueOf(shot.getCommentsCount()));
		}
		
		OnClickListener onClickListener = new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(view.getId() == to[0]){
					Intent intent = new Intent(context,ShotActivity.class);
					intent.putExtra("shot", shot);
					context.startActivity(intent);
				}else if(view.getId() == to[1]){
					Intent intent = new Intent(context,PlayerActivity.class);
					intent.putExtra("player", shot.getPalyer());
					context.startActivity(intent);
				}
			}
		};
		item.imageViewGallery.setOnClickListener(onClickListener);
		item.imageViewPlayer.setOnClickListener(onClickListener);
		
		return convertView;
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				imageView.setImageBitmap(loadedImage);
//				boolean firstDisplay = !displayedImages.contains(imageUri);
//				if (firstDisplay) {
//					FadeInBitmapDisplayer.animate(imageView, 500);
//					displayedImages.add(imageUri);
//				}
			}
		}
	}
}

package yz.android.dribbble.ui.adapter;


import java.util.ArrayList;
import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.pojo.Comment;
import yz.android.dribbble.pojo.Player;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.ui.PlayerActivity;
import android.app.Activity;
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

public class CommentsListAdapter extends BaseAdapter{

	public List<Object> dataList;
	private Activity context;;
	private int[] to;
	private Item item;
	private int resource;

	private LayoutInflater mInflater;
	private ImageLoadingListener animateFirstListener;

	private DisplayImageOptions optionsPlayAvatar;

	private class Item {
		ImageView imageViewPlayer;
		TextView textViewPalyUsername;
		TextView textViewbody;
	}

	public CommentsListAdapter(Activity context, List<Object> dataList,
			int resource) {
		this.context = context;
		this.dataList = dataList;
		if(dataList == null){
			this.dataList = new ArrayList<Object>();
		}
		this.resource = resource;
		this.to = new int[]{R.id.imageViewPlayAvatar, R.id.textViewUsername,R.id.textViewBody};
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

	public CommentsListAdapter(Activity context, List<Object> dataList) {
		this(context, dataList, R.layout.comment_item);
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
		if(dataList.get(position) instanceof Shot){
			Shot shot = (Shot) dataList.get(position);
			View galleryItemView = context.getLayoutInflater().inflate(R.layout.gallery_item, null); 
			ImageView imageVIewUser = (ImageView) galleryItemView.findViewById(R.id.imageViewPlayer);
			imageVIewUser.setOnClickListener(new OnPlayerClicked(shot.getPalyer()));
			ImageLoader.getInstance().displayImage(shot.getImageUrl(), (ImageView) galleryItemView.findViewById(R.id.imageViewGallery));
			ImageLoader.getInstance().displayImage(shot.getPalyer().getAvatarUrl(), imageVIewUser);
			((TextView)galleryItemView.findViewById(R.id.textViewTitle)) .setText(shot.getTitle());
			((TextView)galleryItemView.findViewById(R.id.textViewPlayerUsername)) .setText(shot.getPalyer().getUsername());
			((TextView)galleryItemView.findViewById(R.id.textViewLikeCount)) .setText(String.valueOf(shot.getLikeCount()));
			((TextView)galleryItemView.findViewById(R.id.textViewCommentsCount)) .setText(String.valueOf(shot.getCommentsCount()));
			return galleryItemView;
		}
		
		if(dataList.get(position) instanceof View){
			return (View) dataList.get(position);
		}
			
		if (convertView != null && convertView.getTag()!=null) {
			item = (Item) convertView.getTag();
		} else {
			convertView = mInflater.inflate(resource, null);
			item = new Item();
			item.imageViewPlayer = (ImageView) convertView.findViewById(to[0]);
			item.textViewPalyUsername = (TextView) convertView.findViewById(to[1]);
			item.textViewbody= (TextView) convertView.findViewById(to[2]);
			convertView.setTag(item);
		}

		final Comment comment = (Comment) dataList.get(position);
		if (comment != null) {
			ImageLoader.getInstance().displayImage(comment.getPlayer().getAvatarUrl(), item.imageViewPlayer, optionsPlayAvatar ,animateFirstListener);
			item.textViewPalyUsername.setText(comment.getPlayer().getUsername());
			item.textViewbody.setText(String.valueOf(comment.getBody()));
		}
		
		item.imageViewPlayer.setOnClickListener(new OnPlayerClicked(comment.getPlayer()));
		return convertView;
	}
	
	public class OnPlayerClicked implements OnClickListener{
		Player player;
		public OnPlayerClicked(Player player){
			this.player = player;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(context,PlayerActivity.class);
			intent.putExtra("player",player);
			context.startActivity(intent);			
		}
		
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

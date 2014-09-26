package yz.android.dribbble.ui;

import yz.android.dribbble.R;
import yz.android.dribbble.pojo.Player;
import yz.android.dribbble.ui.base.BaseActionBarActivity;
import yz.android.dribbble.util.AppUtil;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class PlayerActivity extends BaseActionBarActivity {

	Player player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_player);
		
		if(getIntent().getSerializableExtra("player") != null){
			player = (Player) getIntent().getSerializableExtra("player");
		
			ImageView imageViewAvatar = (ImageView) findViewById(R.id.imageViewAvatar);
			TextView textViewUsername = (TextView) findViewById(R.id.textViewUsername);
			TextView textViewLocation = (TextView) findViewById(R.id.textViewLocation);
			TextView textViewFollowers = (TextView) findViewById(R.id.textViewFollower);
			TextView textViewFollowing = (TextView) findViewById(R.id.textViewFollowing);
			TextView textViewShots = (TextView) findViewById(R.id.textViewShots);
			TextView textViewLikes = (TextView) findViewById(R.id.textViewLikes);
			TextView textViewWebUrl= (TextView) findViewById(R.id.textViewWebUrl);
			TextView textViewTwitter = (TextView) findViewById(R.id.textViewUseTwitter);
			TextView textViewDribbble = (TextView) findViewById(R.id.textViewUseDribbble);
			
			ImageLoader.getInstance().displayImage(player.getAvatarUrl(), imageViewAvatar);
			String name = player.getName();
			textViewUsername.setText(name);
			String location = AppUtil.isEmptyString(player.getLocation())?"":player.getLocation();
			textViewLocation.setText(location);
			textViewFollowers.setText(String.valueOf("Followers\n"+player.getFollowersCount()));
			textViewFollowing.setText(String.valueOf("Following\n"+player.getFollowingCount()));
			textViewShots.setText(String.valueOf("Shots\n"+player.getShotsCount()));
			textViewLikes.setText(String.valueOf("Likes\n"+player.getLikesCount()));
			textViewWebUrl.setText(player.getWebSiteUrl());
			textViewTwitter.setText(player.getTwitterScreenname());
			textViewDribbble.setText(player.getUrl());
			
			ActionBar actionBar = getActionBar();
			actionBar.setTitle(name);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}

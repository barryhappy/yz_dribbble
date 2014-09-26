package yz.android.dribbble.ui.base;

import java.util.ArrayList;
import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.util.AppUtil;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActionBarActivity extends ActionBarActivity{
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.navigation_background));
		actionBar.setIcon(getResources().getDrawable(R.drawable.back));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.title:
			onBackPressed();
			return super.onOptionsItemSelected(item);
		case android.R.id.home :
			onBackPressed();
			return super.onOptionsItemSelected(item);
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	public void logV(Object log){
		Log.v("", log.toString());
	}


	public void systemShare(String string) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
		if (resInfo != null) {
			List<Intent> targetedShareIntents = new ArrayList<Intent>();
			for (ResolveInfo info : resInfo) {
				Intent targeted = new Intent(Intent.ACTION_SEND);
				targeted.setType("text/plain");
				ActivityInfo activityInfo = info.activityInfo;
				targeted.putExtra(Intent.EXTRA_TEXT, string);
				targeted.setPackage(activityInfo.packageName);
				targetedShareIntents.add(targeted);
			}
			Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to share");
			if (chooserIntent == null) {
				return ;
			}
			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[] {}));
			try {
				startActivity(chooserIntent);
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this, "Can't find sharecomponent to share", Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	public void toast(String text){
		AppUtil.toastText(text);
	}
	
	public void toast(int resId){
		AppUtil.toastText(resId);
	}
	
}

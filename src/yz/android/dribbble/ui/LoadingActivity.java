package yz.android.dribbble.ui;

import yz.android.dribbble.R;
import yz.android.dribbble.application.AppConfig;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_loading);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	
	@Override
	protected void onResume() {
		if(!AppConfig.app.IF_SHOW_LOADING_ACTIVITY){
			gotoMainActivity();
		}else{
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(AppConfig.app.LUNCHTIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gotoMainActivity();
					
				}
			}).start();
			
		}
		super.onResume();
	}

	private void gotoMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}

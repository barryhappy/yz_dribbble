package yz.android.dribbble.ui;

import java.util.Calendar;

import yz.android.dribbble.R;
import yz.android.dribbble.application.AppConfig;
import yz.android.dribbble.ui.custome.TabPage;
import yz.android.dribbble.util.AppUtil;

public class MainActivity extends BaseTabActivity{
	private long lastPressBackTime = 0L;
	
	protected void initTabPageList(){
		TabPage tabPageDebuts = new TabPage(0, this, AppConfig.shoutType.DEBUTS);
		TabPage tabPageEveryOne = new TabPage(1, this, AppConfig.shoutType.EVERYONE);
		TabPage tabPagePopular = new TabPage(2, this, AppConfig.shoutType.POPULAR);
		TabPage tabPageFavorite = new TabPage(3, this, AppConfig.shoutType.FAVORITE);
		tabPageList.append(0, tabPageDebuts);
		tabPageList.append(1, tabPageEveryOne);
		tabPageList.append(2, tabPagePopular);
		tabPageList.append(3, tabPageFavorite);
		listViewTabPages.add(tabPageDebuts.getLayout());
		listViewTabPages.add(tabPageEveryOne.getLayout());
		listViewTabPages.add(tabPagePopular.getLayout());
		listViewTabPages.add(tabPageFavorite.getLayout());
	}
	
	@Override
	public void onBackPressed() {
		long currentTime = Calendar.getInstance().getTime().getTime();
		if(currentTime - lastPressBackTime > AppConfig.app.DOUBLE_BACK_TO_EXIT_TIME){
			AppUtil.toastText(R.string.tip_press_again_to_exit, false);
			lastPressBackTime = currentTime;
		}else{
			super.onBackPressed();
		}
	}

	@Override
	protected void onAppStart() {
		if(AppUtil.getNetworkStatu(this)==AppConfig.networkState.NONE){
			mPager.setCurrentItem(3);
			loadListViewData(3);
		}else{
			loadListViewData(0);
		}
	}
}
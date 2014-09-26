package yz.android.dribbble.util;

import yz.android.dribbble.application.AppConfig;
import yz.android.dribbble.application.YZApplication;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.view.Gravity;
import android.widget.Toast;

public class AppUtil {

	public static boolean isEmptyString(String string) {
		return null == string || "".equals(string);
	}

	public static boolean isNullObject(Object obj) {
		return null == obj;
	}

	private static void toast(String text, boolean ifCenter) {
		Toast toast = Toast.makeText(YZApplication.getContext(), text, Toast.LENGTH_SHORT);
		if(ifCenter){
			toast.setGravity(Gravity.CENTER, 0, 0);
		}
		toast.show();
	}

//	private static void toastText(int resId) {
//		Toast toast = Toast.makeText(YZApplication.getContext(), YZApplication.getContext().getResources().getString(resId),
//				Toast.LENGTH_SHORT);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.show();
//	}
	
	public static void toastText(Object... args){
		String text ;
		boolean ifCenterGravity = true;
		if(args[0] instanceof String){
			text = (String) args[0];
		}else if(args[0] instanceof Integer){
			text = YZApplication.getContext().getResources().getString((Integer) args[0]);
		}else if(args[0] != null){
			text = args[0].toString();
		}else{
			return;
		}
		
		if(args.length>1 ){
			if(args[1] instanceof Boolean){
				ifCenterGravity = (Boolean) args[1];
			}
		}
		toast(text,ifCenterGravity);
	}
	
	
	public static int getNetworkStatu(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		State statuMobile =  cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		State statusWifi =  cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		int result = AppConfig.networkState.NONE;;
		if("CONNECTED".equals(statuMobile.toString())){
			result = AppConfig.networkState.MOIBLE;
		}else if("CONNECTED".equals(statusWifi.toString())){
			result = AppConfig.networkState.WIFI;
		} 
		return result;
	}

}

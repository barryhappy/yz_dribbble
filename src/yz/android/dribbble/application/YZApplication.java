package yz.android.dribbble.application;

import java.util.HashMap;

import yz.android.dribbble.util.AppUtil;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class YZApplication extends Application {
	
	private static Context context;
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
		context = getApplicationContext();
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(100 * 1024 * 1024) // 100 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				//.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
	public static Context getContext(){
		return context;
	}
	
	private HashMap<Object,Object> map = new HashMap<Object, Object>();

	public  HashMap<Object, Object> getMap() {
		return map;
	}
	
	public void putData(Object key ,Object value){
		this.map.put(key, value);
	}
	
	public Object getData(Object key){
		if(AppUtil.isNullObject(key)){
			return null;
		}
		return this.map.get(key);
	}
}

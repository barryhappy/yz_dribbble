package yz.android.dribbble.application;


public final class AppConfig {
	
	public static final class app{
		public static final boolean IF_SHOW_LOADING_ACTIVITY = true;
		public static final int LUNCHTIME = 1000;
		public static final String ROOT_FILE_PATH = "/sdcard/yz.dribbble/"; 
		public static final long DOUBLE_BACK_TO_EXIT_TIME = 3000;
	}
	
	public static final class shoutType{
		public static final String DEBUTS = "debuts";
		public static final String EVERYONE = "everyone";
		public static final String POPULAR = "popular";
		public static final String FAVORITE = "favorite";
	}
	
	public static final class url{
		private static final String BASE = "http://api.dribbble.com/";
		private static final String BASE_SHOUTS = BASE+"shots/";
		public static final String SHOTS_EVERYONE = BASE_SHOUTS+"everyone";
		public static final String SHOTS_DEBUTS = BASE_SHOUTS+"debuts";
		public static final String SHOTS_POPULAR = BASE_SHOUTS+"popular";
		public static final String SHOUT_PAGE_EXTRA = "?page=";
		public static final String PLAYER = BASE+"players/";
	}
	
	public static final class networkState{
		public static final int NONE = 0;
		public static final int WIFI = 1;
		public static final int MOIBLE = 2;
	}

}

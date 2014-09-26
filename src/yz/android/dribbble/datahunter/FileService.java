package yz.android.dribbble.datahunter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.application.AppConfig;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.util.AppUtil;
import yz.android.dribbble.util.FileUtil;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.nostra13.universalimageloader.core.ImageLoader;

public class FileService {

	private static final String PREFIX_IMG_SHOT = "IMG_SHOT_";
	private static final String PREFIX_IMG_PLAYER = "IMG_PLAYER_";
	private static final String PREFIX_SHOT = "SHOT_";

	public static void saveShot(final Shot shot) {

		new AsyncTask<Object, Void, Boolean>() {

			protected Boolean doInBackground(Object... arg0) {
				String objSaveName = PREFIX_SHOT + shot.getId();
				try {
					FileUtil.saveObject(shot, objSaveName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

//				Bitmap bitmap = ImageLoader.getInstance().loadImageSync(shot.getImageUrl());
//				String fileName = String.valueOf(PREFIX_IMG_SHOT + shot.getId());
//				String filetype = ".png";
//				try {
//					FileUtil.saveBitmap(bitmap, fileName + filetype);
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//
//				Bitmap bitmapAvatar = ImageLoader.getInstance().loadImageSync(shot.getPalyer().getAvatarUrl());
//				String bitmapAvatarName = String.valueOf(PREFIX_IMG_PLAYER + shot.getPalyer().getId());
//				try {
//					FileUtil.saveBitmap(bitmapAvatar, bitmapAvatarName + filetype);
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}

				return true;
			}

			protected void onPostExecute(Boolean result) {
				if (result) {
					AppUtil.toastText(R.string.tip_save_success);
				} else {
					AppUtil.toastText(R.string.tip_save_failed);
				}
			};

		}.execute();

	}

	public static void deleteShot(final Shot shot) {
		new AsyncTask<Object, Void, Boolean>() {

			protected Boolean doInBackground(Object... arg0) {

				File file = new File(AppConfig.app.ROOT_FILE_PATH + PREFIX_SHOT + shot.getId());
				return file.delete();
			}

			protected void onPostExecute(Boolean result) {
				if (result) {
					AppUtil.toastText(R.string.tip_delete_success);
				} else {
					AppUtil.toastText(R.string.tip_delete_failed);
				}
			};

		}.execute();
	}

	public static boolean isShotExist(Shot shot) {
		File file = new File(AppConfig.app.ROOT_FILE_PATH + PREFIX_SHOT + shot.getId());
		try {
			FileUtil.readObject(file.getName());
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static Shot getShot(String shotId) {
		String fileName = PREFIX_SHOT + shotId;
		try {
			Object obj = FileUtil.readObject(fileName);
			return (Shot) obj;
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Shot> getShotList() {
		List<Shot> shots = new ArrayList<Shot>();
		try {
			File[] files = new File(AppConfig.app.ROOT_FILE_PATH).listFiles();
			List<File> shotFiles = new ArrayList<File>();
			for (File file : files) {
				if (file.isFile() && file.getName().startsWith(PREFIX_SHOT)) {
					shotFiles.add(file);
				}
			}

			Collections.sort(shotFiles, new Comparator<File>() {
				@Override
				public int compare(File file1, File file2) {
					return new Long(file2.lastModified() - file1.lastModified()).intValue();
				}
			});

			for (File file : shotFiles) {
				try {
					Object shotFile = FileUtil.readObject(file.getName());
					Shot shot = (Shot) shotFile;
					shots.add(shot);
				} catch (StreamCorruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shots;

	}

}

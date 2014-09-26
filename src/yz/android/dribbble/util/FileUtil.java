package yz.android.dribbble.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import yz.android.dribbble.application.AppConfig;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class FileUtil {

	public static final String ROOT_FILE_DIR = AppConfig.app.ROOT_FILE_PATH;

//	public static boolean saveBitmap(Context c, Bitmap bitmap) {
//		try {
//			MediaStore.Images.Media.insertImage(c.getContentResolver(), FILE_DIR + "hah.png", "testImage.jpg", "test description");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	public static void saveBitmap(Bitmap bitmap, String imageName) throws IOException {
		File file = new File(ROOT_FILE_DIR);
		if (!file.exists()) {
			file.mkdir();
		}
		File imageFile = new File(file, imageName);
		imageFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(imageFile);
		bitmap.compress(CompressFormat.PNG, 100, fos);
		fos.flush();
		fos.close();
	}

	public static Bitmap getBitmap(String imageName) {
		File imageFile = new File(ROOT_FILE_DIR, imageName);
		Bitmap bitmap = null;
		if (imageFile.exists()) {
			try {
				bitmap = BitmapFactory.decodeStream(new FileInputStream(imageFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	public static void saveObject(Serializable obj, String fileName) throws FileNotFoundException, IOException {
		File file = new File(ROOT_FILE_DIR);
		if(!file.exists()){
			file.mkdirs();
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOT_FILE_DIR+fileName));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	public static Object readObject(String fileName) throws StreamCorruptedException, FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROOT_FILE_DIR+fileName));
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}

}

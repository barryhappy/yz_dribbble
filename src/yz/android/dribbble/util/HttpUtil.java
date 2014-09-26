package yz.android.dribbble.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

import android.text.Html;

public class HttpUtil {

	/**
	 * @param urlString
	 * @return
	 */
	public static String getHtmlString(String urlString) {
		try {
			URL url = new URL(urlString);
			//System.out.println(urlString);//TODO
			URLConnection ucon = url.openConnection();
			InputStream instr = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(instr);
			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			return EncodingUtils.getString(baf.toByteArray(), "utf-8");
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTextFromHtml(String html) {
		return Html.fromHtml(html).toString();
	}

	public static String unescapeUnicode(String str) {
		StringBuffer sb = new StringBuffer();
		Matcher matcher = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
		while (matcher.find()) {
			matcher.appendReplacement(sb, (char) Integer.parseInt(matcher.group(1), 16) + "");
		}
		matcher.appendTail(sb);
		return sb.toString().replace("\\", "");
	}
}

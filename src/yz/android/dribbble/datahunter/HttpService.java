package yz.android.dribbble.datahunter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import yz.android.dribbble.application.AppConfig;
import yz.android.dribbble.pojo.Comment;
import yz.android.dribbble.pojo.Comments;
import yz.android.dribbble.pojo.Player;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.pojo.Shots;
import yz.android.dribbble.util.HttpUtil;

public class HttpService {

	public static Shots getShots(String type){
		return getShots(type ,"1");
	}
	
	public static Shots getShots(String type,String page){
		Shots shouts = new Shots();
		String url = AppConfig.url.SHOTS_EVERYONE;
		if(AppConfig.shoutType.DEBUTS.equals(type)){
			url = AppConfig.url.SHOTS_DEBUTS;
		}else if(AppConfig.shoutType.EVERYONE.equals(type)){
			url = AppConfig.url.SHOTS_EVERYONE;
		}else if(AppConfig.shoutType.POPULAR.equals(type)){
			url = AppConfig.url.SHOTS_POPULAR;
		}
		url = url + AppConfig.url.SHOUT_PAGE_EXTRA + page;
		String html = HttpUtil.getHtmlString(url);
		try {
			JSONObject json = new JSONObject(html);
			shouts.setPage(json.getString("page"));
			shouts.setPages(json.getInt("pages"));
			shouts.setPerPage(json.getInt("per_page"));
			shouts.setTotal(json.getInt("total"));
			JSONArray shoutsArrayJson =  json.getJSONArray("shots");
			for(int i=0,length=shoutsArrayJson.length() ; i<length;i++){
				JSONObject j = shoutsArrayJson.getJSONObject(i);
				Shot shot = getShotFromJson(j);
				shouts.getShots().add(shot);
			}
			return shouts;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return shouts;
	}

	public static Shot getShotFromJson(JSONObject j) throws JSONException {
		Shot shot = new Shot();
		shot.setId(j.getLong("id"));
		shot.setTitle(j.getString("title"));
		shot.setDescription(j.getString("description"));
		shot.setHeight(j.getInt("height"));
		shot.setWidth(j.getInt("width"));
		shot.setUrl(j.getString("url"));
		shot.setImageUrl(j.getString("image_url"));
		shot.setLikeCount(j.getInt("likes_count"));
		shot.setCommentsCount(j.getInt("comments_count"));
		JSONObject jp = j.getJSONObject("player");
		
		Player p = getPlayerFromJson(jp);
		
		shot.setPalyer(p);
		return shot;
	}

	public static Player getPlayerFromJson(JSONObject jp) throws JSONException {
		Player player = new Player();
		player.setId(jp.getLong("id"));
		player.setName(jp.getString("name"));
		player.setLocation(jp.getString("location"));
		player.setFollowersCount(jp.getInt("followers_count"));
		player.setFollowingCount(jp.getInt("following_count"));
		player.setLikesCount(jp.getInt("likes_count"));
		player.setDrafteesCount(jp.getInt("draftees_count"));
		player.setLikesReceivedCount(jp.getInt("likes_received_count"));
		player.setCommentsCount(jp.getInt("comments_count"));
		player.setUrl(jp.getString("url"));
		player.setShotsCount(jp.getInt("shots_count"));
		String webUrl = jp.getString("website_url");
		if(webUrl== null || "null".equals(webUrl)){
			webUrl = "";
		}
		player.setWebSiteUrl(webUrl);
		String twitter = jp.getString("twitter_screen_name");
		if(twitter== null || "null".equals(twitter)){
			twitter = "";
		}
		player.setTwitterScreenname(twitter);
		player.setAvatarUrl(jp.getString("avatar_url"));
		player.setUsername(jp.getString("username"));
		return player;
	}
	
	public static Comments getComments(String shotId){
		String html = HttpUtil.getHtmlString("http://api.dribbble.com/shots/"+shotId+"/comments");
		Comments comments = new Comments();
		try {
			JSONObject json = new JSONObject(html);
			comments.setPage(json.getString("page"));
			comments.setPages(json.getInt("pages"));
			comments.setPerPage(json.getInt("per_page"));
			comments.setTotal(json.getInt("total"));
			JSONArray commentArrayJson =  json.getJSONArray("comments");
			for(int i=0,length=commentArrayJson.length() ; i<length;i++){
				JSONObject j = commentArrayJson.getJSONObject(i);
				Comment comment = getCommentFromJson(j);
				comments.getCommments().add(comment);
			}
			return comments;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return comments;
	}

	private static Comment getCommentFromJson(JSONObject j) throws JSONException {
		Comment comment = new Comment();
		comment.setId(j.getLong("id"));
		comment.setLikesCount(j.getInt("likes_count"));
		comment.setBody(j.getString("body"));
		JSONObject jp = j.getJSONObject("player");
		Player p = getPlayerFromJson(jp);
		comment.setPlayer(p);
		return comment;
	}
	
}

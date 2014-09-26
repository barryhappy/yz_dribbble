package yz.android.dribbble.ui;

import java.util.ArrayList;
import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.datahunter.FileService;
import yz.android.dribbble.datahunter.HttpService;
import yz.android.dribbble.pojo.Comment;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.ui.adapter.CommentsListAdapter;
import yz.android.dribbble.ui.base.BaseActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ShotActivity extends BaseActionBarActivity {

	List<Object> dataList = new ArrayList<Object>();

	Shot shot;
//	private ShareActionProvider mShareActionProvider;

	private ListView listviewComments;

	private CommentsListAdapter commentsListAdapter;
	View progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_shot);

		listviewComments = (ListView) findViewById(R.id.listViewComment);

		if (getIntent().getSerializableExtra("shot") != null) {
			shot = (Shot) getIntent().getSerializableExtra("shot");
			dataList.add(shot);
			progressBar = getLayoutInflater().inflate(R.layout.progress_bar, null);
			dataList.add(progressBar);

			commentsListAdapter = new CommentsListAdapter(this, dataList);
			listviewComments.setAdapter(commentsListAdapter);

			new LoadCommentsTask().execute();

			ActionBar actionBar = getActionBar();
			actionBar.setTitle(shot.getTitle());
		}
	}

	class LoadCommentsTask extends AsyncTask<Object, Void, List<Comment>> {

		@Override
		protected List<Comment> doInBackground(Object... arg0) {
			return HttpService.getComments(String.valueOf(shot.getId())).getCommments();
		}

		@Override
		protected void onPostExecute(List<Comment> result) {
			dataList.remove(progressBar);
			dataList.addAll(result);
			commentsListAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shot, menu);
//		MenuItem item = menu.findItem(R.id.action_share);
//		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		if(FileService.isShotExist(shot)){
			menu.removeItem(R.id.action_favorite);
		}else{
			menu.removeItem(R.id.action_unfavorite);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_share:
			String string = shot.getTitle() +" by "+shot.getPalyer().getName()+ " [" + shot.getUrl() + "]";
			systemShare(string);
			break;
		case R.id.action_favorite:
			FileService.saveShot(shot);
			break;
		case R.id.action_unfavorite:
			FileService.deleteShot(shot);
			break;
		case R.id.action_open_in_browser:
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(shot.getUrl()));
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
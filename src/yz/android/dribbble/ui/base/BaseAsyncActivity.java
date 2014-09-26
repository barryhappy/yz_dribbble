package yz.android.dribbble.ui.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;
import android.widget.ProgressBar;
/**
 * 
 * @author Barry
 *
 */
public abstract class BaseAsyncActivity extends BaseActionBarActivity {
	
	ProgressBar progressDialog = null;
	
	boolean needShowProgressDialog = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
	}
	
	public void startTask(Object... params){
		logV("startTask...");
		new LoadDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
	}

	public void startTaskWithoutProgressDialog(){
		startTaskWithoutProgressDialog(null);
	}
	
	public void startTaskWithProgressDialog(){
		startTaskWithProgressDialog(null);
	}
	
	public void startTaskWithoutProgressDialog(Object... params){
		needShowProgressDialog = false;
		startTask(params);
	}
	public void startTaskWithProgressDialog(Object... params){
		needShowProgressDialog = true;
		startTask(params);
	}
	
	public void beforeDoTask(){
	}

	public abstract Object doTask(Object... params);
	
	public abstract void afterDotask(Object result);
	
	class LoadDataTask extends AsyncTask<Object, Void, Object> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			beforeDoTask();
			if(needShowProgressDialog){
				try{
					if(progressDialog != null){
						progressDialog.cancelLongPress();;
					}
					//progressDialog = ProgressDialog.show(BaseAsyncActivity.this, "标题", "内容");
					//progressDialog = new ProgressBar(BaseAsyncActivity.this,null,android.R.attr.progressBarStyleHorizontal);
					setProgressBarIndeterminateVisibility(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		@Override
		protected Object doInBackground(Object... params) {
			//Looper.prepare();
			logV("doTask.....");
			return doTask(params);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			logV("afterDotask...­");
			afterDotask(result);
			setProgressBarIndeterminateVisibility(false);
			if(progressDialog != null){
				//progressDialog.cancel();
			}
			Looper.loop();
		}

	}

}

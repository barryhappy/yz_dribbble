package yz.android.dribbble.ui.custome;

import java.util.ArrayList;
import java.util.List;

import yz.android.dribbble.R;
import yz.android.dribbble.application.AppConfig;
import yz.android.dribbble.datahunter.FileService;
import yz.android.dribbble.datahunter.HttpService;
import yz.android.dribbble.pojo.Shot;
import yz.android.dribbble.ui.adapter.GalleryListAdapter;
import yz.android.dribbble.ui.custome.RefreshableView.PullToRefreshListener;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class TabPage implements OnScrollListener {
	private int indexInTab;
	private ListView listView;
	GalleryListAdapter adapter;
	private int page = 1;
	private int totalPage = 50;
	private int perPage = 15;
	private boolean hasFirstLoad = false;
	private Activity context;
	private String type;
	private View layout;
	private RefreshableView refreshableView;
//	private ProgressBar progressBar;
	private List<Shot> dataList = new ArrayList<Shot>();
	private View bottomLoadingView;

	private int lastVisibleItemIndex;
	private boolean hasFinished = false;
	private boolean isLoding = false;
	
	public TabPage(int indexInTab, final Activity context, final String type) {
		this.indexInTab = indexInTab;
		this.context = context;
		this.type = type;
		LayoutInflater mInflater = context.getLayoutInflater();
		layout = mInflater.inflate(R.layout.listview_gallery_refreshable, null);
		refreshableView = (RefreshableView) layout.findViewById(R.id.refreshableView);
		listView = (ListView) layout.findViewById(R.id.listViewGallery);
		listView.setOnScrollListener(this);
		adapter = new GalleryListAdapter(context, dataList);
		bottomLoadingView = mInflater.inflate(R.layout.main_list_load_more_item, null);
		if(!AppConfig.shoutType.FAVORITE.equals(type)){
			listView.addFooterView(bottomLoadingView);
		}
		listView.setAdapter(adapter);
		PullToRefreshListener listener = new PullToRefreshListener() {
			@Override
			public Object onRefresh() {
				TabPage.this.page = 1;
				List<Shot> dataList = getLatestDataList(type,String.valueOf(TabPage.this.page));
				return dataList;
			}

			@Override
			public void onFinishRefresh(Object result) {
				if (result instanceof List) {
					TabPage.this.dataList = (List<Shot>) result;
					adapter.dataList = TabPage.this.dataList;
					adapter.notifyDataSetChanged();;
				}
				refreshableView.finishRefreshing();
			}
		};
		refreshableView.setOnRefreshListener(listener, indexInTab);
	}

	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public boolean isHasFirstLoad() {
		return hasFirstLoad;
	}

	public void setHasFirstLoad(boolean hasFirstLoad) {
		this.hasFirstLoad = hasFirstLoad;
	}

	public LoadDataTask getLoadDataTask(int page) {
		return new LoadDataTask(layout, type,page);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIndexInTab() {
		return indexInTab;
	}

	public void setIndexInTab(int indexInTab) {
		this.indexInTab = indexInTab;
	}

	public Activity getContext() {
		return context;
	}

	public void setContext(Activity context) {
		this.context = context;
	}

	public View getLayout() {
		return layout;
	}

	public void setLayout(View layout) {
		this.layout = layout;
	}

	public RefreshableView getRefreshableView() {
		return refreshableView;
	}

	public void setRefreshableView(RefreshableView refreshableView) {
		this.refreshableView = refreshableView;
	}

//	public ProgressBar getProgressBar() {
//		return progressBar;
//	}
//
//	public void setProgressBar(ProgressBar progressBar) {
//		this.progressBar = progressBar;
//	}

	private List<Shot> getLatestDataList(final String type,String page) {
		List<Shot> dataList;
		if (AppConfig.shoutType.FAVORITE.equals(type)) {
			dataList = FileService.getShotList();
		} else {
			dataList = HttpService.getShots(type,page).getShots();
		}
		return dataList;
	}

	public class LoadDataTask extends AsyncTask<Object, Void, List<Shot>> {

		public String getType() {
			return type;
		}

		public View getLayout() {
			return layout;
		}

		public LoadDataTask(final View layout, final String type,int page) {
//			this.refreshableView = (RefreshableView) layout.findViewById(R.id.refreshableView);
//			this.listView = (ListView) layout.findViewById(R.id.listViewGallery);
//			this.type = type;
//			this.layout = layout;
//			this.page = page;
			//this.listView.setVisibility(View.INVISIBLE);
		}

		@Override
		protected void onPreExecute() {
			TabPage.this.isLoding = true;
			if(TabPage.this.page != 1){
			}
			super.onPreExecute();
		}

		@Override
		protected List<Shot> doInBackground(Object... arg0) {
			List<Shot> dataList = getLatestDataList(type,String.valueOf(page));
			return dataList;
		}

		@Override
		protected void onPostExecute(List<Shot> dataList) {
//			this.progressBar.setVisibility(View.INVISIBLE);
//			this.listView.setVisibility(View.VISIBLE);
			if(TabPage.this.page == 1){
				TabPage.this.dataList = dataList;
			}else{
				TabPage.this.dataList.addAll(dataList);
			}
			if(TabPage.this.listView.getFooterViewsCount() >0 && !AppConfig.shoutType.FAVORITE.equals(type)){
				TabPage.this.listView.removeFooterView(bottomLoadingView);
			}
			TabPage.this.adapter.dataList = TabPage.this.dataList;
			TabPage.this.adapter.notifyDataSetChanged();
			TabPage.this.listView.addFooterView(bottomLoadingView);
			TabPage.this.isLoding = false;
			if (refreshableView != null) {
				if (refreshableView.getCurrentStatus() != RefreshableView.STATUS_REFRESH_FINISHED) {
					refreshableView.finishRefreshing();
				}
			}
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		lastVisibleItemIndex = firstVisibleItem + visibleItemCount - 1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (OnScrollListener.SCROLL_STATE_IDLE == scrollState 
				&& lastVisibleItemIndex == listView.getCount() - 1 
				&& !AppConfig.shoutType.FAVORITE.equals(this.type)
				&& !hasFinished
				&& !isLoding ) {
			getLoadDataTask(++this.page).execute();
		}
	}

}

package com.zhangtong.googleplay.fragement;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.zhangtong.googleplay.BaseActivity;
import com.zhangtong.googleplay.adapter.MyBaseAdapter;
import com.zhangtong.googleplay.holder.BaseHolder;
import com.zhangtong.googleplay.utils.UIUtils;
import com.zhangtong.googleplay.widget.LoadingPage.LoadResult;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends BaseFragment {

	private List<String> mDatas;

	@Override
	protected View createSuccessView() {
		ListView mListView = new ListView(UIUtils.getContext());
		HomeAdapter adapter = new HomeAdapter(mListView,mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	@Override
	protected LoadResult load() {
		mDatas = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			mDatas.add("内容"+i);
		}
		return chece(mDatas);
	}

	private class HomeAdapter extends MyBaseAdapter{

		private ViewHolder holder;

		public HomeAdapter(ListView mListView, List<String> mDatas) {
			super(mListView,mDatas);
		}


		@Override
		public ViewHolder getHolder() {
			// TODO Auto-generated method stub
			return new ViewHolder();
		}
		
	}
	
	static class ViewHolder extends BaseHolder<String>{
		TextView textView;

		@Override
		public View initView() {
			textView = new TextView(UIUtils.getContext()); 
			return textView;
		}
 
		@Override
		public void refreView() {
			textView.setText((CharSequence) getData());
		}
	}
	
}

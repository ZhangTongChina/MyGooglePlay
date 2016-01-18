package com.zhangtong.googleplay.adapter;

import java.util.List;

import com.zhangtong.googleplay.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class MyBaseAdapter<T> extends BaseAdapter{
	
	public ListView mListView ;
	

	public MyBaseAdapter(ListView mListView, List<T> mDatas) {
		this.mListView = mListView;
		setData(mDatas);
	}
	public List<T> mDatas;
	private BaseHolder holder;
	
	public void setData(List<T> mDatas) {
		this.mDatas = mDatas;
	}
	

	private void mDatas(List<T> mDatas2) {
		// TODO Auto-generated method stub
		
	}


	public List<T> getData(){
		return mDatas;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView != null){
			holder = (BaseHolder) convertView.getTag();
		}else{
			holder = getHolder();
		}
		holder.setData(mDatas.get(position));
		return holder.getRootView();
	}

	public abstract BaseHolder getHolder() ;
}

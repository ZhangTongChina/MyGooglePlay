 package com.zhangtong.googleplay.fragement;

import java.util.List;

import com.zhangtong.googleplay.widget.LoadingPage.LoadResult;
import com.zhangtong.googleplay.utils.UIUtils;
import com.zhangtong.googleplay.widget.LoadingPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	
	private LoadingPage mContentPage;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mContentPage = new LoadingPage(UIUtils.getContext()) {
			@Override
			public View createSuccessView() {
				// TODO Auto-generated method stub
				return BaseFragment.this.createSuccessView();
			}

			@Override
			public LoadResult Load() {
				// TODO Auto-generated method stub
				return BaseFragment.this.load();
			}
		};
		return mContentPage;
	}
	/**
	 * 检查服务器返回的Json数据
	 * @param object
	 * @return
	 */
	protected LoadResult chece(Object object) {
		if(object == null){
			return LoadResult.ERROR;
		}
		if(object instanceof List){
			List list = (List) object;
			if(list.size() == 0){
				return LoadResult.EMPTY;
			}
		}
		return LoadResult.SUCCESS;
	}
	
	protected abstract LoadResult load();
	
	protected abstract View createSuccessView() ;


	public void show() {
		if(null != mContentPage){
			mContentPage.show();
		}
		
	}

}

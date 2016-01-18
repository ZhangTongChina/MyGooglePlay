package com.zhangtong.googleplay.widget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zhangtong.googleplay.R;
import com.zhangtong.googleplay.manager.ThreadManager;
import com.zhangtong.googleplay.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public abstract class LoadingPage extends FrameLayout {
	//默认状态
	private final int UN_LOADING  =  1;
	//加载状态
	private final int LOADING = 2;
	//加载失败状态
	private final int ERROR = 3;
	//加载成功。然后服务器没有返回数据
	private final int EMPTY = 4;
	//加载成功的状态
	private final int SUCCESS = 5;
	//用来记录某种状态
	private int mState;
	private View mLoadingView;
	private View mErrorView;
	private View mEmptyView;

	private View mSuccessView;
	public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		//首先赋值
		mState = UN_LOADING;
		
		mLoadingView = createLoadingView();
		
		if(null != mLoadingView){
			addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		
		mErrorView = createErrorView();
		
		if(null != mErrorView){
			addView(mErrorView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		
		mEmptyView = createEmptyView();
		
		if(null != mEmptyView ){
			addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		showSafePage();
		
	}

	private void showSafePage() {
		UIUtils.runInMainThread(new Runnable() {
			
			@Override
			public void run() {
				showPage();
				
			}
		});
		
	}

	protected void showPage() {
		if(null != mLoadingView){
			mLoadingView.setVisibility(mState == UN_LOADING || mState ==  LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		
		if(null != mErrorView){
			mErrorView.setVisibility(mState == ERROR ? View.VISIBLE : View.INVISIBLE);
		}
		
		if(null != mEmptyView){
			mEmptyView.setVisibility(mState == EMPTY ? View.VISIBLE : View.INVISIBLE);
		}
		
		if(null == mSuccessView && mState == SUCCESS ){
			mSuccessView = createSuccessView();
			addView(mSuccessView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		if(null != mSuccessView){
			mSuccessView.setVisibility(mState == SUCCESS ? View.VISIBLE : View.INVISIBLE);
		}
	}

	public abstract View createSuccessView();

	private View createEmptyView() {
		// TODO Auto-generated method stub
		return UIUtils.inflate(R.layout.loading_page_empty);
	}

	private View createErrorView() {
		// TODO Auto-generated method stub
		return UIUtils.inflate(R.layout.loading_page_error);
	}

	private View createLoadingView() {
		// TODO Auto-generated method stub
		return UIUtils.inflate(R.layout.loading_page_loading);
	}

	public LoadingPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LoadingPage(Context context) {
		super(context);
		init();
	}
//	//加载失败状态
//		private final int ERROR = 3;
//		//加载成功。然后服务器没有返回数据
//		private final int EMPTY = 4;
//		//加载成功的状态
//		private final int SUCCESS = 5;
	public enum LoadResult{
		ERROR(3),EMPTY(4),SUCCESS(5);
		int value ;
		LoadResult(int value){
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	private class LoadTask implements Runnable{

		@Override
		public void run() {
			final LoadResult result = Load();
			UIUtils.runInMainThread(new Runnable() {
				
				@Override
				public void run() {
					
					mState = result.getValue();
					
					showPage();
				}
			});
			
		}
		
	}

	public void show() {
		if(mState == ERROR || mState == EMPTY){
			mState = UN_LOADING;
		}
		
		if(mState == UN_LOADING){
			mState = LOADING;
			
			LoadTask task = new LoadTask();
			ThreadManager.getLongPool().execute(task);
//			ExecutorService service = Executors.newFixedThreadPool(3);
//			LoadTask task = new LoadTask();
//			service.execute(task);
		}
		showSafePage();
	}

	public abstract LoadResult Load() ;

	
}

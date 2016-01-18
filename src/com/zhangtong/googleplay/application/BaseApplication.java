package com.zhangtong.googleplay.application;

import android.app.Application;
import android.os.Handler;

/**
 *	获取到主线程常用的工具类
 */
public class BaseApplication extends Application {
	//获得主线程的上下文
	private static BaseApplication mContext;
	//获得主线程的Handler
	private static Handler mMainThreadHandler;
	//获取到主线程
	private static Thread mMainThread;
	
	private static int mMainThreadId;
	@Override
	public void onCreate() {
		super.onCreate();
		this.mContext = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThread = Thread.currentThread();
		this.mMainThreadId = android.os.Process.myTid();
	}
	
	public static BaseApplication getApplication(){
		return mContext;
	}
	public static Handler getMainThreadHandler(){
		return mMainThreadHandler;
	}
	public static Thread getMainThread(){
		return mMainThread;
	}
	public static int getMainThreadId(){
		return mMainThreadId;
	}
}

package com.zhangtong.googleplay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class BaseActivity extends ActionBarActivity {
	
	private static BaseActivity MForegroundActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initview();
	}
	
	protected abstract void initview() ;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.MForegroundActivity = this;
	}
	
	public static BaseActivity getForegroundActivity(){
		return MForegroundActivity;
	}
}

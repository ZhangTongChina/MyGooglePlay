package com.zhangtong.googleplay;


import com.zhangtong.googleplay.fragement.AppFragment;
import com.zhangtong.googleplay.fragement.BaseFragment;
import com.zhangtong.googleplay.fragement.FragmentFactory;
import com.zhangtong.googleplay.fragement.GameFragement;
import com.zhangtong.googleplay.fragement.HomeFragment;
import com.zhangtong.googleplay.utils.UIUtils;
import com.zhangtong.googleplay.widget.PagerTab;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends BaseActivity implements OnPageChangeListener {

	private PagerTab mTabs;
	private ViewPager mPager;


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	private class MainAdapter extends FragmentPagerAdapter{

		private String[] tab_names;
		private BaseFragment fragment;

		public MainAdapter(FragmentManager fm) {
			super(fm);
			tab_names = UIUtils.getStringArray(R.array.tab_names);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return tab_names[position];
		}
		
		@Override
		public Fragment getItem(int position) {
			return FragmentFactory.creatFragment(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tab_names.length;
		}
		
	}


	@Override
	protected void initview() {
		setContentView(R.layout.activity_main);
		mTabs = (PagerTab) findViewById(R.id.tabs);
		mPager = (ViewPager) findViewById(R.id.pager);
		
		MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
		mPager.setAdapter(adapter);
		
		mTabs.setViewPager(mPager);
		mTabs.setOnPageChangeListener(this);
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int arg0) {
		BaseFragment fragment = FragmentFactory.creatFragment(arg0);
		fragment.show();
 	}

}

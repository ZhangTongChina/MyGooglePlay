package com.zhangtong.googleplay.fragement;

import java.util.HashMap;


/**
 *	产生Fragmeht 
 */
public class FragmentFactory {
	
	private static final int TAB_HOME = 0;
	private static final int TAB_APP = 1;
	private static final int TAB_GAME = 2;
	private static final int TAB_SUBJECT = 3;
	private static final int TAB_RECOMMENT = 4;
	private static final int TAB_CATEGORY = 5;
	private static final int TAB_HOT = 6;
	
	private static HashMap<Integer, BaseFragment> mFragment = new HashMap<Integer, BaseFragment>();
	private static BaseFragment fragment;
	
	public static BaseFragment creatFragment(int position){
		//获取内存中是否有fragment
		fragment = mFragment.get(position);
		//说明内存中没有
		if(null == fragment){
			switch (position) {
			case TAB_HOME:
				fragment = new HomeFragment();
				break;
			case TAB_APP:
				 fragment = new AppFragment();
				break;
			case TAB_GAME:
				fragment = new GameFragement();
				break;
			case TAB_SUBJECT:
				fragment = new SubjectFragment();
				break;
			case TAB_RECOMMENT:
				 fragment = new RecommentFragement();
				break;
			case TAB_CATEGORY:
				fragment = new CategoryFragment();
				break;
			case TAB_HOT:
				fragment = new HotFragment();
				break;
			}
			mFragment.put(position, fragment);
		}
		
		return fragment;
	}
}

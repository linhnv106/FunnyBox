package com.linhnv.apps.funnybox;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.linhnv.apps.funnybox.fragment.HotStoryBoxFragment;
import com.linhnv.apps.funnybox.fragment.PictureHotBoxFragment;
public class StoryBoxScreen extends SherlockFragmentActivity{
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_tabs_pager);
		
		mTabHost=(TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		mViewPager=(ViewPager)findViewById(R.id.pager);
		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
		
		mTabsAdapter.addTab(mTabHost.newTabSpec("Hot Story").setIndicator("TOP HOT"), HotStoryBoxFragment.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("Gallery").setIndicator("Gallery"), HotStoryBoxFragment.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("Recent").setIndicator("Recent"), HotStoryBoxFragment.class, null);
		
		if(arg0!=null){
			mTabHost.setCurrentTabByTag(arg0.getString("tab"));
		}
		
		
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}


	public static class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener,ViewPager.OnPageChangeListener{
		private final Context mContext;
		private final TabHost mTabHost;
		private ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs= new ArrayList<TabInfo>();
		
		static final class TabInfo{
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;
			
			
			public TabInfo(String _tag ,Class<?> _class,Bundle _args) {
				tag=_tag;
				clss=_class;
				args=_args;
			}
			
		}
		
		
		public TabsAdapter(FragmentActivity activity ,TabHost tabHost,ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext=activity;
			mTabHost=tabHost;
			mViewPager=pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		static class DummyTabFactory implements TabHost.TabContentFactory{
			private final Context mContext;
			
			public DummyTabFactory(Context context){
				mContext=context;
			}
			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
			
		}
		
		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss,Bundle args){
				tabSpec.setContent(new DummyTabFactory(mContext));
				String tag=tabSpec.getTag();
				TabInfo info = new TabInfo(tag, clss, args);
				mTabs.add(info);
				mTabHost.addTab(tabSpec);
				notifyDataSetChanged();
			
		}
		
		@Override
		public Fragment getItem(int arg0) {
			TabInfo info=mTabs.get(arg0);
			return  Fragment.instantiate(mContext, info.clss.getName(),info.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(arg0);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			mViewPager.setCurrentItem(position);
		}
		
	}
	
}

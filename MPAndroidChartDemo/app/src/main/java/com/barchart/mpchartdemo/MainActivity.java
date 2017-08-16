package com.barchart.mpchartdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.barchart.mpchartdemo.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
	private List<Fragment> listFragment;
	private RadioGroup rGrpAllTabP;
	private NewFragment newFragment = new NewFragment(); //老版页面
	private OldFragment oldFragment = new OldFragment(); //新版页面
	private ViewFragment viewFragment = new ViewFragment(); //新版页面
	private RadioButton rBtn_oldTab,rBtn_newTab,rBtn_myTab;
	private static CustomViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rGrpAllTabP = (RadioGroup)findViewById(R.id.rGrpAllTabP);
		rBtn_oldTab = (RadioButton)findViewById(R.id.rBtn_oldTab);
		rBtn_newTab = (RadioButton)findViewById(R.id.rBtn_newTab);
		rBtn_myTab = (RadioButton)findViewById(R.id.rBtn_myTab);
		initListener();
		setUpViewPager();
	}

	public void initListener() {
		rGrpAllTabP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int position = 0;
				switch (checkedId) {
					case R.id.rBtn_oldTab:
						position = 0;
						break;
					case R.id.rBtn_newTab:
						position = 1;
						break;
					case R.id.rBtn_myTab:
						position = 2;
						break;
				}
				rGrpAllTabP.check(checkedId);
				if (mViewPager != null) mViewPager.setCurrentItem(position, true);
			}
		});
		listFragment = new ArrayList<>();
		listFragment.add(newFragment);
		listFragment.add(oldFragment);
		listFragment.add(viewFragment);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
			@Override
			public Fragment getItem(int position) {
				return listFragment.get(position);
			}

			@Override
			public int getCount() {
				return listFragment.size();
			}
		};
		mViewPager = (CustomViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(fragmentPagerAdapter);
		mViewPager.setOffscreenPageLimit(listFragment.size());
	}


	private void setUpViewPager() {
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				int checkId = R.id.rBtn_oldTab;
				if (position == 0) {
					checkId = R.id.rBtn_oldTab;
				} else if (position == 1) {
					checkId = R.id.rBtn_newTab;
				}else if (position == 2) {
					checkId = R.id.rBtn_myTab;
				}
				if (rGrpAllTabP != null)
					rGrpAllTabP.check(checkId);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
//                switch (state) {
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        //TODO
//                        break;
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        //TODO
//                        break;
//                    case ViewPager.SCROLL_STATE_SETTLING:
//                        //TODO
//                        break;
//                    default:
//                        //TODO
//                        break;
//                }
			}
		});

		if (mViewPager != null) mViewPager.setCurrentItem(0, true);
	}
}

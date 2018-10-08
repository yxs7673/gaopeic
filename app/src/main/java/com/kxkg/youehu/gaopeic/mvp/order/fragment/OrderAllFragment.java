package com.kxkg.youehu.gaopeic.mvp.order.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kxkg.youehu.developlib.base.BaseFragment;
import com.kxkg.youehu.gaopeic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yxs on 2018/8/27.
 */

public class OrderAllFragment extends BaseFragment {


    private   TabLayout tabsOrderAll;
    private   ViewPager viewpager;
    private WorkFragment workFragment;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, boolean b) {

        View rootView =inflater.inflate(R.layout.fragment_order_all, container, false);

        viewpager = (ViewPager) rootView.findViewById(R.id.viewpager_order_all);
        tabsOrderAll = (TabLayout) rootView.findViewById(R.id.tabs_order_all);

        if (viewpager!=null){
            setupViewPager(viewpager);
            viewpager.setOffscreenPageLimit(2);

        }
        tabsOrderAll.setupWithViewPager(viewpager);

        return rootView;
    }

    @Override
    protected void processLogic() {

    }

    private void setupViewPager(ViewPager viewpage) {
        OrderAllAdapter adapter=new OrderAllAdapter(getChildFragmentManager());

        workFragment=new WorkFragment();
        adapter.addFragment(workFragment,"当前任务");
        adapter.addFragment(new HistoryFragment(),"历史订单");
        viewpage.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    class OrderAllAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public OrderAllAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

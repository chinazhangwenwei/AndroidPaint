package com.xol.android_paint;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xol.fragment.PaintContentFragment;
import com.xol.model.PaintViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpContent;
    private TabLayout mTabLayout;
    private List<PaintViewModel> modelList;


    {
        modelList = new ArrayList<>();
        modelList.add(new PaintViewModel("线性渲染", R.layout.fragment_for_linear1));
        modelList.add(new PaintViewModel("线性文字特效", R.layout.fragment_for_linear));
        modelList.add(new PaintViewModel("扫描渲染", R.layout.fragment_for_sweep));
        modelList.add(new PaintViewModel("雷达扫描", R.layout.fragment_for_radar));
        modelList.add(new PaintViewModel("放射渲染", R.layout.fragment_for_radial));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mVpContent = findViewById(R.id.vp_content);
        mTabLayout = findViewById(R.id.tab_layout);
        mVpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("model", modelList.get(i));
                return PaintContentFragment.createFragment(bundle);
            }

            @Override
            public int getCount() {
                return modelList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return modelList.get(position).mTitle;
            }
        });
        mTabLayout.setupWithViewPager(mVpContent);

    }
}

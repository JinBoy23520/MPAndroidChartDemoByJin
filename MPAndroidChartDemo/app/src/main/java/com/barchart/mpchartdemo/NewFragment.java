package com.barchart.mpchartdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barchart.mpchartdemo.R;

/**
 * 源码新版图表使用demo 涉及修改源码
 * Created by jinB
 */
public class NewFragment extends Fragment {
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_new, container, false);

        return mView;
    }
}

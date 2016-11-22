package com.xiaoququ.androidFlux.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoququ.R;
import com.xiaoququ.androidFlux.view.base.BaseRxFragment_v4;

public class MainFragment03 extends BaseRxFragment_v4 {
    public MainFragment03() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static MainFragment03 newInstance() {
        MainFragment03 fragment = new MainFragment03();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment03, container, false);
    }

    @Override
    public void initView() {
        initToolbar(R.id.fragment_main_fragment01_toolbar);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initDate() {

    }
}

package com.xiaoququ.androidFlux.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiaoququ.R;
import com.xiaoququ.androidFlux.view.base.BaseFragment;

public class LargePictureFragment extends BaseFragment implements View.OnTouchListener {
    private View mView;

    // 用该方式传递数据防止重建Fragment时数据丢失,不再采用构造方法传递参数
    public static Fragment newInstance(String url) {
        LargePictureFragment fragment = new LargePictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_large_picture, container, false);
        initView();
        setListener();
        initDate();
        return mView;
    }

    @Override
    public void initView() {
        // 图片浏览缩放控件
        PhotoView imageView = (PhotoView) mView.findViewById(R.id.lpf_img);
        // 启用图片缩放功能
        imageView.enable();
        // Glide加载图片(可直接加载GIF图片),就不采用上面注释的代码来加载图片喽 PS:如果是后台线程加载缓存资源的话，为了避免内存泄露最好使用Application上下文
        Glide.with(getActivity().getApplicationContext()).load(getArguments().getString("url")).placeholder(R.mipmap.loading).error(R.mipmap.loading_error).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    @Override
    public void setListener() {
        // 拦截触摸事件，防止泄露下去
        mView.setOnTouchListener(this);
        // xml布局中该控件放到最后面,以防止不能触发点击事件(图片放大界面暂时不用返回按钮)
        /*
        mView.findViewById(R.id.lpf_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 移除当前Fragment 下列两种方式都行
                //getFragmentManager().beginTransaction().remove(LargePictureFragment.this).commit();
                getFragmentManager().popBackStack(); // getSupportFragmentManager().popBackStack(); // v4包
            }
        });*/
    }

    @Override
    public void initDate() {

    }

    // onTouch事件 将上层的触摸事件拦截,防止点击到Fragment下面的View
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

}

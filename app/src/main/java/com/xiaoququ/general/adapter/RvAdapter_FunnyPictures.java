package com.xiaoququ.general.adapter;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.xiaoququ.R;
import com.xiaoququ.androidFlux.view.activity.MainActivity;
import com.xiaoququ.androidFlux.view.fragment.LargePictureFragment;
import com.xiaoququ.general.bean.FunnyPicturesBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 趣图的适配器
 *
 * Created by LiZhiHui on 2016/11/18 14:00
 */

public class RvAdapter_FunnyPictures extends CommonAdapter implements  View.OnClickListener{

    public RvAdapter_FunnyPictures(Context context, int layoutId, List data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, Object o, int position) { // 查看源码了解到：Object对象对应List集合中的泛型对象
        final FunnyPicturesBean.ResultBean resultBean = (FunnyPicturesBean.ResultBean) o;
        // SuperTextView
        SuperTextView stv = holder.getView(R.id.ntb_item_list_fmf02_super_tv);
        stv.setLeftTopString("");
        stv.setLeftBottomString("");
        // 设置图片标题
        holder.setText(R.id.ntb_item_list_fmf02_title, resultBean.getTitle());
        // ImageView
        final ImageView imageView = holder.getView(R.id.ntb_item_list_fmf02_iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 子类可以自动隐式转换成父类,父类不能直接强转成子类,除非父类是直接被该子类new出来的那倒可以强转
                FragmentTransaction fragmentTransaction = ((FragmentActivity)mContext).getFragmentManager().beginTransaction();
                /**
                 * 设置自定义转场动画
                 *
                 * 因为要用到属性动画 所以用app包的Fragment而不采用v4包的Fragment
                 * PS:app包的Fragment对应的动画类型是Property Animation
                 *     v4包的Fragment对应的动画类型是View Animation
                 */
                fragmentTransaction.setCustomAnimations(R.animator.scaley_enter, R.animator.scaley_exit, R.animator.scaley_enter, R.animator.scaley_exit);
                fragmentTransaction.add(R.id.activity_main, LargePictureFragment.newInstance(resultBean.getUrl()));
                // 加到回退栈(也可以理解为我对此次事务感到不满意,按返回键可以反悔从而回到提交事务前的状态;这里是指回到add前的状态)
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        // Glide加载图片(可直接加载GIF图片),就不采用上面注释的代码来加载图片喽 PS:如果是后台线程加载缓存资源的话，为了避免内存泄露最好使用Application上下文
        Glide.with(mContext.getApplicationContext())
                .load(resultBean.getUrl())
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loading_error)
                .crossFade()
                // 想要ImageView的宽高根据图片资源的大小而定,使用下面这行代码或者使用dontTransform()方法
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
        // 趣段内容下方笑脸哭脸等
        // 趣段内容下方笑脸哭脸等
        holder.setText(R.id.ntb_item_list_fmf02_praise, resultBean.getFp_praiseNums()); // String.valueOf()只创建一个对象,int值 + ""则会创建两个对象
        holder.setText(R.id.ntb_item_list_fmf02_tread, resultBean.getFp_treadNums());
        holder.setText(R.id.ntb_item_list_fmf02_comments, resultBean.getFp_commentsNums());
        holder.setText(R.id.ntb_item_list_fmf02_share, resultBean.getFp_shareNums());
        holder.getView(R.id.ntb_item_list_fmf02_praise).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf02_tread).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf02_comments).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf02_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ntb_item_list_fmf02_praise:
                break;

            case R.id.ntb_item_list_fmf02_tread:
                break;

            case R.id.ntb_item_list_fmf02_comments:
                break;

            case R.id.ntb_item_list_fmf02_share:
                ((MainActivity)mContext).showShare();
                break;

            default:
                break;
        }
    }
}

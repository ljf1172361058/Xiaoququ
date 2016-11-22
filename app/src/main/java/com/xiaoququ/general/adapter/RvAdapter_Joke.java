package com.xiaoququ.general.adapter;

import android.content.Context;
import android.view.View;

import com.allen.library.SuperTextView;
import com.xiaoququ.R;
import com.xiaoququ.androidFlux.view.activity.MainActivity;
import com.xiaoququ.general.bean.JokeBean;
import com.xiaoququ.general.utils.StringUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 趣段的适配器
 *
 * Created by LiZhiHui on 2016/11/18 13:51.
 */

public class RvAdapter_Joke extends CommonAdapter implements View.OnClickListener{

    public RvAdapter_Joke(Context context, int layoutId, List data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, Object o, int position) { // 查看源码了解到：Object对象对应List集合中的泛型对象
        JokeBean.ResultBean resultBean = (JokeBean.ResultBean) o;
        // SuperTextView
        SuperTextView stv = holder.getView(R.id.ntb_item_list_fmf01_super_tv);
        stv.setLeftTopString("");
        stv.setLeftBottomString("");
        // 设置趣段内容
        holder.setText(R.id.ntb_item_list_fmf01_content, StringUtils.replaceBlank(resultBean.getContent())); // 会将数据中的多余的连续空格替换为一个空格,方便用户阅读
        // 趣段内容下方笑脸哭脸等
        holder.setText(R.id.ntb_item_list_fmf01_praise, resultBean.getJoke_praiseNums()); // String.valueOf()只创建一个对象,int值 + ""则会创建两个对象
        holder.setText(R.id.ntb_item_list_fmf01_tread, resultBean.getJoke_treadNums());
        holder.setText(R.id.ntb_item_list_fmf01_comments, resultBean.getJoke_commentsNums());
        holder.setText(R.id.ntb_item_list_fmf01_share, resultBean.getJoke_shareNums());
        holder.getView(R.id.ntb_item_list_fmf01_praise).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf01_tread).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf01_comments).setOnClickListener(this);
        holder.getView(R.id.ntb_item_list_fmf01_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ntb_item_list_fmf01_praise:
                break;

            case R.id.ntb_item_list_fmf01_tread:
                break;

            case R.id.ntb_item_list_fmf01_comments:
                break;

            case R.id.ntb_item_list_fmf01_share:
                ((MainActivity)mContext).showShare();
                break;

            default:
                break;
        }
    }
}

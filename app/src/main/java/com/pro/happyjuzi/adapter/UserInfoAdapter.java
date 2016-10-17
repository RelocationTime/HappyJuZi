package com.pro.happyjuzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pro.happyjuzi.R;
import com.pro.happyjuzi.bean.AuthorBeanInfo;
import com.pro.happyjuzi.detail.RecommendDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHodler> {

    public Context context;
    public List<AuthorBeanInfo.DataBean.ListBean> list;

    public UserInfoAdapter(Context context, List<AuthorBeanInfo.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void addList(List<AuthorBeanInfo.DataBean.ListBean> listBeen) {
        if (list != null) {
            list.addAll(listBeen);
            notifyDataSetChanged();
        }
    }
    @Override
    public void onBindViewHolder(final ViewHodler holder, int position) {
        final AuthorBeanInfo.DataBean.ListBean bean = list.get(position);
        holder.info_content.setText("\t\t"+bean.getTitle());
        holder.info_readnum.setText(bean.getReadnum()+"");
        holder.info_type_txt.setText(bean.getCat().getName());
        ((View) holder.ic_load_more_pic.getParent().getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecommendDetailActivity.class);
                intent.putExtra("id", bean.getId());
                context.startActivity(intent);

            }
        });
        if (bean.getCat().getIcon() != null) {
            RoundingParams params = RoundingParams.fromCornersRadius(7);
            params.setRoundAsCircle(true);
            holder.info_type_img.getHierarchy().setRoundingParams(params);
            holder.info_type_img.setImageURI(bean.getCat().getIcon());
        }
        if (bean.getPic() != null) {
            holder.ic_load_more_pic.setImageURI(bean.getPic());
        }

    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_item, parent, false);
        return new ViewHodler(view);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0: list.size();
    }

    public static class ViewHodler extends RecyclerView.ViewHolder {

        public TextView info_content;
        public SimpleDraweeView info_type_img;
        public TextView info_type_txt;
        public TextView info_readnum;
        public SimpleDraweeView ic_load_more_pic;
        public RelativeLayout info_bg;

        public ViewHodler(View itemView) {
            super(itemView);
            info_content = ((TextView) itemView.findViewById(R.id.info_content));
            info_type_img = ((SimpleDraweeView) itemView.findViewById(R.id.info_type_img));
            info_type_txt = ((TextView) itemView.findViewById(R.id.info_type_txt));
            info_readnum = ((TextView) itemView.findViewById(R.id.info_readnum));
            ic_load_more_pic = ((SimpleDraweeView) itemView.findViewById(R.id.info_img));
            info_bg = ((RelativeLayout) itemView.findViewById(R.id.info_bg));
        }
    }


}

package com.pro.happyjuzi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pro.happyjuzi.R;
import com.pro.happyjuzi.bean.BaseBean;
import com.pro.happyjuzi.bean.InfoBean;
import com.pro.happyjuzi.bean.LuBean;
import com.pro.happyjuzi.bean.ReBean;
import com.pro.happyjuzi.detail.RecommendDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<BaseBean> list;
    private int prePosition = 0;
    private boolean isDragging = false;
    public ViewPager viewpager;
    public Pools.Pool<ImageView> pool = new Pools.SimplePool<>(3);
    public RecyclerView recyclerView;


    public void removeAll() {
        if (list != null) {
            list.clear();
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };

    public ArrayList<String> picUrl;

    public ChannelAdapter(Context context, List<BaseBean> list) {
        this.context = context;
        this.list = new ArrayList<BaseBean>();
    }

    public void addAll(List<BaseBean> mList) {
        if (list != null) {
            list.addAll(mList);
            notifyDataSetChanged();
        }
    }

    private static final String TAG = "ChannelAdapter";
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final BaseBean bean = list.get(position);
        final MyViewHolder viewHolder = (MyViewHolder) holder;

        if (bean instanceof InfoBean) {
            header_init((MyViewHolder) holder, (InfoBean) bean, viewHolder);
            return;
        } else {
            if (bean instanceof ReBean) {

                final ReBean reBean = (ReBean) bean;
                if ((reBean.getType() == 0 || reBean.getType() == 6) && reBean.getDisplay() == 0) {
                    View rl = (View) ((MyViewHolder) holder).re_title.getParent();
                    rl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, RecommendDetailActivity.class);
                            intent.putExtra("id", reBean.getId());
                            context.startActivity(intent);

                        }
                    });
                    if (reBean != null && reBean.getCat() != null) {
                        viewHolder.re_title.setText(reBean.getTitle());
                        viewHolder.re_cat.setText(""+ reBean.getCat().getName());
                        if (reBean.getCat().getIcon() != null) {
                            viewHolder.re_icon.setImageURI(reBean.getCat().getIcon());
                        }
                        if (reBean.getPic() != null) {
                            PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
                            builder.setUri(Uri.parse(reBean.getPic()));
                            builder.setAutoPlayAnimations(true);
                            viewHolder.re_pic.setController(builder.build());
                        }
                        viewHolder.re_readnum.setText(reBean.getReadnum()+"");

                    }
                    if (viewHolder.re_del != null) {
                        viewHolder.re_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                View view = (View) v.getParent().getParent().getParent().getParent();
                                int layoutPosition = recyclerView.getChildLayoutPosition(view);
                                createDialog(layoutPosition);

                            }
                        });
                    }
                    return;
                } else {

                    if (reBean.getCat() != null) {
                        viewHolder.re_ud_cat_name.setText(reBean.getCat().getName()+"");
                        if (reBean.getCat().getIcon() != null) {
                            viewHolder.re_ud_icon.setImageURI(reBean.getCat().getIcon());
                        }
                    }

                    if (reBean.getPic() != null) {
                        viewHolder.re_ud_pic.setImageURI(reBean.getPic());
                    }

                    ((View) viewHolder.re_ud_title.getParent().getParent()).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, RecommendDetailActivity.class);
                            intent.putExtra("id", reBean.getId());
                            context.startActivity(intent);

                        }
                    });
                    viewHolder.re_ud_readnum.setText(reBean.getReadnum()+"");
                    viewHolder.re_ud_title.setText(reBean.getTitle()+"");
                    viewHolder.ud_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View view = (View) v.getParent().getParent().getParent();
                            int layoutPosition = recyclerView.getChildLayoutPosition(view);
                            createDialog(layoutPosition);

                        }
                    });
                    return;
                }
            }
        }
    }




    private void createDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_not__interest, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialogWindowAnim);
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams params = window.getAttributes();
        dialog.setCanceledOnTouchOutside(false);

        view.findViewById(R.id.de_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + position );
                list.remove(position);
                notifyItemRemoved(position);
                dialog.cancel();
            }
        });

        view.findViewById(R.id.de_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    public void removePosition(int prePosition) {
        list.remove(prePosition);
        notifyItemRemoved(prePosition);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    private void header_init(final MyViewHolder holder, InfoBean bean, MyViewHolder viewHolder) {
        final List<ImageView> imageViews = new ArrayList<ImageView>();

        final InfoBean infoBean = bean;
        List<LuBean> Lulist = infoBean.getInfo();
        viewpager = holder.viewpager;
        holder.ll_point_group.removeAllViews();
        final ArrayList<String> imageDesc = new ArrayList<>();
        picUrl = new ArrayList<>();
        for (int i = 0; i < Lulist.size(); i++) {
            LuBean luBean = Lulist.get(i);
            String title = luBean.getTitle();
            imageDesc.add(title);
            ImageView imageView = new ImageView(context);
//                imageView.setImageResource(R.mipmap.ic_launcher);
//                Picasso.with(context).load(luBean.getPic()).into(imageView);
            picUrl.add(luBean.getPic());
            imageViews.add(imageView);
            ImageView point = new ImageView(context);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            if(i==0){
                point.setEnabled(true);//显示红色
            }else{
                point.setEnabled(false);//显示灰色
                params.leftMargin = 8;
            }
            point.setLayoutParams(params);
            holder.ll_point_group.addView(point);
        }
        holder.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = position%imageViews.size();
                holder.tv_title.setText(imageDesc.get(realPosition));
                if (holder.ll_point_group != null) {
                    holder.ll_point_group.getChildAt(prePosition).setEnabled(false);
                    holder.ll_point_group.getChildAt(realPosition).setEnabled(true);
                }
                prePosition = realPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    isDragging = true;
                    handler.removeCallbacksAndMessages(null);
                }else if(state == ViewPager.SCROLL_STATE_SETTLING){

                }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                    isDragging = false;
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(0,4000);
                }

            }
        });
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();
        holder.viewpager.setCurrentItem(item);
        holder.tv_title.setText(imageDesc.get(prePosition));
        if (picUrl != null && picUrl.size() == 1) {
            handler.removeCallbacksAndMessages(null);
        }
        handler.sendEmptyMessageDelayed(0,3000);
        viewHolder.viewpager.setOffscreenPageLimit(5);
        viewpager.setOffscreenPageLimit(5);
        viewHolder.viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int realPosition = position % imageViews.size();
//                    final ImageView imageView =  imageViews.get(realPosition);
                ImageView imageView = pool.acquire();
                if (imageView != null) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    container.addView(imageView);
                } else {
                    imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.with(context).load(picUrl.get(realPosition)).into(imageView);
                    container.addView(imageView);
                }

                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN://手指按下
                                handler.removeCallbacksAndMessages(null);
                                break;
                            case MotionEvent.ACTION_MOVE://手指在这个控件上移动
                                break;
                            case MotionEvent.ACTION_CANCEL://手指在这个控件上移动
                                break;
                            case MotionEvent.ACTION_UP://手指离开
                                handler.removeCallbacksAndMessages(null);
                                handler.sendEmptyMessageDelayed(0,4000);
                                break;
                        }
                        return false;
                    }
                });
                imageView.setTag(position);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag()%imageViews.size();
                        String text = imageDesc.get(position);
                        Intent intent = new Intent(context, RecommendDetailActivity.class);
                        intent.putExtra("id", infoBean.getInfo().get(position).getId());
                        context.startActivity(intent);

                    }
                });
                return imageView;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object == view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ImageView view = (ImageView) object;
                pool.release(view);
                container.removeView((View) object);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        BaseBean bean = list.get(position);
        if (bean instanceof ReBean) {
            ReBean reBean = (ReBean) bean;
            if ((reBean.getType() == 0 || reBean.getType() == 6) && reBean.getDisplay() == 0) {
                return R.layout.recommend_item_lr;
            } else {
                return R.layout.recommend_item_ud;
            }
        } else {
                return R.layout.recommend_header;
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ViewPager viewpager;
        private TextView tv_title;
        private LinearLayout ll_point_group;
        public TextView re_title;
        public SimpleDraweeView re_icon;
        public TextView re_cat;
        public TextView re_readnum;
        public SimpleDraweeView re_pic;
        public SimpleDraweeView re_ud_pic;
        public TextView re_ud_title;
        public SimpleDraweeView re_ud_icon;
        public TextView textView;
        public TextView re_ud_cat_name;
        public TextView re_ud_readnum;
        public ImageView re_del;
        public ImageView ud_del;

        public MyViewHolder(View itemView) {
            super(itemView);
            viewpager = (ViewPager) itemView.findViewById(R.id.viewpager);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            ll_point_group = (LinearLayout) itemView.findViewById(R.id.ll_point_group);
            re_title = ((TextView) itemView.findViewById(R.id.re_title));
            re_icon = ((SimpleDraweeView) itemView.findViewById(R.id.re_icon));
            re_cat = ((TextView) itemView.findViewById(R.id.re_cat));
            re_readnum = ((TextView) itemView.findViewById(R.id.re_readnum));
            re_pic = ((SimpleDraweeView) itemView.findViewById(R.id.re_pic));
            re_ud_pic = ((SimpleDraweeView) itemView.findViewById(R.id.re_ud_pic));
            re_ud_title = ((TextView) itemView.findViewById(R.id.re_ud_title));
            re_ud_icon = ((SimpleDraweeView) itemView.findViewById(R.id.re_ud_icon));
            re_ud_cat_name = textView = ((TextView) itemView.findViewById(R.id.re_ud_cat_name));
            re_ud_readnum = ((TextView) itemView.findViewById(R.id.re_ud_readnum));
            re_del = ((ImageView) itemView.findViewById(R.id.re_del));
            ud_del = ((ImageView) itemView.findViewById(R.id.ud_del));
        }
    }

}
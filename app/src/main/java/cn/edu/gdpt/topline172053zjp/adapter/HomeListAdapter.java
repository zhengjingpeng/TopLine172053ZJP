package cn.edu.gdpt.topline172053zjp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.gdpt.topline172053zjp.R;
import cn.edu.gdpt.topline172053zjp.activity.NewsDetailActivity;
import cn.edu.gdpt.topline172053zjp.bean.NewsBean;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsBean> newsList;
    private static final int TYPE_ONE = 1; //一个图片的样式
    private static final int TYPE_TWO = 2; //三个图片的样式
    private Context context;
    public HomeListAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<NewsBean> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_TWO) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.home_item_two, viewGroup, false);
            TypeTwoViewHolder viewHolder = new TypeTwoViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.home_item_one, viewGroup, false);
            TypeOneViewHolder viewHolder = new TypeOneViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (newsList == null) return;
        final NewsBean bean = newsList.get(i);
        if (holder instanceof TypeOneViewHolder) {
            ((TypeOneViewHolder) holder).tv_name.setText(bean.getNewsName());
            ((TypeOneViewHolder) holder).tv_news_type_name.setText(
                    bean.getNewsTypeName());
            Glide
                    .with(context)
                    .load(bean.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeOneViewHolder) holder).iv_img);
        } else if (holder instanceof TypeTwoViewHolder) {
            ((TypeTwoViewHolder) holder).tv_name.setText(bean.getNewsName());
            ((TypeTwoViewHolder) holder).tv_news_type_name.setText(
                    bean.getNewsTypeName());
            Glide
                    .with(context)
                    .load(bean.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) holder).iv_img1);
            Glide
                    .with(context)
                    .load(bean.getImg2())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) holder).iv_img2);
            Glide
                    .with(context)
                    .load(bean.getImg3())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) holder).iv_img3);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("newsBean", bean);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        if (1 == newsList.get(position).getType()) {
            return TYPE_ONE; //一个图片的类型
        } else if (2 == newsList.get(position).getType()) {
            return TYPE_TWO; //三个图片的类型
        } else {
            return TYPE_ONE;
        }

    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }
    public class TypeOneViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_news_type_name;
        public ImageView iv_img;
        public TypeOneViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_news_type_name = (TextView) itemView.findViewById(R.id.tv_newsType_name);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
    public class TypeTwoViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_news_type_name;
        public ImageView iv_img1, iv_img2, iv_img3;
        public TypeTwoViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_news_type_name = (TextView) itemView.findViewById(R.id.
                    tv_newsType_name);
            iv_img1 = (ImageView) itemView.findViewById(R.id.iv_img1);
            iv_img2 = (ImageView) itemView.findViewById(R.id.iv_img2);
            iv_img3 = (ImageView) itemView.findViewById(R.id.iv_img3);
        }
    }
}

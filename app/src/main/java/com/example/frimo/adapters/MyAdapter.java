package com.example.frimo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<String> mList;
    public MyAdapter(Context mContext,List<String> mList){
        this.mContext=mContext;
        this.mList=mList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_normal,parent, false);
            viewHolder mViewHolder = new viewHolder(view);
            return mViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((viewHolder) holder).tv_nickname.setText(mList.get(position));
            ((viewHolder) holder).tv_address.setText("深圳市福田区");
//            ((viewHolder) holder).img_heard_pic.setImageResource(R.mipmap.guide);
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }
    public static  class viewHolder extends RecyclerView.ViewHolder {
       TextView  tv_nickname;
       TextView tv_address;
       ImageView img_heard_pic;

        public viewHolder(View itemView) {
            super(itemView);
            tv_nickname=itemView.findViewById(R.id.tv_nickname);
            tv_address=itemView.findViewById(R.id.tv_address);
            img_heard_pic=itemView.findViewById(R.id.img_heard_pic);
        }
    }
    public static  class OneviewHolder extends RecyclerView.ViewHolder {
        public OneviewHolder(View itemView) {
            super(itemView);
        }
    }
}

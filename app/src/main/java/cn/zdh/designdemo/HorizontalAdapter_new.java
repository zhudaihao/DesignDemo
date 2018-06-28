package cn.zdh.designdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Administrator on 2017/4/27.
 */

//竞猜上面的item
public class HorizontalAdapter_new extends RecyclerView.Adapter<HorizontalAdapter_new.ViewHolder> {

    private List<String> mList;
    public HorizontalAdapter_new(List<String> mList) {
        this.mList = mList;

    }

    //设置数据 需要个获取选中那个item
    private int positions = 0;
    public void setList(List<String> mList, int positions) {
        this.positions = positions;
        this.mList = mList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horizontal, viewGroup, false);

        ViewHolder vh = new ViewHolder(inflate);
        vh.text = (TextView) inflate.findViewById(R.id.item_horizontaltext);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // 设置数据
        holder.text.setText(mList.get(position));

        //设置item选中状态
        if (position == positions) {
            holder.text.setSelected(true);
        } else {
            holder.text.setSelected(false);
        }


        //回调监听
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);

                }
            });

        }
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    /**
     * 布局
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
package com.whartonjason.alipay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WhartonJason on 2018/7/12.
 * <p>
 * 处理支付通道信息的Adapter
 */
public class ChannelSelectAdapter extends RecyclerView.Adapter<ChannelSelectAdapter.ChannelSelectViewHolder> implements View.OnClickListener {

    private Context context;

    private List<PayChannel> mItemList;

    private LayoutInflater inflater;

    ChannelSelectAdapter(Context context, List<PayChannel> list) {
        this.context = context;
        this.mItemList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ChannelSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dialog_pay_channel_recycler_item, parent, false);
        view.setOnClickListener(this);
        return new ChannelSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChannelSelectViewHolder holder, int position) {
        holder.itemView.setTag(holder.getAdapterPosition());
        PayChannel channel = mItemList.get(position);
        holder.ivChannelSelectItemLogo.setImageResource(channel.getChannelSrc());
        holder.tvChannelSelectItemName.setText(channel.getChannelName());
        holder.cbChannelSelectItem.setChecked(channel.isCheck());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public void onClick(View view) {
        refreshChannelCheckStatus((Integer) view.getTag());
        if (channelItemListener != null) {
            channelItemListener.onClickChannelItem(view, (Integer) view.getTag());
        }
    }

    /**
     * 刷新支付通道选择状态
     */
    private void refreshChannelCheckStatus(int position) {
        for (int i = 0; i < mItemList.size(); i++) {
            if (position != i) {
                mItemList.get(i).setCheck(false);
            }else{
                mItemList.get(i).setCheck(true);
            }
        }
        notifyDataSetChanged();
    }

    PayChannel getItemInfo(int position) {
        return mItemList.get(position);
    }

    class ChannelSelectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_channel_select_item_logo)
        ImageView ivChannelSelectItemLogo;
        @BindView(R.id.tv_channel_select_item_name)
        TextView tvChannelSelectItemName;
        @BindView(R.id.cb_channel_select_item)
        CheckBox cbChannelSelectItem;

        ChannelSelectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnClickChannelItemListener channelItemListener;

    public interface OnClickChannelItemListener {
        void onClickChannelItem(View view, int position);
    }

    public void setonClickChannelItemListener(OnClickChannelItemListener listener) {
        this.channelItemListener = listener;
    }

}

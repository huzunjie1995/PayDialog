package com.whartonjason.alipay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PayDialogFragment extends DialogFragment implements ChannelSelectAdapter.OnClickChannelItemListener {

    @BindView(R.id.rl_confirm_layout)
    RelativeLayout rlDialogConfirm;
    @BindView(R.id.rl_channel_layout)
    RelativeLayout rlDialogChannel;
    @BindView(R.id.tv_confirm_order_money)
    TextView tvConfirmOrderMoney;
    @BindView(R.id.rlv_channel_pay)
    RecyclerView rlvChannelPay;
    @BindView(R.id.tv_confirm_pay_channel_name)
    TextView tvConfirmPayChannelName;
    @BindView(R.id.iv_confirm_pay_channel_logo)
    ImageView ivConfirmPayChannelLogo;

    Unbinder unbinder;
    /**
     * 处理支付通道信息的Adapter
     */
    ChannelSelectAdapter adapter;

    public static PayDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        PayDialogFragment dialogFragment = new PayDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(mActivity, R.style.Fragment_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fragment_pay);
        unbinder = ButterKnife.bind(this, dialog);
        initDialogAttribute(dialog);
        initViewDefault();
        return dialog;
    }

    /**
     * 初始化默认信息
     */
    private void initViewDefault() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rlvChannelPay.setLayoutManager(manager);
        List<PayChannel> mItemList = new ArrayList<>();
        mItemList.add(new PayChannel("支付宝", R.drawable.pay_channel_ali, true));
        mItemList.add(new PayChannel("微信", R.drawable.pay_channel_wx, false));
        adapter = new ChannelSelectAdapter(mActivity, mItemList);
        rlvChannelPay.setAdapter(adapter);
        adapter.setonClickChannelItemListener(this);
    }

    private void initDialogAttribute(Dialog dialog) {
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setWindowAnimations(R.style.Animation_Bottom);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    @OnClick(R.id.rl_confirm_pay_channel)
    public void onClickChannel() {
        rlDialogConfirm.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.out_to_left));
        rlDialogConfirm.setVisibility(View.GONE);
        rlDialogChannel.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.in_from_right));
        rlDialogChannel.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_dialog_dismiss)
    public void onClickBack() {
        dismiss();
    }

    @OnClick(R.id.iv_channel_pay_back)
    public void onClickChannelBack() {
        rlDialogConfirm.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.in_from_left));
        rlDialogConfirm.setVisibility(View.VISIBLE);
        rlDialogChannel.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.out_to_right));
        rlDialogChannel.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onClickChannelItem(View view, int position) {
        PayChannel channel = adapter.getItemInfo(position);
        ivConfirmPayChannelLogo.setImageResource(channel.getChannelSrc());
        tvConfirmPayChannelName.setText(channel.getChannelName());
        //滑动动画
        rlDialogConfirm.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.in_from_left));
        rlDialogConfirm.setVisibility(View.VISIBLE);
        rlDialogChannel.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.out_to_right));
        rlDialogChannel.setVisibility(View.GONE);
    }

}

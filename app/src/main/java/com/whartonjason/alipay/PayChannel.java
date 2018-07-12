package com.whartonjason.alipay;

public class PayChannel {

    private String channelName;

    private int channelSrc;

    private boolean isCheck;

    public PayChannel(String channelName, int channelSrc, boolean select) {
        this.channelName = channelName;
        this.channelSrc = channelSrc;
        this.isCheck = select;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelSrc() {
        return channelSrc;
    }

    public void setChannelSrc(int channelSrc) {
        this.channelSrc = channelSrc;
    }

}

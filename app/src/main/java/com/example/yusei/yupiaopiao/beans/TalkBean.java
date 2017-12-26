package com.example.yusei.yupiaopiao.beans;

/**
 * Created by yusei on 2017/12/4
 */
public class TalkBean {

    public TalkBean(String content, int imageId, boolean isAsk) {
        super();
        this.content = content;
        this.imageId = imageId;
        this.isAsk = isAsk;
    }

    public String content;
    public int imageId;
    public boolean isAsk;//是否是提问
}

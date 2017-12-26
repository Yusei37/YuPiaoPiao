package com.example.yusei.yupiaopiao.interfaces;

import com.example.yusei.yupiaopiao.beans.CommonResponse;

/**
 * Created by yusei on 2017/12/19
 */
public interface ResponseHandler {

    /**
     * 交易成功的处理
     * @param response 格式化报文
     */
    void success(CommonResponse response);

    /**
     * 报文通信正常，但交易内容失败的处理
     * @param failCode 返回的交易状态码
     * @param failMsg 返回的交易失败说明
     */
    void fail(String failCode, String failMsg);
}

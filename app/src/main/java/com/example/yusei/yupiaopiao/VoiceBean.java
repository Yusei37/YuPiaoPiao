package com.example.yusei.yupiaopiao;

import java.util.ArrayList;

/**
 * Created by yusei on 2017/12/4
 */
public class VoiceBean {

    public ArrayList<WS> ws;

    public class WS {
        public ArrayList<CW> cw;
    }

    public class CW {
        public String w;
    }
}

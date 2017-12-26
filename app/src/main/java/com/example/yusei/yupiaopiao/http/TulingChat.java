package com.example.yusei.yupiaopiao.http;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by yusei on 2017/12/8
 */
public class TulingChat {

    private String code;
    private String text = "";
    private String apiKEY = "d2a63ff42d60465dae93ed1638ad3489";
    private String getURL = "http://www.tuling123.com/openapi/api?key=" + apiKEY + "&info=";

    public TulingChat(){

    }

    public void chat(String quest){
        final String question = quest;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String INFO = URLEncoder.encode(question, "utf-8");
                    getURL += INFO;
                    URL getUrl = new URL(getURL);
                    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
                    connection.connect();

                    // 取得输入流，并使用Reader读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
                    StringBuffer sb = new StringBuffer();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                    // 断开连接
                    connection.disconnect();
                    JSONObject result = new JSONObject(sb.toString());
                    code = result.getString("code");
                    text = result.getString("text");
                } catch (Exception e) {
                    text = "语音机器人出现了一点问题";
                }
            }
        }).start();
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}

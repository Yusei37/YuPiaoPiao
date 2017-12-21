package com.example.yusei.yupiaopiao;

import android.os.AsyncTask;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by yusei on 2017/12/19
 */
public class HttpPostTask extends AsyncTask<String, String, String> {

    private ResponseHandler rHandler;
    private CommonRequest request;

    public HttpPostTask(CommonRequest request,ResponseHandler rHandler) {
        this.request = request;
        this.rHandler = rHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder resultBuf = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            // 第一步：使用URL打开一个HttpURLConnection连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 第二步：设置HttpURLConnection连接相关属性
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”
            connection.setConnectTimeout(8000); // 设置连接建立的超时时间
            connection.setReadTimeout(8000); // 设置网络报文收发超时时间
            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(request.getJsonStr().getBytes("utf-8"));
            out.flush();

            InputStream in = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                resultBuf.append(line);
            }

            return resultBuf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBuf.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if (rHandler != null) {
            if (!"".equals(result)) {

                CommonResponse response = new CommonResponse(result);
                // 这里response.getResCode()为多少表示业务完成也是和服务器约定好的
                if ("0".equals(response.getResCode())) { // 正确
                    rHandler.success(response);
                } else {
                    rHandler.fail(response.getResCode(), response.getResMsg());
                }
            }
        }
    }

}

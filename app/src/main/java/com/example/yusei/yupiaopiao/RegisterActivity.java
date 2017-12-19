package com.example.yusei.yupiaopiao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt_phoneNumber;
    private EditText edt_password;
    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_phoneNumber = (EditText) findViewById(R.id.edt_reg_phoneNumber);
        edt_password = (EditText) findViewById(R.id.edt_reg_password);
        edt_name = (EditText) findViewById(R.id.edt_reg_name);
        edt_email = (EditText) findViewById(R.id.edt_reg_email);
        edt_city = (EditText) findViewById(R.id.edt_reg_city);
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        String text = "* 注册及视为同意 yupiaopiao注册协议及版权声明";
        SpannableString spannableText = new SpannableString(text);
        spannableText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "注册协议", Toast.LENGTH_SHORT).show();
            }
        }, 10, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv_protocol = (TextView) findViewById(R.id.tv_protocol);
        tv_protocol.setText(spannableText);
        tv_protocol.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Toast.makeText(MyApplication.getContext(), "注册中...",Toast.LENGTH_SHORT).show();
                CommonRequest request = new CommonRequest();
                request.addRequestParam("PhoneNumber", edt_phoneNumber.getText().toString());
                request.addRequestParam("Password", edt_password.getText().toString());
                request.addRequestParam("CustomerName", edt_name.getText().toString());
                request.addRequestParam("CustomerEmail", edt_email.getText().toString());
                request.addRequestParam("City", edt_city.getText().toString());
                register(request);
                break;
        }
    }

    private void register(CommonRequest request) {
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(MyApplication.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                Customer currentLoginCustomer = new Customer();
                currentLoginCustomer.setPhoneNumber(edt_phoneNumber.getText().toString());
                currentLoginCustomer.setPassword(edt_password.getText().toString());
                currentLoginCustomer.setCustomerName(edt_name.getText().toString());
                currentLoginCustomer.setCustomerEmail(edt_email.getText().toString());
                currentLoginCustomer.setSex("");
                currentLoginCustomer.setCity(edt_city.getText().toString());
                currentLoginCustomer.setRegisterDate(new java.util.Date());
                currentLoginCustomer.setVIPLevel(0);
                Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                intent.putExtra("Customer", currentLoginCustomer);
                startActivity(intent);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/RegisterServlet");
    }

//    private void register(final CommonRequest request) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //   String result = null;
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    URL url = new URL("http://10.0.2.2:8080/ServletTest/RegisterServlet");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//                    connection.setRequestMethod("POST");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
////            JSONObject object = new JSONObject();
////            object.put("requestCode",100);
////            HashMap<String, String> requestParam = new HashMap<>();
////                    requestParam.put("PhoneNumber","123");
////                    requestParam.put("Password","123");
////            JSONObject param = new JSONObject(requestParam);
////            object.put("requestParam", param);
//                    out.writeBytes(request.getJsonStr());
//                    out.flush();
//
//                    InputStream in = connection.getInputStream();
//
//                    reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    showResponse(response.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
//
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MyApplication.getContext(), response,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}

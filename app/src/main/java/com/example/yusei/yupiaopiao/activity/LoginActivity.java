package com.example.yusei.yupiaopiao.activity;

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
import com.example.yusei.yupiaopiao.*;
import com.example.yusei.yupiaopiao.beans.CommonRequest;
import com.example.yusei.yupiaopiao.beans.CommonResponse;
import com.example.yusei.yupiaopiao.beans.Customer;
import com.example.yusei.yupiaopiao.constant.IP;
import com.example.yusei.yupiaopiao.constant.MyApplication;
import com.example.yusei.yupiaopiao.http.HttpPostTask;
import com.example.yusei.yupiaopiao.interfaces.ResponseHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNumber;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        TextView newRegister = (TextView) findViewById(R.id.newRegister);
        String forgotPasswordText = "忘记密码";
        String newRegisterText = "新用户注册";
        SpannableString spannableForgotPasswordText = new SpannableString(forgotPasswordText);
        spannableForgotPasswordText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
            }
        }, 0, forgotPasswordText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString spannableNewRegisterText = new SpannableString(newRegisterText);
        spannableNewRegisterText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }, 0, newRegisterText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgotPassword.setText(spannableForgotPasswordText);
        forgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
        newRegister.setText(spannableNewRegisterText);
        newRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Toast.makeText(MyApplication.getContext(), "登录中...",Toast.LENGTH_SHORT).show();
                CommonRequest request = new CommonRequest();
                request.addRequestParam("PhoneNumber", phoneNumber.getText().toString());
                request.addRequestParam("Password", password.getText().toString());
                login(request);
                break;
        }
    }

    private void login(CommonRequest request) {
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(MyApplication.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                Customer currentLoginCustomer = new Customer();
                currentLoginCustomer.setPhoneNumber(response.getPropertyMap().get("PhoneNumber"));
                currentLoginCustomer.setPassword(response.getPropertyMap().get("Password"));
                currentLoginCustomer.setCustomerName(response.getPropertyMap().get("CustomerName"));
                currentLoginCustomer.setSex(response.getPropertyMap().get("Sex"));
                currentLoginCustomer.setCustomerEmail(response.getPropertyMap().get("CustomerEmail"));
                currentLoginCustomer.setCity(response.getPropertyMap().get("City"));
                String date = response.getPropertyMap().get("RegisterDate");
                currentLoginCustomer.setRegisterDate(new java.util.Date(Long.valueOf(date).longValue()));
                currentLoginCustomer.setVIPLevel(Integer.valueOf(response.getPropertyMap().get("VIPLevel")));
                Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                intent.putExtra("Customer", currentLoginCustomer);
                startActivity(intent);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute(IP.IP+"/ServletTest/LoginServlet");
    }

//    private void login(final CommonRequest request) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //   String result = null;
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    URL url = new URL("http://10.0.2.2:8080/ServletTest/LoginServlet");
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

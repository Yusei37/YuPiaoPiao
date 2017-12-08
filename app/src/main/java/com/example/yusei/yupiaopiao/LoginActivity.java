package com.example.yusei.yupiaopiao;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNumber;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

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
                Toast.makeText(LoginActivity.this, "新用户注册", Toast.LENGTH_SHORT).show();
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
            case R.id.login:
                break;
        }
    }
}

package cn.zdh.designdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/27.
 */

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button button;
    ProgressBar login_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.email_sign_in_button);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_progress = (ProgressBar) findViewById(R.id.login_progress);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();

            }
        });
    }



    private void setData() {
        if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(LoginActivity.this, "账户不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        button.setVisibility(View.GONE);
        login_progress.setVisibility(View.VISIBLE);
    }
}

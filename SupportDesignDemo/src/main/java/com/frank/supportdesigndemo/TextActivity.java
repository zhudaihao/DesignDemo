package com.frank.supportdesigndemo;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v, 9);
            }
        });
    }

}


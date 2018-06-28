package cn.zdh.designdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/27.
 */

public class SnackBarActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        button = (Button) findViewById(R.id.email_sign_in_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setData();
//                shown1(view);
                show(view);


            }


        });
    }

    private void shown1(View view) {
        Snackbar snackbar = Snackbar
                .make(view, "我就是Snackbar!", Snackbar.LENGTH_LONG)
                //左边按钮监听回调
                .setAction("action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarActivity.this, "我是通过Snackbar弹出来的！", Toast.LENGTH_SHORT).show();
                    }
                })
                //左边按钮颜色
                .setActionTextColor(Color.BLUE)
                //Snackbar显示和隐藏监听
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);

                        Toast.makeText(SnackBarActivity.this, "Snackbar隐藏监听！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        super.onShown(transientBottomBar);
                        Toast.makeText(SnackBarActivity.this, "Snackbar显示", Toast.LENGTH_SHORT).show();
                    }
                });
        //修改颜色
        View _RootView = snackbar.getView();
        TextView _Text = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        Button _Action = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        _RootView.setBackgroundColor(ColorStateList.valueOf(0xFF99CCFF).getDefaultColor());
        _Text.setTextColor(ColorStateList.valueOf(0xFFFFFFFF));
        _Action.setTextColor(ColorStateList.valueOf(0xFFFFFF66));


        snackbar.show();
    }

    private void show(View view) {


        Snackbar snackbar = Snackbar.make(view, "我是从左到右的Snackbar!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View _RootView = snackbar.getView();
        snackbarInAnim(snackbar, _RootView);
        TextView _Text = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        Button _Action = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        _RootView.setBackgroundColor(ColorStateList.valueOf(0xFF99CCFF).getDefaultColor());
        _Text.setTextColor(ColorStateList.valueOf(0xFFFFFFFF));
        _Action.setTextColor(ColorStateList.valueOf(0xFFFFFF66));

        snackbar.show();

    }

    private void snackbarInAnim(final Snackbar pSnackbar, final View pView) {
        Animation animation = AnimationUtils.loadAnimation(pView.getContext(), R.anim.snackbar_in_anim);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                snackbarOutAnim(pSnackbar, pView);
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        pView.startAnimation(animation);
    }

    private void snackbarOutAnim(final Snackbar pSnackbar, final View pView) {
        Animation animation = AnimationUtils.loadAnimation(pView.getContext(), R.anim.snackbar_out_anim);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                pView.setVisibility(View.GONE);
                pSnackbar.dismiss();
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        pView.startAnimation(animation);
    }


}

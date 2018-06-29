package com.frank.supportdesigndemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 *
 */

public class MyBehavior extends Behavior<View> {


    //确定对哪个view操作动画
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll");
        return child instanceof ImageView && nestedScrollAxes == View.SCROLL_AXIS_VERTICAL;
    }


    //view移动方向 offsetTopAndBottom是上下;offsetLeftAndRight是左右
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d(TAG, "onNestedPreScroll  dx:" + dx + " dy:" + dy);
        ViewCompat.offsetTopAndBottom(child, dy);
    }


    //view动画效果 velocityY是滑动速度
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.e(TAG, "------------onNestedPreFling velocityY:" + velocityY + "->>" + child.getHeight());
        if (velocityY > 0) {
            child.animate().scaleX(2.0f).scaleY(2.0f).setDuration(2000).start();
        } else {
            child.animate().scaleX(1.0f).scaleY(1.0f).setDuration(2000).start();
        }

        return false;

    }

    /**
     * ------------------------------------------
     */


    private static final String TAG = "MyBehavior";

    public MyBehavior() {
    }

    //    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        Log.d(TAG, "onDependentViewChanged: " + dependency);
//        float x = child.getX();
//        float y = child.getY();
//
//        int dependTop = dependency.getTop();
//        int dependBottom = dependency.getBottom();
//
//        x = dependency.getX();
//
//        if (child instanceof TextView) {
//            y = dependTop - child.getHeight() - 20;
//        } else {
//            y = dependBottom + 50;
//        }
//
//
//        child.setX(x);
//        child.setY(y);
//
//        return true;
//    }


    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

  /*  @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //return dependency instanceof DependencyView;
        return false;
    }*/


//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        Log.d(TAG,"onNestedScroll:"+dxConsumed+" dy:"+dyConsumed);
//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//    }


//    @Override
//    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
//        Log.d(TAG,"onNestedFling");
//        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
//    }


}

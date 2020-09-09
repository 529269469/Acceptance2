package com.example.acceptance2.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acceptance2.R;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 描述：MVP 基础activity
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    /**
     * contentView
     */
    protected View root;
    /**
     * 代理类
     */
    protected LayoutInflater mInflate;
    private Unbinder mUnBinder = null;
    private ImmersionBar mImmersionBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove saved fragment, will new fragment in mPagerAdapter
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        MyApplication.mContext=this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        root = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mInflate = LayoutInflater.from(this);
        this.setContentView(root);
        mUnBinder = ButterKnife.bind(this);
        initView();
        initDataAndEvent();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                root.setFocusable(true);
                root.setFocusableInTouchMode(true);
                root.requestFocus();
                return false;
            }
        });
    }



    protected abstract void initView();

    /**
     * 获取contentViewId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 获取代理对象
     *
     * @return
     */

    /**
     * 初始化数据和事件
     */
    protected abstract void initDataAndEvent();


    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
        }catch (Exception o){

        }

        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideInputWhenTouchOtherView(this, ev, null);
        return super.dispatchTouchEvent(ev);
    }


    public boolean isTouchView(View view, MotionEvent event) {
        if (view == null || event == null) {
            return false;
        }
        int[] leftTop = {0, 0};
        view.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + view.getHeight();
        int right = left + view.getWidth();
        if (event.getRawX() > left && event.getRawX() < right
                && event.getRawY() > top && event.getRawY() < bottom) {
            return true;
        }
        return false;
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            return !isTouchView(v, event);
        }
        return false;
    }

    public void hideInputWhenTouchOtherView(Activity activity, MotionEvent ev, List<View> excludeViews) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (excludeViews != null && !excludeViews.isEmpty()) {
                for (int i = 0; i < excludeViews.size(); i++) {
                    if (isTouchView(excludeViews.get(i), ev)) {
                        return;
                    }
                }
            }
            View v = activity.getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        }
    }
}

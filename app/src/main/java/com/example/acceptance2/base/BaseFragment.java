package com.example.acceptance2.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.net.Socket;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础fragment
 *
 * @param <P>
 */
public abstract class BaseFragment<P extends IPresenter> extends LazyFragment {
    protected View mRootView;

    private boolean isPrepared;
    private boolean isFirst = true;
    private Unbinder mUnBinder = null;

    // 两次点击按钮之间的点击间隔不能少于500毫秒
    protected static final int MIN_CLICK_DELAY_TIME = 500;
    protected static long lastClickTime;

    public boolean isDoubleClick(){
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            return true;
        }
        lastClickTime = curClickTime;
        return false;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            mUnBinder = ButterKnife.bind(this, mRootView);
            initEventAndData();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }


    protected abstract void initEventAndData();

    protected abstract int getLayoutId();


    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
        }catch (Exception o){

        }

    }


    @Override
    protected void lazyLoad() {
        if (!isVisible || isPrepared) {
            return;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }






}

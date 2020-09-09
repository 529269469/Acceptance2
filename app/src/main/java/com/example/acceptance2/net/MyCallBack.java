package com.example.acceptance2.net;


public interface MyCallBack<T> {
    void onSuccess(T t);
    void onFaile(String msg);
}

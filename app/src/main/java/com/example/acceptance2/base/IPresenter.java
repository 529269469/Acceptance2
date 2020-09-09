package com.example.acceptance2.base;


/**
 * 描述：
 */

public interface IPresenter<V extends IView> {
    void detachView();
}

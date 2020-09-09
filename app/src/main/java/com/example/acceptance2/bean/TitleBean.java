package com.example.acceptance2.bean;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/9 11
 */
public class TitleBean {
    private String title;
    private String checkFileId;
    private boolean isCheck;

    public TitleBean(String title) {
        this.title = title;
    }



    public TitleBean(String title, boolean isCheck) {
        this.title = title;
        this.isCheck = isCheck;
    }

    public TitleBean(String title, String checkFileId) {
        this.title = title;
        this.checkFileId = checkFileId;
    }

    public String getCheckFileId() {
        return checkFileId;
    }

    public void setCheckFileId(String checkFileId) {
        this.checkFileId = checkFileId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}

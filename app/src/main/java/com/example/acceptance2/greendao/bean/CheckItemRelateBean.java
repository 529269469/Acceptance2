package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2020/4/30 08
 */
@Entity
public class CheckItemRelateBean {
    @Id(autoincrement = true)
    private Long uId;
    private String dataPackageId;
    private String checkFileId;
    private String checkGroupId;
    /** 检查项id */
    private String targetId;

    /** 关联的检查项id */
    private String relateItemId;

    /** 选项 */
    private String selected;

    @Generated(hash = 481458402)
    public CheckItemRelateBean(Long uId, String dataPackageId, String checkFileId,
            String checkGroupId, String targetId, String relateItemId,
            String selected) {
        this.uId = uId;
        this.dataPackageId = dataPackageId;
        this.checkFileId = checkFileId;
        this.checkGroupId = checkGroupId;
        this.targetId = targetId;
        this.relateItemId = relateItemId;
        this.selected = selected;
    }

    @Generated(hash = 924596794)
    public CheckItemRelateBean() {
    }

    public Long getUId() {
        return this.uId;
    }

    public void setUId(Long uId) {
        this.uId = uId;
    }

    public String getDataPackageId() {
        return this.dataPackageId;
    }

    public void setDataPackageId(String dataPackageId) {
        this.dataPackageId = dataPackageId;
    }

    public String getCheckFileId() {
        return this.checkFileId;
    }

    public void setCheckFileId(String checkFileId) {
        this.checkFileId = checkFileId;
    }

    public String getCheckGroupId() {
        return this.checkGroupId;
    }

    public void setCheckGroupId(String checkGroupId) {
        this.checkGroupId = checkGroupId;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getRelateItemId() {
        return this.relateItemId;
    }

    public void setRelateItemId(String relateItemId) {
        this.relateItemId = relateItemId;
    }

    public String getSelected() {
        return this.selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }





}

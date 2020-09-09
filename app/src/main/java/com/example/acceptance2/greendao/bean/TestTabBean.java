package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2020/5/14 09
 */
@Entity
public class TestTabBean {
    @Id(autoincrement = true)
    private Long uId;

    private String dataPackageId;
    private String checkFileId;
    private String checkGroupId;

    private String id;
    private String name;
    private String requiredVal;
    private String testVal;
    private String description;
    private String uniqueValue;

    @Generated(hash = 1478435314)
    public TestTabBean(Long uId, String dataPackageId, String checkFileId,
            String checkGroupId, String id, String name, String requiredVal,
            String testVal, String description, String uniqueValue) {
        this.uId = uId;
        this.dataPackageId = dataPackageId;
        this.checkFileId = checkFileId;
        this.checkGroupId = checkGroupId;
        this.id = id;
        this.name = name;
        this.requiredVal = requiredVal;
        this.testVal = testVal;
        this.description = description;
        this.uniqueValue = uniqueValue;
    }
    @Generated(hash = 1125218038)
    public TestTabBean() {
    }

    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRequiredVal() {
        return this.requiredVal;
    }
    public void setRequiredVal(String requiredVal) {
        this.requiredVal = requiredVal;
    }
    public String getTestVal() {
        return this.testVal;
    }
    public void setTestVal(String testVal) {
        this.testVal = testVal;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUniqueValue() {
        return this.uniqueValue;
    }
    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
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


}

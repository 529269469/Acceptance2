package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/9 10
 */
@Entity
public class FileBean {
    @Id(autoincrement = true)
    private Long uId;
    private String dataPackageId;
    private String documentId;
    private String name;
    private String path;
    private String type;
    private String secret;
    private String disabledSecret;


    @Generated(hash = 1870575638)
    public FileBean(Long uId, String dataPackageId, String documentId, String name,
            String path, String type, String secret, String disabledSecret) {
        this.uId = uId;
        this.dataPackageId = dataPackageId;
        this.documentId = documentId;
        this.name = name;
        this.path = path;
        this.type = type;
        this.secret = secret;
        this.disabledSecret = disabledSecret;
    }
    @Generated(hash = 1910776192)
    public FileBean() {
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
    public String getDocumentId() {
        return this.documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSecret() {
        return this.secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String getDisabledSecret() {
        return this.disabledSecret;
    }
    public void setDisabledSecret(String disabledSecret) {
        this.disabledSecret = disabledSecret;
    }
}

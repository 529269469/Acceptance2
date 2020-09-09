package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/9 10
 */
@Entity
public class CheckVerdBean {
    @Id(autoincrement = true)
    private Long uId;
    private String dataPackageId;
    private String id;
    private String name;
    private String code;
    private String qConclusion;
    private String gConclusion;
    private String jConclusion;
    private String conclusion;
    private String checkPerson;
    private String docTypeVal;
    private String checkPersonId;
    private String checkDate;
    private String yConclusion;



    @Generated(hash = 1082600316)
    public CheckVerdBean(Long uId, String dataPackageId, String id, String name,
            String code, String qConclusion, String gConclusion, String jConclusion,
            String conclusion, String checkPerson, String docTypeVal,
            String checkPersonId, String checkDate, String yConclusion) {
        this.uId = uId;
        this.dataPackageId = dataPackageId;
        this.id = id;
        this.name = name;
        this.code = code;
        this.qConclusion = qConclusion;
        this.gConclusion = gConclusion;
        this.jConclusion = jConclusion;
        this.conclusion = conclusion;
        this.checkPerson = checkPerson;
        this.docTypeVal = docTypeVal;
        this.checkPersonId = checkPersonId;
        this.checkDate = checkDate;
        this.yConclusion = yConclusion;
    }
    @Generated(hash = 1115678058)
    public CheckVerdBean() {
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
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getQConclusion() {
        return this.qConclusion;
    }
    public void setQConclusion(String qConclusion) {
        this.qConclusion = qConclusion;
    }
    public String getGConclusion() {
        return this.gConclusion;
    }
    public void setGConclusion(String gConclusion) {
        this.gConclusion = gConclusion;
    }
    public String getJConclusion() {
        return this.jConclusion;
    }
    public void setJConclusion(String jConclusion) {
        this.jConclusion = jConclusion;
    }
    public String getConclusion() {
        return this.conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getCheckPerson() {
        return this.checkPerson;
    }
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }
    public String getDocTypeVal() {
        return this.docTypeVal;
    }
    public void setDocTypeVal(String docTypeVal) {
        this.docTypeVal = docTypeVal;
    }
    public String getCheckPersonId() {
        return this.checkPersonId;
    }
    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }
    public String getCheckDate() {
        return this.checkDate;
    }
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }
    public String getYConclusion() {
        return this.yConclusion;
    }
    public void setYConclusion(String yConclusion) {
        this.yConclusion = yConclusion;
    }
}

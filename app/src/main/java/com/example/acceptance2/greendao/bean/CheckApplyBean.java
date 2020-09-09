package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/9 10
 */
@Entity
public class CheckApplyBean {
    @Id(autoincrement = true)
    private Long uId;
    private String dataPackageId;
    private String id;
    private String name;
    private String code;
    private String contractCode;
    private String contractName;
    private String applicant;
    private String applyCompany;
    private String phone;
    private String conclusion;
    private String description;
    private String docTypeVal;

    private String acceptorUnit;
    private String acceptor;
    private String acceptorDept;



    @Generated(hash = 944161163)
    public CheckApplyBean(Long uId, String dataPackageId, String id, String name,
            String code, String contractCode, String contractName, String applicant,
            String applyCompany, String phone, String conclusion,
            String description, String docTypeVal, String acceptorUnit,
            String acceptor, String acceptorDept) {
        this.uId = uId;
        this.dataPackageId = dataPackageId;
        this.id = id;
        this.name = name;
        this.code = code;
        this.contractCode = contractCode;
        this.contractName = contractName;
        this.applicant = applicant;
        this.applyCompany = applyCompany;
        this.phone = phone;
        this.conclusion = conclusion;
        this.description = description;
        this.docTypeVal = docTypeVal;
        this.acceptorUnit = acceptorUnit;
        this.acceptor = acceptor;
        this.acceptorDept = acceptorDept;
    }
    @Generated(hash = 113687566)
    public CheckApplyBean() {
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
    public String getContractCode() {
        return this.contractCode;
    }
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public String getContractName() {
        return this.contractName;
    }
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    public String getApplicant() {
        return this.applicant;
    }
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getApplyCompany() {
        return this.applyCompany;
    }
    public void setApplyCompany(String applyCompany) {
        this.applyCompany = applyCompany;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getConclusion() {
        return this.conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocTypeVal() {
        return this.docTypeVal;
    }
    public void setDocTypeVal(String docTypeVal) {
        this.docTypeVal = docTypeVal;
    }
    public String getAcceptorUnit() {
        return this.acceptorUnit;
    }
    public void setAcceptorUnit(String acceptorUnit) {
        this.acceptorUnit = acceptorUnit;
    }
    public String getAcceptor() {
        return this.acceptor;
    }
    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }
    public String getAcceptorDept() {
        return this.acceptorDept;
    }
    public void setAcceptorDept(String acceptorDept) {
        this.acceptorDept = acceptorDept;
    }



}

package com.example.acceptance2.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2020/5/12 16
 */
@Entity
public class RegisterBean {
    @Id(autoincrement = true)
    private Long uId;

    private String name;
    private String password;
    @Generated(hash = 197196286)
    public RegisterBean(Long uId, String name, String password) {
        this.uId = uId;
        this.name = name;
        this.password = password;
    }
    @Generated(hash = 2011206514)
    public RegisterBean() {
    }
    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    


}

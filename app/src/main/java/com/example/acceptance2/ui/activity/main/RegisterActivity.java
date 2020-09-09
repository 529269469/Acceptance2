package com.example.acceptance2.ui.activity.main;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.RegisterBean;
import com.example.acceptance2.greendao.db.RegisterBeanDao;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.ToastUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private TextView tvRegister;
    private TextView tvLogin;
    private EditText loginName;
    private EditText loginPassword;
    private TextView loginSignIn;

    @Override
    protected void initView() {
        tvRegister = findViewById(R.id.tv_register);
        tvLogin = findViewById(R.id.tv_login);
        loginName = findViewById(R.id.login_name);
        loginPassword = findViewById(R.id.login_password);
        loginSignIn = findViewById(R.id.login_sign_in);

        tvRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        loginSignIn.setOnClickListener(this);


        loginName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPassword.setText("");
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initDataAndEvent() {

    }

    private boolean isLogin=true;//true登录   false注册
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                isLogin=true;
                tvRegister.setTextColor(getResources().getColor(R.color.color_FFFFFF));
                tvRegister.setBackground(getResources().getDrawable(R.drawable.login_button));

                tvLogin.setTextColor(getResources().getColor(R.color.color_333333));
                tvLogin.setBackground(getResources().getDrawable(R.color.color_FFFFFF));
                break;
            case R.id.tv_login:
                isLogin=false;
                tvRegister.setTextColor(getResources().getColor(R.color.color_333333));
                tvRegister.setBackground(getResources().getDrawable(R.color.color_FFFFFF));

                tvLogin.setTextColor(getResources().getColor(R.color.color_FFFFFF));
                tvLogin.setBackground(getResources().getDrawable(R.drawable.login_button));
                break;
            case R.id.login_sign_in:
                RegisterBeanDao registerBeanDao= MyApplication.getInstances().getRegisterDaoSession().getRegisterBeanDao();
                String name=loginName.getText().toString().trim();
                String password=loginPassword.getText().toString().trim();
                if (isLogin){
                    //登录
                    RegisterBean registerBean=registerBeanDao.queryBuilder()
                            .where(RegisterBeanDao.Properties.Name.eq(name))
                            .unique();
                    if (registerBean!=null){
                        RegisterBean registerBean2=registerBeanDao.queryBuilder()
                                .where(RegisterBeanDao.Properties.Name.eq(name))
                                .where(RegisterBeanDao.Properties.Password.eq(password))
                                .unique();
                        if (registerBean2!=null){
                            ToastUtils.getInstance().showTextToast(this,"登录成功");
                            SPUtils.put(this,"username",name);
                            startActivity(LoginActivity.openIntent(this));
                            finish();
                        }else {
                            ToastUtils.getInstance().showTextToast(this,"密码错误");
                        }
                    }else {
                        ToastUtils.getInstance().showTextToast(this,"用户名错误");
                    }
                }else {
                    //注册
                    RegisterBean registerBean=new RegisterBean(null,name,password);
                    registerBeanDao.insert(registerBean);
                    ToastUtils.getInstance().showTextToast(this,"注册成功");
                    SPUtils.put(this,"username",name);
                    startActivity(LoginActivity.openIntent(this));
                    finish();
                }
                break;
        }
    }
}

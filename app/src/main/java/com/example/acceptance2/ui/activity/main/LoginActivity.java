package com.example.acceptance2.ui.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.acceptance2.R;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.utils.RootCmd;
import com.example.acceptance2.view.ChangeTextViewSpace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ChangeTextViewSpace tvTv;
    private LinearLayout llNew;
    private LinearLayout llFile;
    private LinearLayout llChecklist;
    private ImageView iv_setup;

    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void initView() {


        tvTv = findViewById(R.id.tv_tv);
        llNew = findViewById(R.id.ll_new);
        llFile = findViewById(R.id.ll_file);
        llChecklist = findViewById(R.id.ll_checklist);
        iv_setup= findViewById(R.id.iv_setup);
        llNew.setOnClickListener(this);
        llFile.setOnClickListener(this);
        llChecklist.setOnClickListener(this);
        iv_setup.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDataAndEvent() {

        ininnnnData();

        List<PermissionItem> permissionItems1 = new ArrayList<PermissionItem>();
        permissionItems1.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
        HiPermission.create(this)
                .permissions(permissionItems1)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_new:
                //新建任务
                startActivity(NewActivity.openIntent(LoginActivity.this));
                break;
            case R.id.ll_file:
                List<PermissionItem> permissionItems1 = new ArrayList<PermissionItem>();
                permissionItems1.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));

                HiPermission.create(this)
                        .permissions(permissionItems1)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                            }

                            @Override
                            public void onFinish() {
                                startActivity(ToActivity.openIntent(LoginActivity.this));
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                            }
                        });

                break;
            case R.id.ll_checklist:
                //现有任务
                startActivity(ChecklistActivity.openIntent(this));
                break;
            case R.id.iv_setup:
                startActivity(UPActivity.openIntent(this));

                break;
        }
    }



    private void ininnnnData() {
        //创建文件
        File file=new File(getExternalCacheDir(),"test.txt");
        RootCmd.moveFileToSystem(file.getAbsolutePath(),"/system");
        try {
            //判断文件是否存在
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //开始向文件中写内容
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write("testing,,,,");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

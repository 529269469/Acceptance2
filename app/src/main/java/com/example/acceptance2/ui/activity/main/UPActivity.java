package com.example.acceptance2.ui.activity.main;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.TitleAdapter;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.bean.TitleBean;
import com.example.acceptance2.ui.fragment.up.UpFragment;
import com.example.acceptance2.ui.fragment.up.UpFragment2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UPActivity extends BaseActivity {

    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.help_loading)
    RelativeLayout helpLoading;
    @BindView(R.id.gv_one)
    ListView gvOne;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private UpFragment upFragment;
    private UpFragment2 upFragment2;
    private TitleAdapter titleAdapter;

    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, UPActivity.class);
        return intent;
    }


    private FragmentTransaction transaction;
    private List<TitleBean> list = new ArrayList<>();

    @Override
    protected void initView() {
//        openUsbDevice();
        ivGenduo.setOnClickListener(v -> finish());

        tvTuichu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        transaction = getSupportFragmentManager().beginTransaction();
        upFragment2 = new UpFragment2();
        transaction.replace(R.id.frame, upFragment2);
        transaction.commit();


        list.add(new TitleBean("内部储存"));
        list.add(new TitleBean("外部储存"));


        titleAdapter = new TitleAdapter(this, list);
        gvOne.setAdapter(titleAdapter);

        gvOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
                tvTuichu.setText(list.get(position).getTitle());
                if (position==0){
                    transaction = getSupportFragmentManager().beginTransaction();
                    upFragment2 = new UpFragment2();
                    transaction.replace(R.id.frame, upFragment2);
                    transaction.commit();
                }else {
                    transaction = getSupportFragmentManager().beginTransaction();
                    upFragment = new UpFragment();
                    transaction.replace(R.id.frame, upFragment);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_u_p;
    }

    @Override
    protected void initDataAndEvent() {

    }


}

package com.example.acceptance2.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.DeliveryAdapter2;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryActivity extends BaseActivity {
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;

    public static Intent openIntent(Context context, String id) {
        Intent intent = new Intent(context, DeliveryActivity.class);
        intent.putExtra("id", id);
        ;
        return intent;
    }

    @Override
    protected void initView() {

        ivGenduo.setOnClickListener(v -> finish());


        String id = getIntent().getStringExtra("id");
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(id))
                .whereOr(DeliveryListBeanDao.Properties.ParentId.eq("null"), DeliveryListBeanDao.Properties.ParentId.eq(""))
                .list();

        DeliveryAdapter2 legacyAdapter = new DeliveryAdapter2(this, deliveryListBeans);
        lvList.setAdapter(legacyAdapter);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    protected void initDataAndEvent() {

    }


}

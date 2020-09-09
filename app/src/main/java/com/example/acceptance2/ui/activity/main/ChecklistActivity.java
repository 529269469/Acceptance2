package com.example.acceptance2.ui.activity.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.main.ChecklistAdapter;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.AcceptDeviceBean;
import com.example.acceptance2.greendao.bean.ApplyDeptBean;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.CheckApplyBean;
import com.example.acceptance2.greendao.bean.CheckFileBean;
import com.example.acceptance2.greendao.bean.CheckGroupBean;
import com.example.acceptance2.greendao.bean.CheckItemBean;
import com.example.acceptance2.greendao.bean.CheckItemRelateBean;
import com.example.acceptance2.greendao.bean.CheckTaskBean;
import com.example.acceptance2.greendao.bean.CheckUnresolvedBean;
import com.example.acceptance2.greendao.bean.CheckVerdBean;
import com.example.acceptance2.greendao.bean.DataPackageDBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.PropertyBean;
import com.example.acceptance2.greendao.bean.PropertyBeanX;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.bean.TestTabBean;
import com.example.acceptance2.greendao.bean.UnresolvedBean;
import com.example.acceptance2.greendao.db.AcceptDeviceBeanDao;
import com.example.acceptance2.greendao.db.ApplyDeptBeanDao;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckApplyBeanDao;
import com.example.acceptance2.greendao.db.CheckFileBeanDao;
import com.example.acceptance2.greendao.db.CheckGroupBeanDao;
import com.example.acceptance2.greendao.db.CheckItemBeanDao;
import com.example.acceptance2.greendao.db.CheckItemRelateBeanDao;
import com.example.acceptance2.greendao.db.CheckTaskBeanDao;
import com.example.acceptance2.greendao.db.CheckUnresolvedBeanDao;
import com.example.acceptance2.greendao.db.CheckVerdBeanDao;
import com.example.acceptance2.greendao.db.DataPackageDBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanXDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.greendao.db.TestTabBeanDao;
import com.example.acceptance2.greendao.db.UnresolvedBeanDao;
import com.example.acceptance2.ui.MainActivity;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChecklistActivity extends BaseActivity {


    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.lv_checklist)
    MyListView lvChecklist;
    @BindView(R.id.progressBar)
    RelativeLayout progressBar;

    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, ChecklistActivity.class);
        return intent;
    }
    private int position;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    list.remove(position);
                    checklistAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };
    @Override
    protected void initView() {



        ivGenduo.setOnClickListener(view -> finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checklist;

    }

    private ChecklistAdapter checklistAdapter;
    private List<DataPackageDBean> list = new ArrayList<>();


    @Override
    protected void initDataAndEvent() {


        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
        List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.loadAll();

        list.addAll(dataPackageDBeans);
        checklistAdapter = new ChecklistAdapter(this, list);
        lvChecklist.setAdapter(checklistAdapter);


        lvChecklist.setOnItemClickListener((adapterView, view, i, l) -> {
            startActivity(MainActivity.openIntent(ChecklistActivity.this, list.get(i).getId()));
        });
        lvChecklist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ChecklistActivity.this.position=position;
                AlertDialog.Builder builder = new AlertDialog.Builder(ChecklistActivity.this);
                builder.setTitle("是否删除此项目：" + list.get(position).getName());
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.sendEmptyMessage(1);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                                CheckApplyBeanDao checkApplyBeanDao = MyApplication.getInstances().getCheckApplyDaoSession().getCheckApplyBeanDao();
                                CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
                                ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                                CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                                CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                                PropertyBeanDao propertyBeanDao = MyApplication.getInstances().getPropertyDaoSession().getPropertyBeanDao();
                                CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
                                AcceptDeviceBeanDao acceptDeviceBeanDao = MyApplication.getInstances().getAcceptDeviceaDaoSession().getAcceptDeviceBeanDao();
                                CheckItemRelateBeanDao checkItemRelateBeanDao = MyApplication.getInstances().getCheckItemRelateDaoSession().getCheckItemRelateBeanDao();
                                PropertyBeanXDao propertyBeanXDao = MyApplication.getInstances().getPropertyXDaoSession().getPropertyBeanXDao();
                                RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                                CheckVerdBeanDao checkVerdBeanDao = MyApplication.getInstances().getCheckVerdDaoSession().getCheckVerdBeanDao();
                                CheckUnresolvedBeanDao checkUnresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getCheckUnresolvedBeanDao();
                                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                                UnresolvedBeanDao unresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getUnresolvedBeanDao();
                                DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
                                ApplyDeptBeanDao applyDeptBeanDao = MyApplication.getInstances().getApplyDeptDaoSession().getApplyDeptBeanDao();
                                TestTabBeanDao testTabBeanDao = MyApplication.getInstances().getTestTabDaoSession().getTestTabBeanDao();
                                DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                                dataPackageDBeanDao.deleteByKey(list.get(position).getUId());
                                FileUtils.deleteFile(new File(list.get(position).getUpLoadFile()));
                                List<ApplyDeptBean> del18 = applyDeptBeanDao.queryBuilder()
                                        .where(ApplyDeptBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del18.size(); i++) {
                                    applyDeptBeanDao.deleteByKey(del18.get(i).getUId());
                                }
                                List<DeliveryListBean> del17 = deliveryListBeanDao.queryBuilder()
                                        .where(DeliveryListBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del17.size(); i++) {
                                    deliveryListBeanDao.deleteByKey(del17.get(i).getUId());
                                }
                                List<UnresolvedBean> del16 = unresolvedBeanDao.queryBuilder()
                                        .where(UnresolvedBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del16.size(); i++) {
                                    unresolvedBeanDao.deleteByKey(del16.get(i).getUId());
                                }
                                List<FileBean> del15 = fileBeanDao.queryBuilder()
                                        .where(FileBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del15.size(); i++) {
                                    fileBeanDao.deleteByKey(del15.get(i).getUId());
                                }
                                List<CheckUnresolvedBean> del14 = checkUnresolvedBeanDao.queryBuilder()
                                        .where(CheckUnresolvedBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del14.size(); i++) {
                                    checkUnresolvedBeanDao.deleteByKey(del14.get(i).getUId());
                                }
                                List<CheckVerdBean> del13 = checkVerdBeanDao.queryBuilder()
                                        .where(CheckVerdBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del13.size(); i++) {
                                    checkVerdBeanDao.deleteByKey(del13.get(i).getUId());
                                }
                                List<RelatedDocumentIdSetBean> del12 = relatedDocumentIdSetBeanDao.queryBuilder()
                                        .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del12.size(); i++) {
                                    relatedDocumentIdSetBeanDao.deleteByKey(del12.get(i).getUId());
                                }
                                List<PropertyBeanX> del11 = propertyBeanXDao.queryBuilder()
                                        .where(PropertyBeanXDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del11.size(); i++) {
                                    propertyBeanXDao.deleteByKey(del11.get(i).getUId());
                                }
                                List<AcceptDeviceBean> del10 = acceptDeviceBeanDao.queryBuilder()
                                        .where(AcceptDeviceBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del10.size(); i++) {
                                    acceptDeviceBeanDao.deleteByKey(del10.get(i).getUId());
                                }
                                List<CheckItemBean> del9 = checkItemBeanDao.queryBuilder()
                                        .where(CheckItemBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del9.size(); i++) {
                                    checkItemBeanDao.deleteByKey(del9.get(i).getUId());
                                }
                                List<PropertyBean> del8 = propertyBeanDao.queryBuilder()
                                        .where(PropertyBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del8.size(); i++) {
                                    propertyBeanDao.deleteByKey(del8.get(i).getUId());
                                }
                                List<CheckGroupBean> del7 = checkGroupBeanDao.queryBuilder()
                                        .where(CheckGroupBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del7.size(); i++) {
                                    checkGroupBeanDao.deleteByKey(del7.get(i).getUId());
                                }
                                List<CheckFileBean> del6 = checkFileBeanDao.queryBuilder()
                                        .where(CheckFileBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del6.size(); i++) {
                                    checkFileBeanDao.deleteByKey(del6.get(i).getUId());
                                }
                                List<ApplyItemBean> del5 = applyItemBeanDao.queryBuilder()
                                        .where(ApplyItemBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del5.size(); i++) {
                                    applyItemBeanDao.deleteByKey(del5.get(i).getUId());
                                }
                                List<DataPackageDBean> del1 = dataPackageDBeanDao.queryBuilder()
                                        .where(DataPackageDBeanDao.Properties.Id.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del1.size(); i++) {
                                    dataPackageDBeanDao.deleteByKey(del1.get(i).getUId());
                                }
                                List<DocumentBean> del2 = documentBeanDao.queryBuilder()
                                        .where(DocumentBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del2.size(); i++) {
                                    documentBeanDao.deleteByKey(del2.get(i).getUId());
                                }
                                List<CheckApplyBean> del3 = checkApplyBeanDao.queryBuilder()
                                        .where(CheckApplyBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del3.size(); i++) {
                                    checkApplyBeanDao.deleteByKey(del3.get(i).getUId());
                                }
                                List<CheckTaskBean> del4 = checkTaskBeanDao.queryBuilder()
                                        .where(CheckTaskBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del4.size(); i++) {
                                    checkTaskBeanDao.deleteByKey(del4.get(i).getUId());
                                }
                                List<CheckItemRelateBean> del21 = checkItemRelateBeanDao.queryBuilder()
                                        .where(CheckItemRelateBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < del21.size(); i++) {
                                    checkItemRelateBeanDao.deleteByKey(del21.get(i).getUId());
                                }
                                List<TestTabBean> beans = testTabBeanDao.queryBuilder()
                                        .where(TestTabBeanDao.Properties.DataPackageId.eq(list.get(position).getId())).list();
                                for (int i = 0; i < beans.size(); i++) {
                                    testTabBeanDao.deleteByKey(beans.get(i).getUId());
                                }


                                handler.sendEmptyMessage(2);
                            }
                        }).start();


                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();


                return true;
            }
        });

    }


}

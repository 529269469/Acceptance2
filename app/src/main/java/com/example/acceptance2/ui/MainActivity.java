package com.example.acceptance2.ui;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.TitleAdapter;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.bean.TitleBean;
import com.example.acceptance2.greendao.bean.CheckFileBean;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckFileBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.ui.activity.main.LoginActivity;
import com.example.acceptance2.ui.fragment.AcceptanceConclusionFragment;
import com.example.acceptance2.ui.fragment.ApplyForFragment;
import com.example.acceptance2.ui.fragment.DeliveryFragment;
import com.example.acceptance2.ui.fragment.KittingFileFragment;
import com.example.acceptance2.ui.fragment.KittingProductFragment;
import com.example.acceptance2.ui.fragment.LegacyFragment;
import com.example.acceptance2.ui.fragment.ParticularsFragment;
import com.example.acceptance2.ui.fragment.TaskFragment;
import com.example.acceptance2.utils.DaoUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private DrawerLayout drawerLayout;
    private ImageView ivGenduo;
    private TextView tvTuichu;
    private TextView tvOperation;
    private TextView tv_add;
    private FrameLayout frame;
    private RelativeLayout help_loading;
    private RelativeLayout llTitle;
    private ListView gvOne;
    private KittingProductFragment kittingProductFragment;
    private PopupWindow popupWindow;


    public static Intent openIntent(Context context, String dataPackageid) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("dataPackageid", dataPackageid);
        return intent;
    }

    private String dataPackageid;

    private List<TitleBean> list = new ArrayList<>();
    private List<String> listId = new ArrayList<>();
    private TitleAdapter titleAdapter;
    private FragmentTransaction transaction;

    /**
     * 控制LODING显示隐藏
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    help_loading.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    help_loading.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(MainActivity.this, "数据包已导出");
                    MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    break;
                case 3:
                    help_loading.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(MainActivity.this, "模板已导出");
                    break;
                case 4:
                    help_loading.setVisibility(View.GONE);
                    break;
            }

        }
    };

    @Override
    protected void initView() {

        drawerLayout = findViewById(R.id.drawerlayout_drawer);
        ivGenduo = findViewById(R.id.iv_genduo);
        tvTuichu = findViewById(R.id.tv_tuichu);
        tvOperation = findViewById(R.id.tv_operation);
        frame = findViewById(R.id.frame);
        help_loading = findViewById(R.id.help_loading);
        llTitle = findViewById(R.id.ll_title);
        gvOne = findViewById(R.id.gv_one);
        tv_add = findViewById(R.id.tv_add);
        ivGenduo.setOnClickListener(view -> {
            //显示侧滑菜单
            drawerLayout.openDrawer(GravityCompat.START);
        });

        tvOperation.setOnClickListener(view -> {
            operation();
        });

        tv_add.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private AcceptanceConclusionFragment acceptanceConclusionFragment;
    private LegacyFragment legacyFragment;
    private DeliveryFragment deliveryFragment;
    private TaskFragment taskFragment;
    private ParticularsFragment particularsFragment;
    private ApplyForFragment applyForFragment;
    private KittingFileFragment kittingFileFragment;

    @Override
    protected void initDataAndEvent() {
        dataPackageid = getIntent().getStringExtra("dataPackageid");


        ininData(0);

        titleAdapter = new TitleAdapter(this, list);
        gvOne.setAdapter(titleAdapter);

        Bundle bundle = new Bundle();
        bundle.putString("dataPackageid", dataPackageid);
        transaction = getSupportFragmentManager().beginTransaction();
        particularsFragment = new ParticularsFragment();
        particularsFragment.setArguments(bundle);
        transaction.replace(R.id.frame, particularsFragment);
        transaction.commit();

        gvOne.setOnItemClickListener((adapterView, view, position, l) -> {
            drawerLayout.closeDrawers();
            tvTuichu.setText(list.get(position).getTitle());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(false);
            }
            list.get(position).setCheck(true);
            titleAdapter.notifyDataSetChanged();
            transaction = getSupportFragmentManager().beginTransaction();

            switch (list.get(position).getTitle()) {
                case "详情信息":
                    particularsFragment = new ParticularsFragment();
                    particularsFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, particularsFragment);
                    break;
                case "验收申请":
                    applyForFragment = new ApplyForFragment();
                    applyForFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, applyForFragment);
                    break;
                case "验收任务单":
                    taskFragment = new TaskFragment();
                    taskFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, taskFragment);
                    break;
                case "验收结论":
                    acceptanceConclusionFragment = new AcceptanceConclusionFragment();
                    acceptanceConclusionFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, acceptanceConclusionFragment);
                    break;
                case "遗留问题落实":
                    legacyFragment = new LegacyFragment();
                    legacyFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, legacyFragment);
                    break;
                case "交付清单":
                    deliveryFragment = new DeliveryFragment();
                    deliveryFragment.setArguments(bundle);
                    transaction.replace(R.id.frame, deliveryFragment);
                    break;
                default:
                    CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                    List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                            .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(CheckFileBeanDao.Properties.Id.eq(list.get(position).getCheckFileId()))
                            .list();
                    if (checkFileBeans != null && !checkFileBeans.isEmpty() && checkFileBeans.get(0).getDocType().equals("CHECKACCORD")) {
                        kittingFileFragment = new KittingFileFragment();
                        bundle.putString("checkFileId", list.get(position).getCheckFileId());
                        bundle.putString("checkFileName", list.get(position).getTitle());
                        kittingFileFragment.setArguments(bundle);
                        transaction.replace(R.id.frame, kittingFileFragment);
                    } else {
                        kittingProductFragment = new KittingProductFragment();
                        bundle.putString("checkFileId", list.get(position).getCheckFileId());
                        bundle.putString("checkFileName", list.get(position).getTitle());
                        kittingProductFragment.setArguments(bundle);
                        transaction.replace(R.id.frame, kittingProductFragment);
                    }
                    break;
            }
            transaction.commit();
        });

        /**
         * 长按删除
         */
        gvOne.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position>2&&position<list.size()-3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("是否删除（"+list.get(position).getTitle()+"）检查单");
                    builder.setPositiveButton("删除", (dialog, which) -> {
                        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                        CheckFileBean checkFileBeans = checkFileBeanDao.queryBuilder()
                                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(CheckFileBeanDao.Properties.Id.eq(list.get(position).getCheckFileId()))
                                .unique();
                        checkFileBeanDao.deleteByKey(checkFileBeans.getUId());
                        ToastUtils.getInstance().showTextToast(MainActivity.this,"删除"+list.get(position).getTitle());
                        drawerLayout.closeDrawers();
                        ininData(0);
                    });

                    builder.setNegativeButton("取消", (dialog, which) -> {
                    });
                    builder.show();


                }

                return true;
            }
        });

    }

    /**
     * 刷新信息
     * @param position 第几个
     */
    private void ininData(int position) {
        list.clear();
        list.add(new TitleBean("详情信息"));
        list.add(new TitleBean("验收申请"));
        list.add(new TitleBean("验收任务单"));
        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
        List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        Comparator<CheckFileBean> comparator = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };
        Collections.sort(checkFileBeans, comparator);
        for (int i = 0; i < checkFileBeans.size(); i++) {
            CheckFileBean checkFileBean = checkFileBeanDao.queryBuilder()
                    .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(CheckFileBeanDao.Properties.UId.eq(checkFileBeans.get(i).getUId()))
                    .unique();
            checkFileBean.setSort(i + 1 + "");
            checkFileBeanDao.update(checkFileBean);

            list.add(new TitleBean(checkFileBeans.get(i).getTabsName(), checkFileBeans.get(i).getId()));
        }

        list.add(new TitleBean("验收结论"));
        list.add(new TitleBean("遗留问题落实"));
        list.add(new TitleBean("交付清单"));
        list.get(position).setCheck(true);
        tvTuichu.setText(list.get(position).getTitle());
        if (titleAdapter != null) {
            titleAdapter.notifyDataSetChanged();
        }
        Bundle bundle = new Bundle();
        bundle.putString("dataPackageid", dataPackageid);
        if (position == 0) {
            transaction = getSupportFragmentManager().beginTransaction();
            particularsFragment = new ParticularsFragment();
            particularsFragment.setArguments(bundle);
            transaction.replace(R.id.frame, particularsFragment);
            transaction.commit();
        } else {
            transaction = getSupportFragmentManager().beginTransaction();
            CheckFileBeanDao checkFileBeanDao2 = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
            List<CheckFileBean> checkFileBeans2 = checkFileBeanDao2.queryBuilder()
                    .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(CheckFileBeanDao.Properties.Id.eq(list.get(position).getCheckFileId()))
                    .list();
            bundle.putString("checkFileId", list.get(position).getCheckFileId());
            bundle.putString("checkFileName", list.get(position).getTitle());
            if (checkFileBeans2 != null && !checkFileBeans2.isEmpty() && checkFileBeans2.get(0).getDocType().equals("CHECKACCORD")) {
                kittingFileFragment = new KittingFileFragment();
                kittingFileFragment.setArguments(bundle);
                transaction.replace(R.id.frame, kittingFileFragment);
                transaction.commit();
            } else {
                kittingProductFragment = new KittingProductFragment();
                kittingProductFragment.setArguments(bundle);
                transaction.replace(R.id.frame, kittingProductFragment);
                transaction.commit();
            }
        }


    }


    public void setDeliveryFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        deliveryFragment = new DeliveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dataPackageid", dataPackageid);
        deliveryFragment.setArguments(bundle);
        transaction.replace(R.id.frame, deliveryFragment);
        transaction.commit();
    }


    /**
     * 导出模板/导出数据包
     */
    private void operation() {
        View poview = getLayoutInflater().inflate(R.layout.tv_operation_view, null);
        popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popupWindow.showAsDropDown(tvOperation);

        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
            getWindow().setAttributes(lp1);
        });
        LinearLayout tv_daochu1 = poview.findViewById(R.id.ll_file);
        LinearLayout tv_daochu2 = poview.findViewById(R.id.ll_file2);
        ImageView iv_close = poview.findViewById(R.id.iv_close);

        iv_close.setOnClickListener(v ->popupWindow.dismiss() );

        tv_daochu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                handler.sendEmptyMessage(1);
                new Thread() {
                    @Override
                    public void run() {
                        DaoUtils.setDao(dataPackageid, "casic12345");
                        handler.sendEmptyMessage(2);
                        //需要在子线程中处理的逻辑
                    }
                }.start();
            }
        });

        tv_daochu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                daochumpban();
            }
        });

    }

    /**
     * 导出模板
     */
    private void daochumpban() {
        View poview = getLayoutInflater().inflate(R.layout.moban, null);
        PopupWindow daochu = new PopupWindow(poview);
        daochu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        daochu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        daochu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        daochu.setOutsideTouchable(true);
        daochu.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        daochu.showAtLocation(tvOperation, Gravity.CENTER, 0, 0);

        daochu.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
            getWindow().setAttributes(lp1);
        });

        EditText edit_name = poview.findViewById(R.id.edit_name);
        TextView tv_no = poview.findViewById(R.id.tv_no);
        TextView tv_yes = poview.findViewById(R.id.tv_yes);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daochu.dismiss();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isBlank(edit_name.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(MainActivity.this, "请输入模板名称");
                    return;
                }
                File file = new File(Environment.getExternalStorageDirectory() + "/模板");
                if (!file.exists()) {
                    file.mkdirs();
                }
                daochu.dismiss();
                handler.sendEmptyMessage(1);
                new Thread() {
                    @Override
                    public void run() {
                        DaoUtils.setmoban(dataPackageid, edit_name.getText().toString().trim());
                        handler.sendEmptyMessage(3);
                        //需要在子线程中处理的逻辑
                    }
                }.start();

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add://添加检查单
                View poview = getLayoutInflater().inflate(R.layout.add_check_file, null);
                PopupWindow popupWindow = new PopupWindow(poview);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                WindowManager.LayoutParams lp = this.getWindow().getAttributes();
                lp.alpha = 0.7f;
                getWindow().setAttributes(lp);
                popupWindow.showAtLocation(tv_add, Gravity.CENTER, 0, 0);

                popupWindow.setOnDismissListener(() -> {
                    WindowManager.LayoutParams lp1 = getWindow().getAttributes();
                    lp1.alpha = 1f;
                    getWindow().setAttributes(lp1);
                });

                TextView tv_docType = poview.findViewById(R.id.tv_docType);
                TextView tv_productType = poview.findViewById(R.id.tv_productType);
                EditText tv_name = poview.findViewById(R.id.tv_name);
                EditText tv_sort = poview.findViewById(R.id.tv_sort);
                EditText tv_description = poview.findViewById(R.id.tv_description);
                TextView tv_save = poview.findViewById(R.id.tv_save);
                TextView tv_popup_quxiao = poview.findViewById(R.id.tv_popup_quxiao);

                tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                tv_docType.setOnClickListener(v1 -> {
                    setDialog2(tv_docType);
                });
                tv_productType.setOnClickListener(v1 -> {
                    setDialog(tv_productType);
                });
                tv_save.setOnClickListener(v1 -> {
                    String docType = tv_docType.getText().toString().trim();
                    String productType = tv_productType.getText().toString().trim();
                    String name = tv_name.getText().toString().trim();
                    String sort = tv_sort.getText().toString().trim();
                    String description = tv_description.getText().toString().trim();

                    if (StringUtils.isBlank(docType)) {
                        ToastUtils.getInstance().showTextToast(this, "检查单类型不能为空");
                        return;
                    }
                    if (StringUtils.isBlank(productType)) {
                        ToastUtils.getInstance().showTextToast(this, "产品类别不能为空");
                        return;
                    }
                    if (StringUtils.isBlank(name)) {
                        ToastUtils.getInstance().showTextToast(this, "检查单名称不能为空");
                        return;
                    }
                    if (StringUtils.isBlank(sort)) {
                        ToastUtils.getInstance().showTextToast(this, "排序号不能为空");
                        return;
                    }

                    CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                    List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                            .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .list();
                    String code = "";
                    if (checkFileBeans != null && !checkFileBeans.isEmpty()) {
                        String strThem = checkFileBeans.get(checkFileBeans.size() - 1).getCode();
                        String strThem2 = strThem.substring(strThem.length() - 2, strThem.length());

                        DecimalFormat decimalFormat = new DecimalFormat("00");
                        String string2 = decimalFormat.format(Integer.parseInt(strThem2) + 1);
                        code = (String) SPUtils.get(this, "code", "") + "-" + string2;
                    } else {
                        code = (String) SPUtils.get(this, "code", "") + "-01";
                    }

                    Comparator<CheckFileBean> comparator = (o1, o2) -> {
                        return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
                    };
                    Collections.sort(checkFileBeans, comparator);
                    for (int i = 0; i < checkFileBeans.size(); i++) {
                        int a = Integer.parseInt(sort);
                        int b = Integer.parseInt(StringUtils.isBlank(checkFileBeans.get(i).getSort()) ? "0" : checkFileBeans.get(i).getSort());
                        if (a <= b) {
                            CheckFileBean checkFileBean = checkFileBeanDao.queryBuilder()
                                    .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                    .where(CheckFileBeanDao.Properties.UId.eq(checkFileBeans.get(i).getUId()))
                                    .unique();
                            checkFileBean.setSort(b + 1 + "");
                            checkFileBeanDao.update(checkFileBean);
                        }
                        if (Integer.parseInt(sort)>checkFileBeans.size()){
                            sort=checkFileBeans.size()+1+"";
                        }

                    }

                    CheckFileBean checkFileBean = new CheckFileBean(null,
                            dataPackageid,
                            System.currentTimeMillis() + "",
                            (String) SPUtils.get(this, "name", "") + docTypetext,
                            code,
                            docTypetext2,
                            productTypetext,
                            "",
                            "",
                            "",
                            sort,
                            "",
                            sort,
                            name,
                            "",
                            "",
                            UUID.randomUUID() + "",
                            productTypetext2,
                            "");
                    checkFileBeanDao.insert(checkFileBean);
                    ininData(Integer.parseInt(sort)+2);
                    popupWindow.dismiss();
                    drawerLayout.closeDrawers();
                });

                break;
        }
    }

    String productTypetext = "";
    String productTypetext2 = "";

    private void setDialog(TextView textView) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("请选择产品类别：");
        String[] secret = new String[]{"电气产品", "电缆产品", "机械产品"};
        String[] secret2 = new String[]{"ELECTRIC", "CABLE", "MACHINE"};
        boolean[] checkedItems = new boolean[]{false, false, false};
        builder2.setMultiChoiceItems(secret, checkedItems, (dialog, which, isChecked) -> {
            checkedItems[which] = isChecked;
        });

        builder2.setPositiveButton("确定", (dialog, which) -> {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i = 0; i < secret.length; i++) {
                if (checkedItems[i]) {
                    stringBuffer.append(secret[i]).append(",");
                    stringBuffer2.append(secret2[i]).append(",");
                }
            }
            if (!StringUtils.isBlank(stringBuffer.toString())){
                productTypetext = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
                productTypetext2 = stringBuffer2.toString().substring(0, stringBuffer2.toString().length() - 1);
                textView.setText(productTypetext);
            }
            dialog.dismiss();
        });
        builder2.setNegativeButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();
    }

    String docTypetext = "";
    String docTypetext2 = "";

    private void setDialog2(TextView textView) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("请选择检查单类型：");
        String[] secret = new String[]{"齐套性检查", "过程检查", "技术类检查", "验收依据文件"};
        String[] secret2 = new String[]{"CHECKCOM", "CHECKPROCESS", "CHECKTECH", "CHECKACCORD"};
        builder2.setSingleChoiceItems(secret, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(secret[which]);
                docTypetext = secret[which];
                docTypetext2 = secret2[which];
                dialog.dismiss();
            }
        });

        builder2.setNegativeButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();
    }


}

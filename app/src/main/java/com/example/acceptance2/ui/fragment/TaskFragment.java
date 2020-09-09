package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;


import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.ApplyDeptAdapter;
import com.example.acceptance2.adapter.home.ApplyForAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.ApplyDeptBean;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.CheckTaskBean;
import com.example.acceptance2.greendao.bean.DataPackageDBean;
import com.example.acceptance2.greendao.db.ApplyDeptBeanDao;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckTaskBeanDao;
import com.example.acceptance2.greendao.db.DataPackageDBeanDao;
import com.example.acceptance2.utils.DatePickerDialogUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyListView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * 验收任务单
 */
public class TaskFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.lv_list2)
    MyListView lvList2;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_issuer)
    EditText tvIssuer;
    @BindView(R.id.tv_accepter)
    EditText tvAccepter;
    @BindView(R.id.tv_applyCompany)
    EditText tvApplyCompany;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_issueDept)
    EditText tvIssueDept;
    @BindView(R.id.tv_acceptDate)
    TextView tvAcceptDate;
    @BindView(R.id.tv_applicant)
    EditText tvApplicant;
    @BindView(R.id.tv_checkDate)
    TextView tvCheckDate;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    private List<ApplyItemBean> list = new ArrayList<>();
    private List<ApplyDeptBean> list2 = new ArrayList<>();
    private ApplyForAdapter applyForAdapter;
    private String dataPackageid;
    private ApplyDeptAdapter applyDeptAdapter;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String words = editable.toString();
            if (!StringUtils.isBlank(words)) {
                CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
                List<CheckTaskBean> checkTaskBeans = checkTaskBeanDao.queryBuilder()
                        .where(CheckTaskBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();

                if (checkTaskBeans != null && checkTaskBeans.size() > 0) {
                    CheckTaskBean checkTaskBean = new CheckTaskBean(checkTaskBeans.get(0).getUId(),
                            checkTaskBeans.get(0).getDataPackageId(),
                            checkTaskBeans.get(0).getId(),
                            checkTaskBeans.get(0).getName(),
                            checkTaskBeans.get(0).getCode(),
                            tvIssuer.getText().toString().trim(),
                            tvIssueDept.getText().toString().trim(),
                            tvAccepter.getText().toString().trim(),
                            tvAcceptDate.getText().toString().trim(),
                            tvCheckDate.getText().toString().trim(),
                            tvApplicant.getText().toString().trim(),
                            tvApplyCompany.getText().toString().trim(),
                            tvPhone.getText().toString().trim(),
                            checkTaskBeans.get(0).getDocTypeVal());
                    checkTaskBeanDao.update(checkTaskBean);
                } else {
                    DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                    List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
                            .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                            .list();
                    CheckTaskBean checkTaskBean = new CheckTaskBean(null,
                            dataPackageDBeans.get(0).getId(),
                            dataPackageDBeans.get(0).getId(),
                            dataPackageDBeans.get(0).getName(),
                            dataPackageDBeans.get(0).getCode(),
                            tvIssuer.getText().toString().trim(),
                            tvIssueDept.getText().toString().trim(),
                            tvAccepter.getText().toString().trim(),
                            tvAcceptDate.getText().toString().trim(),
                            tvCheckDate.getText().toString().trim(),
                            tvApplicant.getText().toString().trim(),
                            tvApplyCompany.getText().toString().trim(),
                            tvPhone.getText().toString().trim(),
                            "");
                    checkTaskBeanDao.insert(checkTaskBean);
                }
            }

        }
    };


    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
        List<CheckTaskBean> checkTaskBeans = checkTaskBeanDao.queryBuilder()
                .where(CheckTaskBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        if (checkTaskBeans != null && checkTaskBeans.size() > 0) {
            CheckTaskBean checkTaskBean = checkTaskBeans.get(0);
            tvCode.setText("编号：" + checkTaskBean.getCode());
            tvName.setText("名称：" + checkTaskBean.getName());
            tvIssuer.setText(checkTaskBean.getIssuer());
            tvAccepter.setText(checkTaskBean.getAccepter());
            tvApplyCompany.setText(checkTaskBean.getApplyCompany());
            tvPhone.setText(checkTaskBean.getPhone());
            tvIssueDept.setText(checkTaskBean.getIssueDept());
            tvAcceptDate.setText(checkTaskBean.getAcceptDate());
            tvApplicant.setText(checkTaskBean.getApplicant());
            tvCheckDate.setText(checkTaskBean.getCheckDate());

            ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
            List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                    .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .list();
            list.addAll(applyItemBeans);
            applyForAdapter = new ApplyForAdapter(getActivity(), list);
            lvList.setAdapter(applyForAdapter);


            ApplyDeptBeanDao applyDeptBeanDao = MyApplication.getInstances().getApplyDeptDaoSession().getApplyDeptBeanDao();
            List<ApplyDeptBean> applyDeptBeans = applyDeptBeanDao.queryBuilder()
                    .where(ApplyDeptBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(ApplyDeptBeanDao.Properties.CheckTaskId.eq(checkTaskBeans.get(0).getId()))
                    .list();

            list2.addAll(applyDeptBeans);
            applyDeptAdapter = new ApplyDeptAdapter(getActivity(), list2);
            lvList2.setAdapter(applyDeptAdapter);
        }

        tvIssuer.addTextChangedListener(textWatcher);
        tvAccepter.addTextChangedListener(textWatcher);
        tvApplyCompany.addTextChangedListener(textWatcher);
        tvPhone.addTextChangedListener(textWatcher);
        tvIssueDept.addTextChangedListener(textWatcher);
        tvApplicant.addTextChangedListener(textWatcher);


        tvSave.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvCheckDate.setOnClickListener(this);
        tvAcceptDate.setOnClickListener(this);


        lvList.setOnItemClickListener((adapterView, view, i, l) -> {
            addPopup(true, i);
        });

        lvList.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否删除本条数据");
            builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                    applyItemBeanDao.deleteByKey(list.get(i).getUId());
                    list.remove(i);
                    applyForAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();

            return true;
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
                List<CheckTaskBean> checkTaskBeans = checkTaskBeanDao.queryBuilder()
                        .where(CheckTaskBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();
                CheckTaskBean checkTaskBean = new CheckTaskBean(checkTaskBeans.get(0).getUId(),
                        checkTaskBeans.get(0).getDataPackageId(),
                        checkTaskBeans.get(0).getId(),
                        checkTaskBeans.get(0).getName(),
                        checkTaskBeans.get(0).getCode(),
                        tvIssuer.getText().toString().trim(),
                        tvIssueDept.getText().toString().trim(),
                        tvAccepter.getText().toString().trim(),
                        tvAcceptDate.getText().toString().trim(),
                        tvCheckDate.getText().toString().trim(),
                        tvApplicant.getText().toString().trim(),
                        tvApplyCompany.getText().toString().trim(),
                        tvPhone.getText().toString().trim(),
                        checkTaskBeans.get(0).getDocTypeVal());
                checkTaskBeanDao.update(checkTaskBean);
                ToastUtils.getInstance().showTextToast(getActivity(), "保存成功");
                break;
            case R.id.tv_add:
                addPopup(false, 0);
                break;
            case R.id.tv_checkDate:
                DatePickerDialogUtils.setData(tvCheckDate, getActivity(), new DatePickerDialogUtils.IDatePickerDialog() {
                    @Override
                    public void setData(String data) {
                        CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
                        List<CheckTaskBean> checkTaskBeans = checkTaskBeanDao.queryBuilder()
                                .where(CheckTaskBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .list();

                        if (checkTaskBeans != null && checkTaskBeans.size() > 0) {
                            CheckTaskBean checkTaskBean = new CheckTaskBean(checkTaskBeans.get(0).getUId(),
                                    checkTaskBeans.get(0).getDataPackageId(),
                                    checkTaskBeans.get(0).getId(),
                                    checkTaskBeans.get(0).getName(),
                                    checkTaskBeans.get(0).getCode(),
                                    tvIssuer.getText().toString().trim(),
                                    tvIssueDept.getText().toString().trim(),
                                    tvAccepter.getText().toString().trim(),
                                    tvAcceptDate.getText().toString().trim(),
                                    data,
                                    tvApplicant.getText().toString().trim(),
                                    tvApplyCompany.getText().toString().trim(),
                                    tvPhone.getText().toString().trim(),
                                    checkTaskBeans.get(0).getDocTypeVal());
                            checkTaskBeanDao.update(checkTaskBean);
                        } else {
                            DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                            List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
                                    .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                                    .list();
                            CheckTaskBean checkTaskBean = new CheckTaskBean(null,
                                    dataPackageDBeans.get(0).getId(),
                                    dataPackageDBeans.get(0).getId(),
                                    dataPackageDBeans.get(0).getName(),
                                    dataPackageDBeans.get(0).getCode(),
                                    tvIssuer.getText().toString().trim(),
                                    tvIssueDept.getText().toString().trim(),
                                    tvAccepter.getText().toString().trim(),
                                    tvAcceptDate.getText().toString().trim(),
                                    data,
                                    tvApplicant.getText().toString().trim(),
                                    tvApplyCompany.getText().toString().trim(),
                                    tvPhone.getText().toString().trim(),
                                    "");
                            checkTaskBeanDao.insert(checkTaskBean);
                        }
                    }
                });
                break;
            case R.id.tv_acceptDate:
                DatePickerDialogUtils.setData(tvAcceptDate, getActivity(), new DatePickerDialogUtils.IDatePickerDialog() {
                    @Override
                    public void setData(String data) {
                        CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
                        List<CheckTaskBean> checkTaskBeans = checkTaskBeanDao.queryBuilder()
                                .where(CheckTaskBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .list();

                        if (checkTaskBeans != null && checkTaskBeans.size() > 0) {
                            CheckTaskBean checkTaskBean = new CheckTaskBean(checkTaskBeans.get(0).getUId(),
                                    checkTaskBeans.get(0).getDataPackageId(),
                                    checkTaskBeans.get(0).getId(),
                                    checkTaskBeans.get(0).getName(),
                                    checkTaskBeans.get(0).getCode(),
                                    tvIssuer.getText().toString().trim(),
                                    tvIssueDept.getText().toString().trim(),
                                    tvAccepter.getText().toString().trim(),
                                    data,
                                    tvCheckDate.getText().toString().trim(),
                                    tvApplicant.getText().toString().trim(),
                                    tvApplyCompany.getText().toString().trim(),
                                    tvPhone.getText().toString().trim(),
                                    checkTaskBeans.get(0).getDocTypeVal());
                            checkTaskBeanDao.update(checkTaskBean);
                        } else {
                            DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                            List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
                                    .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                                    .list();
                            CheckTaskBean checkTaskBean = new CheckTaskBean(null,
                                    dataPackageDBeans.get(0).getId(),
                                    dataPackageDBeans.get(0).getId(),
                                    dataPackageDBeans.get(0).getName(),
                                    dataPackageDBeans.get(0).getCode(),
                                    tvIssuer.getText().toString().trim(),
                                    tvIssueDept.getText().toString().trim(),
                                    tvAccepter.getText().toString().trim(),
                                    tvAcceptDate.getText().toString().trim(),
                                    data,
                                    tvApplicant.getText().toString().trim(),
                                    tvApplyCompany.getText().toString().trim(),
                                    tvPhone.getText().toString().trim(),
                                    "");
                            checkTaskBeanDao.insert(checkTaskBean);
                        }
                    }
                });
                break;

        }
    }


    private void addPopup(boolean isAdd, int position) {
        View view = getLayoutInflater().inflate(R.layout.popup_add, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(tvAdd, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });
        TextView tv_productCodeName = view.findViewById(R.id.tv_productCodeName);
        TextView tv_productName = view.findViewById(R.id.tv_productName);
        EditText tv_productCode = view.findViewById(R.id.tv_productCode);
        EditText tv_productStatus = view.findViewById(R.id.tv_productStatus);
        EditText tv_checkCount = view.findViewById(R.id.tv_checkCount);
        EditText tv_checkCount2 = view.findViewById(R.id.tv_checkCount2);
        EditText tv_checkCount3 = view.findViewById(R.id.tv_checkCount3);
        EditText tv_description = view.findViewById(R.id.tv_description);
        Switch tv_isPureCheck = view.findViewById(R.id.tv_isPureCheck);
        Switch tv_isArmyCheck = view.findViewById(R.id.tv_isArmyCheck);
        Switch tv_isCompleteChoice = view.findViewById(R.id.tv_isCompleteChoice);
        Switch tv_isCompleteRoutine = view.findViewById(R.id.tv_isCompleteRoutine);
        Switch tv_isSatisfyRequire = view.findViewById(R.id.tv_isSatisfyRequire);
        TextView tv_popup_save = view.findViewById(R.id.tv_popup_save);
        TextView tv_popup_quxiao = view.findViewById(R.id.tv_popup_quxiao);

        tv_popup_quxiao.setOnClickListener(v ->
                popupWindow.dismiss());

        tv_productCodeName.setText((String) SPUtils.get(getActivity(), "productCode", ""));
        tv_productName.setText((String) SPUtils.get(getActivity(), "productName", ""));
        if (isAdd) {
            tv_productCode.setText(list.get(position).getProductCode());
            tv_productStatus.setText(list.get(position).getProductStatus());
            tv_description.setText(list.get(position).getDescription());
            if (!StringUtils.isBlank(list.get(position).getIsPureCheck()) && list.get(position).getIsPureCheck().equals("true")) {
                tv_isPureCheck.setChecked(true);
            } else {
                tv_isPureCheck.setChecked(false);
            }
            if (!StringUtils.isBlank(list.get(position).getIsArmyCheck()) && list.get(position).getIsArmyCheck().equals("true")) {
                tv_isArmyCheck.setChecked(true);
            } else {
                tv_isArmyCheck.setChecked(false);
            }
            if (!StringUtils.isBlank(list.get(position).getIsCompleteChoice()) && list.get(position).getIsCompleteChoice().equals("true")) {
                tv_isCompleteChoice.setChecked(true);
            } else {
                tv_isCompleteChoice.setChecked(false);
            }
            if (!StringUtils.isBlank(list.get(position).getIsCompleteRoutine()) && list.get(position).getIsCompleteRoutine().equals("true")) {
                tv_isCompleteRoutine.setChecked(true);
            } else {
                tv_isCompleteRoutine.setChecked(false);
            }
            if (!StringUtils.isBlank(list.get(position).getIsSatisfyRequire()) && list.get(position).getIsSatisfyRequire().equals("true")) {
                tv_isSatisfyRequire.setChecked(true);
            } else {
                tv_isSatisfyRequire.setChecked(false);
            }
            if (!StringUtils.isBlank(list.get(position).getCheckCount())) {
                String[] checkCoun = list.get(position).getCheckCount().split("/");
                try {
                    tv_checkCount.setText(checkCoun[0]);
                    tv_checkCount2.setText(checkCoun[1]);
                    tv_checkCount3.setText(checkCoun[2]);
                } catch (Exception o) {

                }

            }

        }

        tv_popup_save.setOnClickListener(view1 -> {
            ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
            if (isAdd) {
                ApplyItemBean applyItemBean = new ApplyItemBean(list.get(position).getUId(),
                        list.get(position).getDataPackageId(),
                        list.get(position).getId(),
                        tv_productCodeName.getText().toString().trim(),
                        tv_productCode.getText().toString().trim(),
                        tv_productStatus.getText().toString().trim(),
                        tv_checkCount.getText().toString().trim() + "/" + tv_checkCount2.getText().toString().trim() + "/" + tv_checkCount3.getText().toString().trim(),
                        tv_isPureCheck.isChecked() + "",
                        tv_isArmyCheck.isChecked() + "",
                        tv_isCompleteChoice.isChecked() + "",
                        tv_isCompleteRoutine.isChecked() + "",
                        tv_isSatisfyRequire.isChecked() + "",
                        tv_description.getText().toString().trim(),
                        tv_productName.getText().toString().trim(),
                        list.get(position).getPassCheck(),
                        list.get(position).getUniqueValue());
                applyItemBeanDao.update(applyItemBean);
            } else {
                ApplyItemBean applyItemBean = new ApplyItemBean(null,
                        dataPackageid,
                        System.currentTimeMillis() + "",
                        tv_productCodeName.getText().toString().trim(),
                        tv_productCode.getText().toString().trim(),
                        tv_productStatus.getText().toString().trim(),
                        tv_checkCount.getText().toString().trim() + "/" + tv_checkCount2.getText().toString().trim() + "/" + tv_checkCount3.getText().toString().trim(),
                        tv_isPureCheck.isChecked() + "",
                        tv_isArmyCheck.isChecked() + "",
                        tv_isCompleteChoice.isChecked() + "",
                        tv_isCompleteRoutine.isChecked() + "",
                        tv_isSatisfyRequire.isChecked() + "",
                        tv_description.getText().toString().trim(),
                        tv_productName.getText().toString().trim(),
                        "",
                        UUID.randomUUID().toString());
                applyItemBeanDao.insert(applyItemBean);
            }
            List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                    .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .list();
            list.clear();
            list.addAll(applyItemBeans);
            applyForAdapter.notifyDataSetChanged();
            popupWindow.dismiss();
            ToastUtils.getInstance().showTextToast(getActivity(), "保存成功");
        });

    }
}

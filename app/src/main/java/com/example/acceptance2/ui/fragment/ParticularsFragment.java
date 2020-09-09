package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.TypeAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.CheckGroupBean;
import com.example.acceptance2.greendao.bean.DataPackageDBean;
import com.example.acceptance2.greendao.db.CheckGroupBeanDao;
import com.example.acceptance2.greendao.db.DataPackageDBeanDao;
import com.example.acceptance2.utils.DatePickerDialogUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyListView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 详情信息
 */
public class ParticularsFragment extends BaseFragment {


    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_type_part2)
    TextView tvTypePart2;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.tv_modelSeriesName)
    EditText tvModelSeriesName;
    @BindView(R.id.tv_productName)
    EditText tvProductName;
    @BindView(R.id.tv_productType2)
    TextView tvProductType2;
    @BindView(R.id.tv_productType)
    TextView tvProductType;
    @BindView(R.id.tv_stage)
    TextView tvStage;
    @BindView(R.id.tv_createTime)
    TextView tvCreateTime;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_responseUnit2)
    TextView tvResponseUnit2;
    @BindView(R.id.tv_responseUnit)
    EditText tvResponseUnit;
    @BindView(R.id.tv_modelCode)
    EditText tvModelCode;
    @BindView(R.id.tv_productCode)
    EditText tvProductCode;
    @BindView(R.id.tv_batch)
    EditText tvBatch;
    @BindView(R.id.tv_creator)
    EditText tvCreator;
    private String dataPackageid;

    /**
     * 输入框自动保存事件
     */
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
                DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                List<DataPackageDBean> list = dataPackageDBeanDao.queryBuilder()
                        .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                        .list();
                DataPackageDBean packageDBean = new DataPackageDBean(list.get(0).getUId(),
                        list.get(0).getNamePackage(),
                        list.get(0).getUpLoadFile(),
                        list.get(0).getId(),
                        tvName.getText().toString().trim(),
                        tvCode.getText().toString().trim(),
                        tvType.getText().toString().trim(),
                        tvResponseUnit.getText().toString().trim(),
                        tvModelCode.getText().toString().trim(),
                        tvProductName.getText().toString().trim(),
                        tvProductCode.getText().toString().trim(),
                        tvProductType.getText().toString().trim(),
                        tvBatch.getText().toString().trim(),
                        tvCreator.getText().toString().trim(),
                        tvCreateTime.getText().toString().trim(),
                        list.get(0).getModelSeries(),
                        tvModelSeriesName.getText().toString().trim(),
                        list.get(0).getPkgTemplateId(),
                        list.get(0).getLifecycleStateId(),
                        list.get(0).getLifecycleStateIdentifier(),
                        list.get(0).getBaseType(),
                        list.get(0).getModelSeriesId(),
                        list.get(0).getRepositoryId(),
                        list.get(0).getIsTemplate(),
                        list.get(0).getOwnerId(),
                        list.get(0).getProductTypeValue(),
                        list.get(0).getApplyCompany(),
                        list.get(0).getAcceptorUnit(),
                        tvStage.getText().toString().trim(),
                        list.get(0).getUniqueValue(),
                        list.get(0).getVersionInfo(),
                        list.get(0).getVersionInfo2());
                dataPackageDBeanDao.update(packageDBean);
            }

        }
    };


    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        dataPackageid = bundle.getString("dataPackageid");
        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
        List<DataPackageDBean> list = dataPackageDBeanDao.queryBuilder()
                .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                .list();

        DataPackageDBean dataPackageDBean = list.get(0);
        SPUtils.put(getActivity(), "path", dataPackageDBean.getUpLoadFile());
        SPUtils.put(getActivity(), "code", dataPackageDBean.getCode());
        SPUtils.put(getActivity(), "name", dataPackageDBean.getName());
        SPUtils.put(getActivity(), "modelCode", dataPackageDBean.getModelCode());
        SPUtils.put(getActivity(), "productName", dataPackageDBean.getProductName());
        SPUtils.put(getActivity(), "productCode", dataPackageDBean.getProductCode());
        SPUtils.put(getActivity(), "id", dataPackageDBean.getId());

        tvCode.setText(dataPackageDBean.getCode());
        tvCreator.setText(dataPackageDBean.getCreator());
        tvResponseUnit.setText(dataPackageDBean.getResponseUnit());
        tvProductName.setText(dataPackageDBean.getProductName());
        tvModelCode.setText(dataPackageDBean.getModelCode());
        tvName.setText(dataPackageDBean.getName());
        tvCreateTime.setText(dataPackageDBean.getCreateTime());
        tvType.setText(dataPackageDBean.getType());
        tvProductType.setText(dataPackageDBean.getProductType());
        tvBatch.setText(dataPackageDBean.getBatch());
        tvProductCode.setText(dataPackageDBean.getProductCode());
        tvStage.setText(dataPackageDBean.getStage());
        tvModelSeriesName.setText(dataPackageDBean.getModelSeriesName());


        tvModelSeriesName.addTextChangedListener(textWatcher);
        tvCode.addTextChangedListener(textWatcher);
        tvCreator.addTextChangedListener(textWatcher);
        tvResponseUnit.addTextChangedListener(textWatcher);
        tvProductName.addTextChangedListener(textWatcher);
        tvModelCode.addTextChangedListener(textWatcher);
        tvName.addTextChangedListener(textWatcher);
        tvType.addTextChangedListener(textWatcher);
        tvBatch.addTextChangedListener(textWatcher);
        tvProductCode.addTextChangedListener(textWatcher);


        tvStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] stage = new String[]{"方案阶段(M)", "初样阶段(C)", "C1", "C2", "试样阶段(S)",
                        "S1", "S2", "Sd", "正样阶段(Z)", "定型阶段(D)",
                        "工艺定型阶段(G)", "批产阶段(P)", "可行性研究阶段(KXX)"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("请选择阶段：");
                int dishPos = 0;
                builder2.setSingleChoiceItems(stage, dishPos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvStage.setText(stage[which]);
                        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                        List<DataPackageDBean> list = dataPackageDBeanDao.queryBuilder()
                                .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                                .list();
                        DataPackageDBean packageDBean = new DataPackageDBean(list.get(0).getUId(),
                                list.get(0).getNamePackage(),
                                list.get(0).getUpLoadFile(),
                                list.get(0).getId(),
                                tvName.getText().toString().trim(),
                                tvCode.getText().toString().trim(),
                                tvType.getText().toString().trim(),
                                tvResponseUnit.getText().toString().trim(),
                                tvModelCode.getText().toString().trim(),
                                tvProductName.getText().toString().trim(),
                                tvProductCode.getText().toString().trim(),
                                tvProductType.getText().toString().trim(),
                                tvBatch.getText().toString().trim(),
                                tvCreator.getText().toString().trim(),
                                tvCreateTime.getText().toString().trim(),
                                list.get(0).getModelSeries(),
                                tvModelSeriesName.getText().toString().trim(),
                                list.get(0).getPkgTemplateId(),
                                list.get(0).getLifecycleStateId(),
                                list.get(0).getLifecycleStateIdentifier(),
                                list.get(0).getBaseType(),
                                list.get(0).getModelSeriesId(),
                                list.get(0).getRepositoryId(),
                                list.get(0).getIsTemplate(),
                                list.get(0).getOwnerId(),
                                list.get(0).getProductTypeValue(),
                                list.get(0).getApplyCompany(),
                                list.get(0).getAcceptorUnit(),
                                stage[which],
                                list.get(0).getUniqueValue(),
                                list.get(0).getVersionInfo(),
                                list.get(0).getVersionInfo2());
                        dataPackageDBeanDao.update(packageDBean);
                        dialog.dismiss();
                    }
                });
                builder2.setPositiveButton("取消", null);
                builder2.setCancelable(false);
                builder2.show();
            }
        });

        tvProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO
               ToastUtils.getInstance().showTextToast(getActivity(),"不可更改");
//                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
//                builder2.setTitle("请选择产品类别：");
//                String[] secret = new String[]{"电气产品", "电缆产品", "机械产品"};
//                String[] secret2 = new String[]{"ELECTRIC", "CABLE", "MACHINE"};
//                boolean[] checkedItems = new boolean[]{false, false, false};
//                builder2.setMultiChoiceItems(secret, checkedItems, (dialog, which, isChecked) -> {
//                    checkedItems[which] = isChecked;
//                });
//
//                builder2.setPositiveButton("确定", (dialog, which) -> {
//                    StringBuffer stringBuffer = new StringBuffer();
//                    StringBuffer stringBuffer2 = new StringBuffer();
//                    for (int i = 0; i < secret.length; i++) {
//                        if (checkedItems[i]) {
//                            stringBuffer.append(secret[i]).append(",");
//                            stringBuffer2.append(secret2[i]).append(",");
//                        }
//                    }
//                    if (!StringUtils.isBlank(stringBuffer.toString())){
//                       String text = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
//                        String text2 = stringBuffer2.toString().substring(0, stringBuffer2.toString().length() - 1);
//                        tvProductType.setText(text);
//                    }
//                    dialog.dismiss();
//                });
//                builder2.setNegativeButton("取消", null);
//                builder2.setCancelable(false);
//                builder2.show();
            }
        });


        //时间选择器
        tvCreateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogUtils.setData(tvCreateTime, getActivity(), new DatePickerDialogUtils.IDatePickerDialog() {
                    @Override
                    public void setData(String data) {
                        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                        List<DataPackageDBean> list = dataPackageDBeanDao.queryBuilder()
                                .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                                .list();
                        DataPackageDBean packageDBean = new DataPackageDBean(list.get(0).getUId(),
                                list.get(0).getNamePackage(),
                                list.get(0).getUpLoadFile(),
                                list.get(0).getId(),
                                tvName.getText().toString().trim(),
                                tvCode.getText().toString().trim(),
                                tvType.getText().toString().trim(),
                                tvResponseUnit.getText().toString().trim(),
                                tvModelCode.getText().toString().trim(),
                                tvProductName.getText().toString().trim(),
                                tvProductCode.getText().toString().trim(),
                                tvProductType.getText().toString().trim(),
                                tvBatch.getText().toString().trim(),
                                tvCreator.getText().toString().trim(),
                                data,
                                list.get(0).getModelSeries(),
                                tvModelSeriesName.getText().toString().trim(),
                                list.get(0).getPkgTemplateId(),
                                list.get(0).getLifecycleStateId(),
                                list.get(0).getLifecycleStateIdentifier(),
                                list.get(0).getBaseType(),
                                list.get(0).getModelSeriesId(),
                                list.get(0).getRepositoryId(),
                                list.get(0).getIsTemplate(),
                                list.get(0).getOwnerId(),
                                list.get(0).getProductTypeValue(),
                                list.get(0).getApplyCompany(),
                                list.get(0).getAcceptorUnit(),
                                tvStage.getText().toString().trim(),
                                list.get(0).getUniqueValue(),
                                list.get(0).getVersionInfo(),
                                list.get(0).getVersionInfo2());
                        dataPackageDBeanDao.update(packageDBean);
                    }
                });
            }
        });

        tvTypePart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = (String) SPUtils.get(getActivity(), "PacketType", "");
                if (!StringUtils.isBlank(type)) {
                    mobanPo(tvType, type);
                }
            }
        });
        tvResponseUnit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = (String) SPUtils.get(getActivity(), "DutyType", "");
                if (!StringUtils.isBlank(type)) {
                    mobanPo(tvResponseUnit, type);
                }
            }
        });
        tvProductType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = (String) SPUtils.get(getActivity(), "ProductType", "");
                if (!StringUtils.isBlank(type)) {
                    mobanPo(tvProductType, type);
                }
            }
        });
    }


    private void mobanPo(TextView editText, String str) {
        View poview = getLayoutInflater().inflate(R.layout.moban_item2, null);
        PopupWindow daochu = new PopupWindow(poview);
        daochu.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        daochu.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        daochu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        daochu.setOutsideTouchable(true);
        daochu.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        daochu.showAsDropDown(editText);

        daochu.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        List<String> list = new ArrayList<>();
        MyListView lv_moban = poview.findViewById(R.id.lv_moban);
        String[] ss = str.split(",");
        for (int i = 0; i < ss.length; i++) {
            list.add(ss[i]);
        }

        TypeAdapter moAdapter = new TypeAdapter(getActivity(), list);
        lv_moban.setAdapter(moAdapter);

        lv_moban.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                daochu.dismiss();
                editText.setText(list.get(i));
                DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                List<DataPackageDBean> list = dataPackageDBeanDao.queryBuilder()
                        .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                        .list();
                DataPackageDBean packageDBean = new DataPackageDBean(list.get(0).getUId(),
                        list.get(0).getNamePackage(),
                        list.get(0).getUpLoadFile(),
                        list.get(0).getId(),
                        tvName.getText().toString().trim(),
                        tvCode.getText().toString().trim(),
                        tvType.getText().toString().trim(),
                        tvResponseUnit.getText().toString().trim(),
                        tvModelCode.getText().toString().trim(),
                        tvProductName.getText().toString().trim(),
                        tvProductCode.getText().toString().trim(),
                        tvProductType.getText().toString().trim(),
                        tvBatch.getText().toString().trim(),
                        tvCreator.getText().toString().trim(),
                        tvCreateTime.getText().toString().trim(),
                        list.get(0).getModelSeries(),
                        tvModelSeriesName.getText().toString().trim(),
                        list.get(0).getPkgTemplateId(),
                        list.get(0).getLifecycleStateId(),
                        list.get(0).getLifecycleStateIdentifier(),
                        list.get(0).getBaseType(),
                        list.get(0).getModelSeriesId(),
                        list.get(0).getRepositoryId(),
                        list.get(0).getIsTemplate(),
                        list.get(0).getOwnerId(),
                        list.get(0).getProductTypeValue(),
                        list.get(0).getApplyCompany(),
                        list.get(0).getAcceptorUnit(),
                        tvStage.getText().toString().trim(),
                        list.get(0).getUniqueValue(),
                        list.get(0).getVersionInfo(),
                        list.get(0).getVersionInfo2());
                dataPackageDBeanDao.update(packageDBean);

            }
        });


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_particulars;
    }

}

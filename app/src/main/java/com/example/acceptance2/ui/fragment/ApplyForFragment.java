package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.FileAddAdapter;
import com.example.acceptance2.adapter.FileApplyForAdapter;
import com.example.acceptance2.adapter.GridAdapter;
import com.example.acceptance2.adapter.home.ApplyForAdapter;
import com.example.acceptance2.adapter.home.LvFileAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.CheckApplyBean;
import com.example.acceptance2.greendao.bean.DataPackageDBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckApplyBeanDao;
import com.example.acceptance2.greendao.db.DataPackageDBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.ui.MainActivity;
import com.example.acceptance2.ui.activity.main.AddActivity;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyGridView;
import com.example.acceptance2.view.MyListView;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;

/**
 * 验收申请
 */

public class ApplyForFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_conclusion)
    TextView tvConclusion;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_contractCode)
    TextView tvContractCode;
    @BindView(R.id.tv_contractName)
    TextView tvContractName;
    @BindView(R.id.et_applyCompany)
    EditText etApplyCompany;
    @BindView(R.id.et_applicant)
    EditText etApplicant;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_conclusion)
    EditText etConclusion;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.tv_save)
    TextView tv_save;
    @BindView(R.id.lv_conclusion)
    MyListView lv_conclusion;
    @BindView(R.id.tv_paizhao)
    TextView tv_paizhao;
    @BindView(R.id.et_acceptorUnit)
    EditText et_acceptorUnit;
    @BindView(R.id.et_acceptor)
    EditText et_acceptor;
    @BindView(R.id.et_acceptorDept)
    EditText et_acceptorDept;
    @BindView(R.id.tv_add_contract)
    TextView tvAddContract;
    @BindView(R.id.lv_list_contract)
    MyListView lvListContract;


    private List<ApplyItemBean> list = new ArrayList<>();
    private ApplyForAdapter applyForAdapter;
    private String dataPackageid;
    private List<FileBean> fileLvList = new ArrayList<>();
    private String parentId;
    private String checkApplyId;

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
                CheckApplyBeanDao checkApplyBeanDao = MyApplication.getInstances().getCheckApplyDaoSession().getCheckApplyBeanDao();
                List<CheckApplyBean> checkApplyBeans = checkApplyBeanDao.queryBuilder()
                        .where(CheckApplyBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();
                if (checkApplyBeans != null && checkApplyBeans.size() > 0) {
                    CheckApplyBean checkApplyBean = new CheckApplyBean(checkApplyBeans.get(0).getUId(),
                            checkApplyBeans.get(0).getDataPackageId(),
                            checkApplyBeans.get(0).getId(),
                            checkApplyBeans.get(0).getName(),
                            checkApplyBeans.get(0).getCode(),
                            checkApplyBeans.get(0).getContractCode(),
                            checkApplyBeans.get(0).getContractName(),
                            etApplicant.getText().toString().trim(),
                            etApplyCompany.getText().toString().trim(),
                            etPhone.getText().toString().trim(),
                            etConclusion.getText().toString().trim(),
                            etDescription.getText().toString().trim(),
                            checkApplyBeans.get(0).getDocTypeVal(),
                            et_acceptorUnit.getText().toString().trim(),
                            et_acceptor.getText().toString().trim(),
                            et_acceptorDept.getText().toString().trim());
                    checkApplyBeanDao.update(checkApplyBean);
                } else {
                    DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
                    List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
                            .where(DataPackageDBeanDao.Properties.Id.eq(dataPackageid))
                            .list();
                    CheckApplyBean checkApplyBean = new CheckApplyBean(null,
                            dataPackageDBeans.get(0).getId(),
                            dataPackageDBeans.get(0).getId(),
                            dataPackageDBeans.get(0).getName(),
                            dataPackageDBeans.get(0).getCode(),
                            dataPackageDBeans.get(0).getProductCode(),
                            dataPackageDBeans.get(0).getProductName(),
                            etApplicant.getText().toString().trim(),
                            etApplyCompany.getText().toString().trim(),
                            etPhone.getText().toString().trim(),
                            etConclusion.getText().toString().trim(),
                            etDescription.getText().toString().trim(),
                            "",
                            et_acceptorUnit.getText().toString().trim(),
                            et_acceptor.getText().toString().trim(),
                            et_acceptorDept.getText().toString().trim());
                    checkApplyBeanDao.insert(checkApplyBean);
                }

            }

        }
    };
    private List<DocumentBean> dcumentList = new ArrayList<>();
    private LvFileAdapter lvFileAdapter;

    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        CheckApplyBeanDao checkApplyBeanDao = MyApplication.getInstances().getCheckApplyDaoSession().getCheckApplyBeanDao();
        List<CheckApplyBean> checkApplyBeans = checkApplyBeanDao.queryBuilder()
                .where(CheckApplyBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        if (checkApplyBeans != null && checkApplyBeans.size() > 0) {
            CheckApplyBean checkApplyBean = checkApplyBeans.get(0);
            tvCode.setText("编号：" + checkApplyBean.getCode());
            tvName.setText("名称：" + checkApplyBean.getName());
            tvContractCode.setText("合同(调度单)编号：" + checkApplyBean.getContractCode());
            tvContractName.setText("合同(调度单)名称：" + checkApplyBean.getContractName());
            checkApplyId=checkApplyBean.getId();
            etApplyCompany.setText(checkApplyBean.getApplyCompany());
            etApplicant.setText(checkApplyBean.getApplicant());
            etPhone.setText(checkApplyBean.getPhone());
            etConclusion.setText(checkApplyBean.getConclusion());
            etDescription.setText(checkApplyBean.getDescription());
            et_acceptorUnit.setText(checkApplyBean.getAcceptorUnit());
            et_acceptor.setText(checkApplyBean.getAcceptor());
            et_acceptorDept.setText(checkApplyBean.getAcceptorDept());
        }


        ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
        List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        list.addAll(applyItemBeans);

        applyForAdapter = new ApplyForAdapter(getActivity(), list);
        lvList.setAdapter(applyForAdapter);

        etApplicant.addTextChangedListener(textWatcher);
        etApplyCompany.addTextChangedListener(textWatcher);
        etPhone.addTextChangedListener(textWatcher);
        etConclusion.addTextChangedListener(textWatcher);
        etDescription.addTextChangedListener(textWatcher);
        et_acceptorUnit.addTextChangedListener(textWatcher);
        et_acceptor.addTextChangedListener(textWatcher);
        et_acceptorDept.addTextChangedListener(textWatcher);

        tv_add.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        tv_paizhao.setOnClickListener(this);
        tvAddContract.setOnClickListener(this);
        tvContractCode.setOnClickListener(this);
        tvContractName.setOnClickListener(this);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addPopup(true, i);
            }
        });

        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
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
            }
        });


        /**
         * 下面为合同列表
         */

        setResume();
        lvFileAdapter = new LvFileAdapter(getActivity(), dcumentList);
        lvListContract.setAdapter(lvFileAdapter);

        lvListContract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForResult(AddActivity.openIntent(getActivity(), dataPackageid, dcumentList.get(position).getId(), true, true), 1);

            }
        });


        lvListContract.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否删除本条数据");
            builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeans = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(FileBeanDao.Properties.DocumentId.eq(dcumentList.get(i).getId()))
                            .list();

                    for (int j = 0; j < fileBeans.size(); j++) {
                        fileBeanDao.deleteByKey(fileBeans.get(j).getUId());
                    }
                    DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                    documentBeanDao.deleteByKey(dcumentList.get(i).getUId());
                    setResume();
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



        fileAdapter = new FileApplyForAdapter(getActivity(), fileBeans);
        lv_conclusion.setAdapter(fileAdapter);

        fileAdapter.setOnDel(new FileApplyForAdapter.OnDel() {
            @Override
            public void onDel(int position) {
                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeans=fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(checkApplyId))
                        .list();
                fileBeanDao.deleteByKey(fileBeans.get(position).getUId());
                setResume();
            }
        });
    }
    private List<FileBean> fileBeans = new ArrayList<>();
    private FileApplyForAdapter fileAdapter;
    private void setResume() {
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> parentIdList = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.Project.eq("验收依据文件"))
                .list();
        parentId = parentIdList.get(0).getId();

        List<DeliveryListBean> deliveryListBeanList1 = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.ParentId.eq(parentId))
                .where(DeliveryListBeanDao.Properties.Project.eq("合同、调度单"))
                .where(DeliveryListBeanDao.Properties.TypeDisplay.eq("管理"))
                .list();


        RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
        List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = relatedDocumentIdSetBeanDao.queryBuilder()
                .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(deliveryListBeanList1.get(0).getId()))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(deliveryListBeanList1.get(0).getId()))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(deliveryListBeanList1.get(0).getId()))
                .list();

        dcumentList.clear();
        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
        for (int i = 0; i < relatedDocumentIdSetBeans.size(); i++) {
            List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                    .where(DocumentBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(DocumentBeanDao.Properties.Id.eq(relatedDocumentIdSetBeans.get(i).getRelatedDocumentId()))
                    .list();
            dcumentList.addAll(documentBeans);
        }

        if (lvFileAdapter != null) {
            lvFileAdapter.notifyDataSetChanged();
        }

        FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
        List<FileBean> fileBeans1=fileBeanDao.queryBuilder()
                .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(FileBeanDao.Properties.DocumentId.eq(checkApplyId))
                .list();
        fileBeans.clear();
        fileBeans.addAll(fileBeans1);
        if (fileAdapter != null) {
            fileAdapter.notifyDataSetChanged();
        }

    }

    private ApplyForFragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_apply_for;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                //添加验收申请
                addPopup(false, 0);
                break;
            case R.id.tv_add_contract:
                //添加。。。合同
                startActivityForResult(AddActivity.openIntent(getActivity(), dataPackageid, "", true, false), 1);
                break;
            case R.id.tv_paizhao:
                this.mFragment=this;
                new LFilePicker()
                        .withSupportFragment(mFragment)
                        .withRequestCode(11)
                        .withStartPath("/storage/emulated/0/数据包")//指定初始显示路径
                        .withIsGreater(true)//过滤文件大小 小于指定大小的文件
                        .withFileSize(0)//指定文件大小为500K
                        .withChooseMode(true)
                        .withMutilyMode(false)
                        .start();
                break;
            case R.id.tv_contractCode:
                setTextContract("合同（调度单）编号", tvContractCode);
                break;
            case R.id.tv_contractName:
                setTextContract("合同（调度单）名称", tvContractName);
                break;
        }
    }

    private void setTextContract(String tetle, TextView textView) {
        final EditText et = new EditText(getActivity());
        new AlertDialog.Builder(getActivity())
                .setTitle(tetle)
                .setView(et)
                .setPositiveButton("确定", (dialog, which) -> {
                    textView.setText(tetle + "：" + et.getText().toString().trim());
                    CheckApplyBeanDao checkApplyBeanDao = MyApplication.getInstances().getCheckApplyDaoSession().getCheckApplyBeanDao();
                    CheckApplyBean checkApplyBean = checkApplyBeanDao.queryBuilder()
                            .where(CheckApplyBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .unique();
                    if (tetle.equals("合同（调度单）编号")) {
                        checkApplyBean.setContractCode(et.getText().toString().trim());
                    } else {
                        checkApplyBean.setContractName(et.getText().toString().trim());
                    }
                    checkApplyBeanDao.update(checkApplyBean);

                })
                .setNegativeButton("取消", null)
                .show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult: " );
        if (requestCode == 11 && data != null) {
            List<String> list = data.getStringArrayListExtra("paths");
            String path = list.get(0);
                if (path != null) {
                    File file = new File(path);
                    if (file.exists()) {
                        String upLoadFilePath = file.toString();
                        String upLoadFileName = file.getName();
                        FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                        String end = upLoadFileName.substring(upLoadFileName.lastIndexOf(".") + 1,
                                upLoadFileName.length()).toLowerCase(Locale.getDefault());
                        String filePath=System.currentTimeMillis()+"."+end;
                        FileUtils.copyFile(upLoadFilePath, (String) SPUtils.get(getActivity(), "path", "") + "/" + filePath);

                        FileBean fileBean2=fileBeanDao.queryBuilder()
                                .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(FileBeanDao.Properties.DocumentId.eq(checkApplyId))
                                .unique();
                        if (fileBean2!=null){
                            FileBean fileBean = new FileBean(fileBean2.getUId(),
                                    dataPackageid,
                                    checkApplyId,
                                    upLoadFileName,
                                    filePath,
                                    "",
                                    "",
                                    "");
                            fileBeanDao.update(fileBean);
                        }else {
                            FileBean fileBean = new FileBean(null,
                                    dataPackageid,
                                    checkApplyId,
                                    upLoadFileName,
                                    filePath,
                                    "",
                                    "",
                                    "");
                            fileBeanDao.insert(fileBean);
                        }


                    }

            }
        }
        setResume();

    }




    private void addPopup(boolean isAdd, int position) {
        View view = getLayoutInflater().inflate(R.layout.popup_add, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);


        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(tv_add, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });
        TextView tv_productCodeName = view.findViewById(R.id.tv_productCodeName);
        TextView tv_productName = view.findViewById(R.id.tv_productName);
        TextView tv_popup_quxiao = view.findViewById(R.id.tv_popup_quxiao);

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

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        tv_popup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

    }
}

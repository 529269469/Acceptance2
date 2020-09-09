package com.example.acceptance2.ui.activity.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.FileAddAdapter;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.OpenFileUtil;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyListView;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_modelCode)
    TextView etModelCode;
    @BindView(R.id.et_productCodeName)
    TextView etProductCodeName;
    @BindView(R.id.et_stage)
    TextView etStage;
    @BindView(R.id.tv_stage)
    TextView tvStage;
    @BindView(R.id.et_approver)
    EditText etApprover;
    @BindView(R.id.tv_approver)
    TextView tvApprover;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rg_)
    RadioGroup rg;
    @BindView(R.id.tv_rg)
    TextView tvRg;
    @BindView(R.id.et_payClassifyName)
    TextView etPayClassifyName;
    @BindView(R.id.tv_payClassifyName)
    TextView tvPayClassifyName;
    @BindView(R.id.et_productCode)
    TextView etProductCode;
    @BindView(R.id.tv_productCode)
    TextView tvProductCode;
    @BindView(R.id.et_secret)
    TextView etSecret;
    @BindView(R.id.tv_secret)
    TextView tvSecret;
    @BindView(R.id.et_techStatus)
    EditText etTechStatus;
    @BindView(R.id.et_approvalDate)
    TextView etApprovalDate;
    @BindView(R.id.tv_approvalDate)
    TextView tvApprovalDate;
    @BindView(R.id.rb_issl_yes)
    RadioButton rbIsslYes;
    @BindView(R.id.rb_issl_no)
    RadioButton rbIsslNo;
    @BindView(R.id.rg_issl)
    RadioGroup rgIssl;
    @BindView(R.id.tv_rg_issl)
    TextView tvRgIssl;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_conclusion)
    EditText etConclusion;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.lv_file)
    MyListView lvFile;
    @BindView(R.id.tv_file2)
    TextView tvFile2;
    @BindView(R.id.lv_file2)
    MyListView lvFile2;
    @BindView(R.id.tv_popup_save)
    TextView tvPopupSave;

    public static Intent openIntent(Context context, String dataPackageid, String id, boolean sort, boolean isAdd) {
        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra("dataPackageid", dataPackageid);
        intent.putExtra("id", id);
        intent.putExtra("sort", sort);
        intent.putExtra("isAdd", isAdd);
        return intent;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add;
    }

    private String dataPackageid;
    private String id;
    private boolean sort;
    private String issl = "true";
    private boolean isAdd;

    private List<FileBean> fileBeans = new ArrayList<>();
    private FileAddAdapter fileAdapter;
    private List<FileBean> fileBeans2 = new ArrayList<>();
    private FileAddAdapter fileAdapter2;
    private String path = (String) SPUtils.get(this, "path", "") + "/";

    @Override
    protected void initDataAndEvent() {
        ivGenduo.setOnClickListener(v -> finish());

        id = getIntent().getStringExtra("id");
        dataPackageid = getIntent().getStringExtra("dataPackageid");
        sort = getIntent().getBooleanExtra("sort", false);
        isAdd = getIntent().getBooleanExtra("isAdd", false);


        etModelCode.setText((String) SPUtils.get(this, "modelCode", ""));
        etProductCodeName.setText((String) SPUtils.get(this, "productCode", ""));

        if (isAdd) {
            setResume();
        }
        rgIssl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_issl_yes:
                        issl = "true";
                        break;
                    case R.id.rb_issl_no:
                        issl = "false";
                        break;
                }
            }
        });

        if (sort) {
            //管理
            rbYes.setChecked(true);
            rbNo.setChecked(false);
        } else {
            //技术
            rbNo.setChecked(true);
            rbYes.setChecked(false);
        }


        fileAdapter = new FileAddAdapter(this, fileBeans);
        lvFile.setAdapter(fileAdapter);

        fileAdapter2 = new FileAddAdapter(this, fileBeans2);
        lvFile2.setAdapter(fileAdapter2);

        lvFile2.setOnItemClickListener((adapterView, view, i, l) -> {
            File file = new File(path + fileBeans2.get(i).getPath());
            if (file.isFile() && file.exists()) {
                try {
                    startActivity(OpenFileUtil.openFile(path + fileBeans2.get(i).getPath()));
                } catch (Exception o) {
                }
            }
        });

        lvFile.setOnItemClickListener((adapterView, view, i, l) -> {
            File file = new File(path + fileBeans.get(i).getPath());
            if (file.isFile() && file.exists()) {
                try {
                    startActivity(OpenFileUtil.openFile(path + fileBeans.get(i).getPath()));
                } catch (Exception o) {
                }
            }
        });
        fileAdapter.setOnDel(new FileAddAdapter.OnDel() {
            @Override
            public void onDel(int position) {
                fileBeans.remove(position);
                fileAdapter.notifyDataSetChanged();
            }
        });

        fileAdapter2.setOnDel(new FileAddAdapter.OnDel() {
            @Override
            public void onDel(int position) {
                fileBeans2.remove(position);
                fileAdapter2.notifyDataSetChanged();
            }
        });

    }


    private void setResume() {
        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
        List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                .where(DocumentBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DocumentBeanDao.Properties.Id.eq(id))
                .list();
        DocumentBean documentBean = documentBeans.get(0);
        etName.setText(documentBean.getName());
        etStage.setText(documentBean.getStage());
        etApprover.setText(documentBean.getApprover());
        etPayClassifyName.setText(documentBean.getPayClassifyName());

        etSecret.setText(documentBean.getSecret());
        etTechStatus.setText(documentBean.getTechStatus());
        etApprovalDate.setText(documentBean.getApprovalDate());
        etDescription.setText(documentBean.getDescription());
        etConclusion.setText(documentBean.getConclusion());


//        String[] productCodes=documentBean.getProductCode().split(",");
//
//        StringBuffer productCodeBuffer=new StringBuffer();
//        for (int i = 0; i < productCodes.length; i++) {
//            ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
//            List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
//                    .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
//                    .where(ApplyItemBeanDao.Properties.Id.eq(productCodes[i]))
//                    .list();
//            if (applyItemBeans!=null&&!applyItemBeans.isEmpty()){
//                productCodeBuffer.append(applyItemBeans.get(0).getProductCode()).append(",");
//            }
//        }
//
//        if (!StringUtils.isBlank(productCodeBuffer.toString())){
//            etProductCode.setText(productCodeBuffer.toString().substring(0,productCodeBuffer.length()-1));
//        }


        productcode = documentBean.getProductCode();
        etProductCode.setText(productcode);

        //晒蓝
        if (!StringUtils.isBlank(documentBean.getIssl())) {
            if (documentBean.getIssl().equals("true")) {
                issl = "true";
                rbIsslYes.setChecked(true);
                rbIsslNo.setChecked(false);
            } else {
                issl = "false";
                rbIsslYes.setChecked(false);
                rbIsslNo.setChecked(true);
            }
        }


        fileBeans.clear();
        fileBeans2.clear();

        FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
        List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(FileBeanDao.Properties.DocumentId.eq(id))
                .where(FileBeanDao.Properties.Type.eq("主内容"))
                .list();

        List<FileBean> fileBeanList2 = fileBeanDao.queryBuilder()
                .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(FileBeanDao.Properties.DocumentId.eq(id))
                .where(FileBeanDao.Properties.Type.eq("附件"))
                .list();

        fileBeans.addAll(fileBeanList);
        fileBeans2.addAll(fileBeanList2);
        if (fileAdapter != null) {
            fileAdapter.notifyDataSetChanged();
        }
        if (fileAdapter2 != null) {
            fileAdapter2.notifyDataSetChanged();
        }
    }


    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    String productcode = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_stage://阶段
                String[] stage = new String[]{"方案阶段(M)", "初样阶段(C)", "C1", "C2", "试样阶段(S)",
                        "S1", "S2", "Sd", "正样阶段(Z)", "定型阶段(D)",
                        "工艺定型阶段(G)", "批产阶段(P)", "可行性研究阶段(KXX)"};
                setDialog(etStage, stage, "请选择阶段：");
                break;
            case R.id.et_productCode://产品编号
                ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                        .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();
                String[] productCode = new String[applyItemBeans.size()];
                for (int i = 0; i < productCode.length; i++) {
                    productCode[i] = applyItemBeans.get(i).getProductCode();
                }
                setDialog(etProductCode, productCode, "请选择产品编号：");

                break;
            case R.id.et_secret://密级
                String[] secret = new String[]{"非密", "内部", "秘密", "机密"};
                setDialog(etSecret, secret, "请选择密级：");
                break;
            case R.id.et_payClassifyName://交付类别
                if (!isAdd) {
                    if (sort) {
                        String[] payClassifyName = new String[]{"合同、调度单"};
                        setDialog(etPayClassifyName, payClassifyName, "请选择交付类别：");
                    } else {
                        String[] payClassifyName = new String[]{"任务书（或技术要求）", "验收大纲", "验收细节", "鉴定细节", "技术条件", "技术图样"};
                        setDialog(etPayClassifyName, payClassifyName, "请选择交付类别：");
                    }
                }
                break;
            case R.id.et_approvalDate://批准日期
                new DatePickerDialog(this, 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etApprovalDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH))
                        .show();

                break;
            case R.id.tv_file://主要文件
                new LFilePicker()
                        .withActivity(this)
                        .withRequestCode(11)
                        .withStartPath("/storage/emulated/0/数据包")//指定初始显示路径
                        .withIsGreater(true)//过滤文件大小 小于指定大小的文件
                        .withFileSize(0)//指定文件大小为500K
                        .withChooseMode(true)
                        .withMutilyMode(false)
                        .start();
                break;
            case R.id.tv_file2://附件
                new LFilePicker()
                        .withActivity(this)
                        .withRequestCode(22)
                        .withStartPath("/storage/emulated/0/数据包")//指定初始显示路径
                        .withIsGreater(true)//过滤文件大小 小于指定大小的文件
                        .withFileSize(0)//指定文件大小为500K
                        .withChooseMode(true)
                        .withMutilyMode(false)
                        .start();
                break;
            case R.id.tv_popup_save://保存
                if (StringUtils.isBlank(etName.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "名称不能为空");
                    return;
                }
                if (StringUtils.isBlank(etPayClassifyName.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "交付类别不能为空");
                    return;
                }

                if (StringUtils.isBlank(etProductCode.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "产品编号不能为空");
                    return;
                }

                if (StringUtils.isBlank(etSecret.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "密级不能为空");
                    return;
                }

                if (StringUtils.isBlank(etStage.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "阶段不能为空");
                    return;
                }

                if (StringUtils.isBlank(etApprover.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "批准人不能为空");
                    return;
                }
                if (StringUtils.isBlank(etApprovalDate.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(this, "批准日期不能为空");
                    return;
                }

                if (fileBeans.isEmpty()) {
                    ToastUtils.getInstance().showTextToast(this, "请添加主要文件");
                    return;
                }
                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();


                String documentId = System.currentTimeMillis() + "";

                DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
                List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                        .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(DeliveryListBeanDao.Properties.IsParent.eq("false"))
                        .where(DeliveryListBeanDao.Properties.Project.eq(etPayClassifyName.getText().toString().trim()))
                        .list();

                if (isAdd) {
                    List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                            .where(DocumentBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(DocumentBeanDao.Properties.Id.eq(id))
                            .list();

                    DocumentBean documentBean = new DocumentBean(documentBeans.get(0).getUId(),
                            dataPackageid,
                            id,
                            documentBeans.get(0).getCode(),
                            etName.getText().toString().trim(),
                            etSecret.getText().toString().trim(),
                            documentBeans.get(0).getPayClassify(),
                            etPayClassifyName.getText().toString().trim(),
                            (String) SPUtils.get(this, "modelCode", ""),
                            (String) SPUtils.get(this, "productCode", ""),
                            productcode,
                            etStage.getText().toString().trim(),
                            etTechStatus.getText().toString().trim(),
                            etApprover.getText().toString().trim(),
                            etApprovalDate.getText().toString().trim(),
                            issl,
                            etConclusion.getText().toString().trim(),
                            etDescription.getText().toString().trim(),
                            documentBeans.get(0).getOnLine(),
                            documentBeans.get(0).getInfoUrl(),
                            documentBeans.get(0).getUniqueValue());
                    documentBeanDao.update(documentBean);

                } else {
                    RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                    RelatedDocumentIdSetBean relatedDocumentIdSetBean = new RelatedDocumentIdSetBean(null,
                            dataPackageid,
                            deliveryListBeans.get(0).getId(),
                            deliveryListBeans.get(0).getId(),
                            deliveryListBeans.get(0).getId(),
                            documentId);
                    relatedDocumentIdSetBeanDao.insert(relatedDocumentIdSetBean);

                    List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                            .where(DocumentBeanDao.Properties.DataPackageId.eq((String) SPUtils.get(this, "id", "")))
                            .list();

                    String code = "";
                    if (documentBeans != null && !documentBeans.isEmpty()) {
                        String strThem = documentBeans.get(documentBeans.size() - 1).getCode();
                        String strThem2 = strThem.substring(strThem.length() - 3, strThem.length());

                        DecimalFormat decimalFormat = new DecimalFormat("000");
                        String string2 = decimalFormat.format(Integer.parseInt(strThem2) + 1);
                        code = (String) SPUtils.get(this, "code", "") + "-D" + string2;
                    } else {
                        code = (String) SPUtils.get(this, "code", "") + "-D001";
                    }
                    DocumentBean documentBean = new DocumentBean(null,
                            dataPackageid,
                            documentId,
                            code,
                            etName.getText().toString().trim(),
                            etSecret.getText().toString().trim(),
                            deliveryListBeans.get(0).getId(),
                            etPayClassifyName.getText().toString().trim(),
                            (String) SPUtils.get(this, "modelCode", ""),
                            (String) SPUtils.get(this, "productCode", ""),
                            productcode,
                            etStage.getText().toString().trim(),
                            etTechStatus.getText().toString().trim(),
                            etApprover.getText().toString().trim(),
                            etApprovalDate.getText().toString().trim(),
                            issl,
                            etConclusion.getText().toString().trim(),
                            etDescription.getText().toString().trim(),
                            "false",
                            "",
                            UUID.randomUUID().toString());
                    documentBeanDao.insert(documentBean);

                }

                FileBeanDao fileBeanDaoSave = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList1 = fileBeanDaoSave.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(id))
                        .where(FileBeanDao.Properties.Type.eq("主内容"))
                        .list();
                for (int i = 0; i < fileBeanList1.size(); i++) {
                    fileBeanDaoSave.deleteByKey(fileBeanList1.get(i).getUId());
                }
                String end = fileBeans.get(0).getName().substring(fileBeans.get(0).getName().lastIndexOf(".") + 1,
                        fileBeans.get(0).getName().length()).toLowerCase(Locale.getDefault());
                String filePath = System.currentTimeMillis() + "." + end;
                FileBean fileBean11 = new FileBean(null,
                        dataPackageid,
                        isAdd ? id : documentId,
                        fileBeans.get(0).getName(),
                        filePath,
                        "主内容",
                        fileAdapter.getList().get(0).getSecret(),
                        fileAdapter.getList().get(0).getDisabledSecret());
                fileBeanDaoSave.insert(fileBean11);

                File oldFile = new File(fileBeans.get(0).getPath());
                if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                    FileUtils.copyFile(path + fileBeans.get(0).getPath(), path + filePath);
                } else {
                    FileUtils.copyFile(fileBeans.get(0).getPath(), path + filePath);
                }

                for (int i = 0; i < fileBeanList1.size(); i++) {
                    FileUtils.delFile(path + fileBeanList1.get(i).getPath());
                }

                List<FileBean> fileBeanList2 = fileBeanDaoSave.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(id))
                        .where(FileBeanDao.Properties.Type.eq("附件"))
                        .list();
                for (int i = 0; i < fileBeanList2.size(); i++) {
                    fileBeanDaoSave.deleteByKey(fileBeanList2.get(i).getUId());
                }
                for (int i = 0; i < fileBeans2.size(); i++) {
                    String end2 = fileBeans2.get(0).getName().substring(fileBeans2.get(0).getName().lastIndexOf(".") + 1,
                            fileBeans2.get(0).getName().length()).toLowerCase(Locale.getDefault());
                    String filePath2 = System.currentTimeMillis() + "." + end2;
                    FileBean fileBean = new FileBean(null,
                            dataPackageid,
                            isAdd ? id : documentId,
                            fileBeans2.get(i).getName(),
                            filePath2,
                            "附件",
                            fileAdapter2.getList().get(i).getSecret(),
                            fileAdapter2.getList().get(i).getDisabledSecret());
                    fileBeanDaoSave.insert(fileBean);
                    File oldFile2 = new File(fileBeans2.get(0).getPath());
                    if (!oldFile2.exists() || !oldFile2.isFile() || !oldFile2.canRead()) {
                        FileUtils.copyFile(path + fileBeans2.get(0).getPath(), path + filePath2);
                    } else {
                        FileUtils.copyFile(fileBeans2.get(0).getPath(), path + filePath2);
                    }
                }
                for (int i = 0; i < fileBeanList2.size(); i++) {
                    FileUtils.delFile(path + fileBeanList2.get(i).getPath());
                }

                ToastUtils.getInstance().showTextToast(this, "保存成功");
                Intent intent3 = getIntent();
                setResult(RESULT_OK, intent3);
                finish();
                break;
        }
    }

    private void setDialog(TextView textView, String[] dish, String title) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle(title);
        int dishPos = 0;
        builder2.setSingleChoiceItems(dish, dishPos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(dish[which]);
                dialog.dismiss();
                if ("请选择产品编号：".equals(title)) {
                    ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                    List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                            .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .list();
                    productcode = applyItemBeans.get(which).getId();
                }
            }
        });
        builder2.setNegativeButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && data != null) {
            List<String> list = data.getStringArrayListExtra("paths");
            String path = list.get(0);
            if (path != null) {
                File file = new File(path);
                if (file.exists()) {
                    String upLoadFilePath = file.toString();
                    String upLoadFileName = file.getName();
                    Log.e("TAG", "upLoadFilePath: " + upLoadFilePath);
                    Log.e("TAG", "upLoadFileName: " + upLoadFileName);
                    fileBeans.clear();
                    fileBeans.add(new FileBean((long) 0, "", "", upLoadFileName, upLoadFilePath, "主内容", "非密", ""));
                    fileAdapter.notifyDataSetChanged();
                }
            }
        } else if (requestCode == 22 && data != null) {
            List<String> list = data.getStringArrayListExtra("paths");
            String path = list.get(0);
            if (path != null) {
                File file = new File(path);
                if (file.exists()) {
                    String upLoadFilePath = file.toString();
                    String upLoadFileName = file.getName();
                    Log.e("TAG", "upLoadFilePath: " + upLoadFilePath);
                    Log.e("TAG", "upLoadFileName: " + upLoadFileName);
                    fileBeans2.add(new FileBean((long) 0, "", "", upLoadFileName, upLoadFilePath, "附件", "非密", ""));
                    fileAdapter2.notifyDataSetChanged();

                }
            }
        }

    }
}

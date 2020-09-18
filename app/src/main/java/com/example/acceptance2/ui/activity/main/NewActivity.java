package com.example.acceptance2.ui.activity.main;

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
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.MoAdapter;
import com.example.acceptance2.base.BaseActivity;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.bean.DataPackageBean;
import com.example.acceptance2.bean.PackageBean;
import com.example.acceptance2.greendao.bean.AcceptDeviceBean;
import com.example.acceptance2.greendao.bean.ApplyDeptBean;
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
import com.example.acceptance2.greendao.bean.PropertyBean;
import com.example.acceptance2.greendao.bean.PropertyBeanX;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
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
import com.example.acceptance2.greendao.db.UnresolvedBeanDao;
import com.example.acceptance2.ui.MainActivity;
import com.example.acceptance2.utils.CompressOperate_zip4j;
import com.example.acceptance2.utils.DataUtils;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.utils.ZipUtils2;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_acceptorUnit)
    EditText tvAcceptorUnit;
    @BindView(R.id.tv_applyCompany)
    EditText tvApplyCompany;
    @BindView(R.id.tv_modelSeriesName)
    EditText tvModelSeriesName;
    @BindView(R.id.tv_modelCode)
    EditText tvModelCode;
    @BindView(R.id.tv_productCode)
    EditText tvProductCode;
    @BindView(R.id.tv_productName)
    EditText tvProductName;
    @BindView(R.id.tv_productType)
    TextView tvProductType;
    @BindView(R.id.tv_batch)
    EditText tvBatch;
    @BindView(R.id.tv_stage)
    TextView tvStage;
    @BindView(R.id.tv_moban)
    TextView tvMoban;
    @BindView(R.id.bt_yes)
    TextView btYes;
    @BindView(R.id.progressBar)
    RelativeLayout progressBar;

    private String dataPackageid;

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
                    progressBar.setVisibility(View.GONE);
                    startActivity(MainActivity.openIntent(NewActivity.this, dataPackageid));
                    finish();
                    break;
                case 3:
                    progressBar.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(NewActivity.this,"模板错误");
                    break;
            }

        }
    };
    private String code;


    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, NewActivity.class);
        return intent;
    }

    @Override
    protected void initView() {
        ivGenduo.setOnClickListener(this);
        tvProductType.setOnClickListener(this);
        tvMoban.setOnClickListener(this);
        btYes.setOnClickListener(this);
        tvStage.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_genduo:
                finish();
                break;
            case R.id.tv_productType:
                setDialog2();
                break;
            case R.id.tv_moban:
                List<PackageBean> list = new ArrayList<>();
                File files = new File(Environment.getExternalStorageDirectory() + "/模板");
                if(!files.exists()){
                    try {
                        files.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                File[] subFile = files.listFiles();
                if (subFile!=null){
                    for (int i = 0; i < subFile.length; i++) {
                        if (!subFile[i].exists() || subFile[i].length() == 0) {
                            ToastUtils.getInstance().showTextToast(this, "模板错误");
                            return;
                        }
                        String filename = subFile[i].getName();
                        File file = new File(filename);
                        /* 取得扩展名 */
                        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
                        if (end.equals("zip")) {
                            list.add(new PackageBean(filename, subFile[i] + ""));
                        }
                    }
                    String[] productCode = new String[list.size()];
                    for (int i = 0; i < productCode.length; i++) {
                        productCode[i] = list.get(i).getName();
                    }
                    setDialog(tvMoban, productCode, "请选择模板：");
                }else {
                    ToastUtils.getInstance().showTextToast(this,"你没有模板，请先导入模板");
                }
                break;
            case R.id.bt_yes:
                newisPkg();
                break;
            case R.id.tv_stage:
                String[] stage = new String[]{"方案阶段(M)", "初样阶段(C)", "C1", "C2", "试样阶段(S)",
                        "S1", "S2", "Sd", "正样阶段(Z)", "定型阶段(D)",
                        "工艺定型阶段(G)", "批产阶段(P)", "可行性研究阶段(KXX)"};
                setDialog(tvStage, stage, "请选择阶段：");
                break;

        }
    }

    String text = "";
    String text2 = "";

    private void setDialog2() {
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
                text = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
                text2 = stringBuffer2.toString().substring(0, stringBuffer2.toString().length() - 1);
                tvProductType.setText(text);
            }
            dialog.dismiss();
        });
        builder2.setNegativeButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();

    }

    private String Name;
    private String AcceptorUnit;
    private String ApplyCompany;
    private String ProductCode;
    private String ProductName;
    private String ProductType;
    private String Batch;
    private String Stage;
    private String Moban;

    private void newisPkg() {
        Name = tvName.getText().toString().trim();
        AcceptorUnit = tvAcceptorUnit.getText().toString().trim();
        ApplyCompany = tvApplyCompany.getText().toString().trim();
        ProductCode = tvProductCode.getText().toString().trim();
        ProductName = tvProductName.getText().toString().trim();
        ProductType = tvProductType.getText().toString().trim();
        Batch = tvBatch.getText().toString().trim();
        Stage = tvStage.getText().toString().trim();
        Moban = tvMoban.getText().toString().trim();
        if (StringUtils.isBlank(Name)) {
            ToastUtils.getInstance().showTextToast(this, "名称不能为空");
            return;
        }
        if (StringUtils.isBlank(AcceptorUnit)) {
            ToastUtils.getInstance().showTextToast(this, "验收单位（甲方）不能为空");
            return;
        }
        if (StringUtils.isBlank(ApplyCompany)) {
            ToastUtils.getInstance().showTextToast(this, "申请单位（乙方）不能为空");
            return;
        }
        if (StringUtils.isBlank(ProductCode)) {
            ToastUtils.getInstance().showTextToast(this, "产品代号不能为空");
            return;
        }
        if (StringUtils.isBlank(ProductName)) {
            ToastUtils.getInstance().showTextToast(this, "产品名称不能为空");
            return;
        }
        if (StringUtils.isBlank(ProductType)) {
            ToastUtils.getInstance().showTextToast(this, "产品类别不能为空");
            return;
        }

        if (StringUtils.isBlank(Batch)) {
            ToastUtils.getInstance().showTextToast(this, "批次不能为空");
            return;
        }
        if (StringUtils.isBlank(Stage)) {
            ToastUtils.getInstance().showTextToast(this, "阶段标记不能为空");
            return;
        }
        if (StringUtils.isBlank(Moban)) {
            ToastUtils.getInstance().showTextToast(this, "数据包模板不能为空");
            return;
        }
        newPkg();
    }

    private void newPkg() {
        code = "W-Pkg-YS-" + tvProductCode.getText().toString().trim() + "-" + tvStage.getText().toString().trim() + "-" + System.currentTimeMillis();
        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
        File file = new File(Environment.getExternalStorageDirectory() + "/数据包/" + code);
        if (!file.exists()) {
            file.mkdirs();
        }
        dataPackageid = System.currentTimeMillis() + "";

        DataPackageDBean dataPackageDBean = new DataPackageDBean(null,
                code,
                Environment.getExternalStorageDirectory() + "/数据包/" + code,
                dataPackageid,
                tvName.getText().toString().trim(),
                code,
                "交付验收包",
                "",
                tvModelCode.getText().toString().trim(),
                tvProductName.getText().toString().trim(),
                tvProductCode.getText().toString().trim(),
                tvProductType.getText().toString().trim(),
                tvBatch.getText().toString().trim(),
                (String) SPUtils.get(this, "username", ""),
                DataUtils.getData(),
                "",
                tvModelSeriesName.getText().toString().trim(),
                "",
                "",
                "",
                "",
                "",
                "",
                "false",
                "",
                text2,
                tvApplyCompany.getText().toString().trim(),
                tvAcceptorUnit.getText().toString().trim(),
                tvStage.getText().toString().trim(),
                UUID.randomUUID() + "",
                "A.1","1");
        dataPackageDBeanDao.insert(dataPackageDBean);

        if (!StringUtils.isBlank(tvMoban.getText().toString().trim())) {

            handler.sendEmptyMessage(1);
            new Thread() {
                @Override
                public void run() {

                    CompressOperate_zip4j compressOperate_zip4j = new CompressOperate_zip4j();
                    compressOperate_zip4j.uncompressZip4j(Environment.getExternalStorageDirectory() + "/模板/" + Moban,
                            Environment.getExternalStorageDirectory() + "/数据包/" + code,
                            "casic12345");

                    int eee = compressOperate_zip4j.uncompressZip4j(Environment.getExternalStorageDirectory() + "/数据包/" + code+"/"+Moban,
                            Environment.getExternalStorageDirectory() + "/数据包/" + code,
                            "casic12345");
                    //casic12345
                    Log.e("TAG", "eee: " + eee);
                    if (eee == 0) {
                       FileUtils.delFile(Environment.getExternalStorageDirectory() + "/数据包/" + code+"/"+Moban);
                        filePath(Environment.getExternalStorageDirectory() + "/数据包/" + code);
                        handler.sendEmptyMessage(2);
                    } else {
                        FileUtils.delFile(Environment.getExternalStorageDirectory() + "/数据包/" + code);
                        handler.sendEmptyMessage(3);
                    }


                }
            }.start();


        }


    }

    private void filePath(String upLoadFile) {
        File files = new File(upLoadFile);
        File[] subFile = files.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            String filename = subFile[i].getName();
            if (filename.equals("DataPackage.xml")) {
                input(subFile[i]);
                input2();
                break;
            }

        }
    }

    /**
     * 模板没有的
     */
    private void input2() {
        CheckApplyBeanDao checkApplyBeanDao = MyApplication.getInstances().getCheckApplyDaoSession().getCheckApplyBeanDao();
        CheckApplyBean checkApplyBean = new CheckApplyBean(null,
                dataPackageid,
                System.currentTimeMillis() + "",
                Name + "验收申请",
                code + "-SQ",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "CHECKAPPLY",
                "",
                "",
                "");
        checkApplyBeanDao.insert(checkApplyBean);

        CheckTaskBeanDao checkTaskBeanDao = MyApplication.getInstances().getCheckTaskDaoSession().getCheckTaskBeanDao();
        CheckTaskBean checkTaskBean = new CheckTaskBean(null,
                dataPackageid,
                System.currentTimeMillis() + "",
                Name + "验收任务单",
                code + "-RW",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "CHECKTASK");
        checkTaskBeanDao.insert(checkTaskBean);

        CheckVerdBeanDao checkVerdBeanDao = MyApplication.getInstances().getCheckVerdDaoSession().getCheckVerdBeanDao();
        CheckVerdBean checkVerdBean = new CheckVerdBean(null,
                dataPackageid,
                System.currentTimeMillis() + "",
                Name + "验收结论",
                code + "-JL",
                "",
                "",
                "",
                "",
                "",
                "CHECKCONCLU",
                "",
                "",
                "");
        checkVerdBeanDao.insert(checkVerdBean);

        CheckUnresolvedBeanDao checkUnresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getCheckUnresolvedBeanDao();
        CheckUnresolvedBean checkUnresolvedBean = new CheckUnresolvedBean(null,
                dataPackageid,
                System.currentTimeMillis() + "",
                Name + "验收遗留问题落实",
                code + "-YL",
                "CHECKUNRESOLED");
        checkUnresolvedBeanDao.insert(checkUnresolvedBean);

    }

    /**
     * 读取模板
     *
     * @param subFile
     */
    private void input(File subFile) {
        String content = "";
        try {
            InputStream instream = new FileInputStream(subFile);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            //分行读取
            while ((line = buffreader.readLine()) != null) {
                content += line + "\n";
            }
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XStream xStream = new XStream();
        xStream.processAnnotations(DataPackageBean.class);
        DataPackageBean dataPackageBean = (DataPackageBean) xStream.fromXML(content);

        /**
         *  检查菜单
         * */
        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
        CheckItemRelateBeanDao checkItemRelateBeanDao = MyApplication.getInstances().getCheckItemRelateDaoSession().getCheckItemRelateBeanDao();
        PropertyBeanDao propertyBeanDao = MyApplication.getInstances().getPropertyDaoSession().getPropertyBeanDao();
        CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
        PropertyBeanXDao propertyBeanXDao = MyApplication.getInstances().getPropertyXDaoSession().getPropertyBeanXDao();
        RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
        for (int i = 0; i < dataPackageBean.getCheckFileSet().getCheckFile().size(); i++) {
            boolean isBaocun = false;
            String[] textThem = text2.split(",");
            if (!StringUtils.isBlank(dataPackageBean.getCheckFileSet().getCheckFile().get(i).getProductTypeValue())) {
                String[] textThem2 = dataPackageBean.getCheckFileSet().getCheckFile().get(i).getProductTypeValue().split(",");
                for (int j = 0; j < textThem.length; j++) {
                    for (int k = 0; k < textThem2.length; k++) {
                        if (textThem[j].equals(textThem2[k])) {
                            isBaocun = true;
                            break;
                        }
                    }
                }
            }
            if (!StringUtils.isBlank(dataPackageBean.getCheckFileSet().getCheckFile().get(i).getDocType()) &&
                    "CHECKACCORD".equals(dataPackageBean.getCheckFileSet().getCheckFile().get(i).getDocType())) {
                isBaocun = true;
            }
            DecimalFormat decimalFormat = new DecimalFormat("00");
            String string2 = decimalFormat.format(i + 1);
            if (isBaocun) {
                CheckFileBean checkFileBean = new CheckFileBean(null,
                        dataPackageid,
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getName(),
                        code + "-" + string2,
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getDocType(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getProductType(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getConclusion(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckPerson(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckDate(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getSortBy(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckTime(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getSort(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getTabsName(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getAccordFile(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getSelectEdit(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getUniqueValue(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getProductTypeValue(),
                        dataPackageBean.getCheckFileSet().getCheckFile().get(i).getDescription());
                checkFileBeanDao.insert(checkFileBean);

                try {
                    for (int j = 0; j < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().size(); j++) {
                        try {
                            CheckGroupBean checkGroupBean = new CheckGroupBean(null,
                                    dataPackageid,
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getGroupName(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckGroupConclusion(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckPerson(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getIsConclusion(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getIsTable(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getUniqueValue(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckTime(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getConclusionF(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckPersonF(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getSort(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckTimeF(),
                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getTestTable());
                            checkGroupBeanDao.insert(checkGroupBean);
                        } catch (Exception o) {

                        }

                        try {

                            for (int k = 0; k < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemRelateSet().getCheckItemRelate().size(); k++) {
                                try {
                                    CheckItemRelateBean checkItemRelateBean = new CheckItemRelateBean(null,
                                            dataPackageid,
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemRelateSet().getCheckItemRelate().get(k).getTargetId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemRelateSet().getCheckItemRelate().get(k).getRelateItemId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemRelateSet().getCheckItemRelate().get(k).getSelected());

                                    checkItemRelateBeanDao.insert(checkItemRelateBean);
                                } catch (Exception o) {

                                }
                            }


                        } catch (Exception o) {

                        }

                        try {
                            for (int k = 0; k < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getPropertySet().getProperty().size(); k++) {
                                try {
                                    PropertyBean propertyBean = new PropertyBean(null,
                                            dataPackageid,
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getPropertySet().getProperty().get(k).getName(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getPropertySet().getProperty().get(k).getValue());
                                    propertyBeanDao.insert(propertyBean);
                                } catch (Exception o) {

                                }
                            }
                        } catch (Exception o) {

                        }


                        try {

                            for (int k = 0; k < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().size(); k++) {
                                try {
                                    CheckItemBean checkItemBean = new CheckItemBean(null,
                                            dataPackageid,
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getId(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getName(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getOptions(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getSelected(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getUniqueValue(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getSort(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getDescription(),
                                            dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getRelate(),
                                            false);
                                    checkItemBeanDao.insert(checkItemBean);
                                } catch (Exception o) {

                                }
                                try {
                                    for (int l = 0; l < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getPropertySet().getProperty().size(); l++) {
                                        try {
                                            PropertyBeanX propertyBeanX = new PropertyBeanX(null,
                                                    dataPackageid,
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getPropertySet().getProperty().get(l).getName(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getPropertySet().getProperty().get(l).getValue());
                                            propertyBeanXDao.insert(propertyBeanX);
                                        } catch (Exception o) {

                                        }
                                    }
                                } catch (Exception o) {

                                }
                                try {
                                    for (int l = 0; l < dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getRelatedDocumentIdSet().getRelatedDocumentId().size(); l++) {
                                        try {
                                            RelatedDocumentIdSetBean relatedDocumentIdSetBean = new RelatedDocumentIdSetBean(null,
                                                    dataPackageid,
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getId(),
                                                    dataPackageBean.getCheckFileSet().getCheckFile().get(i).getCheckGroupSet().getCheckGroup().get(j).getCheckItemSet().getCheckItem().get(k).getRelatedDocumentIdSet().getRelatedDocumentId().get(l));
                                            relatedDocumentIdSetBeanDao.insert(relatedDocumentIdSetBean);
                                        } catch (Exception o) {

                                        }
                                    }
                                } catch (Exception o) {

                                }


                            }

                        } catch (Exception o) {

                        }


                    }

                } catch (Exception o) {

                }
            }

        }

        /**
         *  交付清单
         * */
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        for (int i = 0; i < dataPackageBean.getDeliveryLists().getDeliveryList().size(); i++) {
            try {
                DeliveryListBean deliveryListBean = new DeliveryListBean(null,
                        dataPackageid,
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getId(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getIsParent(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getProject(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getParentId(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getUniqueValue(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getTypeDisplay(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getSortBy(),
                        dataPackageBean.getDeliveryLists().getDeliveryList().get(i).getSort());
                deliveryListBeanDao.insert(deliveryListBean);
            } catch (Exception o) {

            }
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
            }
        });
        builder2.setPositiveButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();
    }


}

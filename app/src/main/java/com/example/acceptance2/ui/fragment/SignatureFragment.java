package com.example.acceptance2.ui.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.acceptance2.R;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.CheckFileBean;
import com.example.acceptance2.greendao.bean.CheckGroupBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.db.CheckFileBeanDao;
import com.example.acceptance2.greendao.db.CheckGroupBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.view.LinePathView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 过程检查——电气产品——元器件，原材料，标准件检查
 */

public class SignatureFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.et_conclusion)
    EditText etConclusion;
    @BindView(R.id.iv_checkPerson2)
    ImageView ivCheckPerson2;
    @BindView(R.id.tv_signature)
    EditText tvSignature;
    @BindView(R.id.iv_XX)
    ImageView iv_XX;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private String dataPackageid;
    private String checkFileId;


    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        checkFileId = getArguments().getString("checkFileId");


        addData();
        tvSignature.addTextChangedListener(textWatcher);
        etConclusion.addTextChangedListener(textWatcher);
        ivCheckPerson2.setOnClickListener(this);

        iv_XX.setOnClickListener(this);
        tvTime.setOnClickListener(this);
    }

    private String docType = "";

    private void addData() {
        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
        List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                .list();


            FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
            List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                    .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(FileBeanDao.Properties.DocumentId.eq(checkFileId))
                    .list();

            if (!fileBeanList.isEmpty()) {
                if (StringUtils.isBlank(fileBeanList.get(0).getPath())) {
                    iv_XX.setVisibility(View.GONE);
                } else {
                    iv_XX.setVisibility(View.VISIBLE);
                }
                Glide.with(getActivity())
                        .load(new File(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList.get(0).getPath()))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivCheckPerson2);
            }

            tvSignature.setText(checkFileBeans.get(0).getCheckPerson());
            etConclusion.setText(checkFileBeans.get(0).getConclusion());
            tvTime.setText(checkFileBeans.get(0).getCheckTime());


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signature;
    }

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
                CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                        .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                        .list();
                CheckFileBean checkFileBean = new CheckFileBean(checkFileBeans.get(0).getUId(),
                        checkFileBeans.get(0).getDataPackageId(),
                        checkFileBeans.get(0).getId(),
                        checkFileBeans.get(0).getName(),
                        checkFileBeans.get(0).getCode(),
                        checkFileBeans.get(0).getDocType(),
                        checkFileBeans.get(0).getProductType(),
                        etConclusion.getText().toString().trim(),
                        tvSignature.getText().toString().trim(),
                        checkFileBeans.get(0).getCheckDate(),
                        checkFileBeans.get(0).getSortBy(),
                        checkFileBeans.get(0).getCheckTime(),
                        checkFileBeans.get(0).getSort(),
                        checkFileBeans.get(0).getTabsName(),
                        checkFileBeans.get(0).getAccordFile(),
                        checkFileBeans.get(0).getSelectEdit(),
                        checkFileBeans.get(0).getUniqueValue(),
                        checkFileBeans.get(0).getProductTypeValue(),
                        checkFileBeans.get(0).getDescription());
                checkFileBeanDao.update(checkFileBean);
            }

        }
    };
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_checkPerson2:
                pathPopu(ivCheckPerson2);
                break;
            case R.id.iv_XX:
                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(checkFileId))
                        .list();
                Glide.with(getActivity())
                        .load("")
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivCheckPerson2);
                if (fileBeanList != null && !fileBeanList.isEmpty()) {
                    FileUtils.delFile(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList.get(0).getPath());
                    FileBean fileBean = new FileBean(fileBeanList.get(0).getUId(),
                            dataPackageid,
                            checkFileId,
                            "",
                            "",
                            "主内容",
                            "非密", "");
                    fileBeanDao.update(fileBean);
                }
                iv_XX.setVisibility(View.GONE);
                break;
            case R.id.tv_time:
                new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                        List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                                .list();
                        CheckFileBean checkFileBean = new CheckFileBean(checkFileBeans.get(0).getUId(),
                                checkFileBeans.get(0).getDataPackageId(),
                                checkFileBeans.get(0).getId(),
                                checkFileBeans.get(0).getName(),
                                checkFileBeans.get(0).getCode(),
                                checkFileBeans.get(0).getDocType(),
                                checkFileBeans.get(0).getProductType(),
                                etConclusion.getText().toString().trim(),
                                tvSignature.getText().toString().trim(),
                                checkFileBeans.get(0).getCheckDate(),
                                checkFileBeans.get(0).getSortBy(),
                                tvTime.getText().toString().trim(),
                                checkFileBeans.get(0).getSort(),
                                checkFileBeans.get(0).getTabsName(),
                                checkFileBeans.get(0).getAccordFile(),
                                checkFileBeans.get(0).getSelectEdit(),
                                checkFileBeans.get(0).getUniqueValue(),
                                checkFileBeans.get(0).getProductTypeValue(),
                                checkFileBeans.get(0).getDescription());
                        checkFileBeanDao.update(checkFileBean);
                    }
                }, calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();


                break;
        }
    }

    private LinePathView mPathView;
    private String checkFileName = "过程检查确认人签字";

    private void pathPopu(ImageView iv) {
        View poview = getLayoutInflater().inflate(R.layout.path_view, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(iv, Gravity.TOP, 0, 80);

        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        mPathView = poview.findViewById(R.id.path_view);
        TextView mBtnClear = poview.findViewById(R.id.m_btn_clear);
        TextView mBtnSave = poview.findViewById(R.id.m_btn_save);

        //修改背景、笔宽、颜色
        mPathView.setBackColor(Color.WHITE);
        mPathView.setPaintWidth(10);
        mPathView.setPenColor(Color.BLACK);
        //清除
        mBtnClear.setOnClickListener(v -> {
            mPathView.clear();
            mPathView.setBackColor(Color.WHITE);
            mPathView.setPaintWidth(10);
            mPathView.setPenColor(Color.BLACK);
        });
        //保存
        String path = System.currentTimeMillis() + ".png";
        mBtnSave.setOnClickListener(v -> {
            if (mPathView.getTouched()) {
                try {
                    mPathView.save(SPUtils.get(getActivity(), "path", "") + File.separator + path, true, 100);
                    Glide.with(getActivity())
                            .load(new File(SPUtils.get(getActivity(), "path", "") + File.separator + path))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(iv);
                    Toast.makeText(getActivity(), "签名成功~", Toast.LENGTH_SHORT).show();
                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(FileBeanDao.Properties.DocumentId.eq(checkFileId))
                            .list();
                    if (fileBeanList != null && !fileBeanList.isEmpty()) {
                        FileUtils.delFile(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList.get(0).getPath());
                        FileBean fileBean = new FileBean(fileBeanList.get(0).getUId(),
                                dataPackageid,
                                checkFileId,
                                checkFileName,
                                path,
                                "主内容",
                                "非密", "");
                        fileBeanDao.update(fileBean);
                    } else {
                        FileBean fileBean = new FileBean(null,
                                dataPackageid,
                                checkFileId,
                                checkFileName,
                                path,
                                "主内容",
                                "非密", "");
                        fileBeanDao.insert(fileBean);
                    }
                    popupWindow.dismiss();
                    iv_XX.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "您没有签名~", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

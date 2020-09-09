package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
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

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.LvFileAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.CheckFileBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.CheckFileBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.ui.activity.main.AddActivity;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.view.LinePathView;
import com.example.acceptance2.view.MyListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 齐套性检查——依据文件检查
 */
public class KittingFileFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.lv_file_kitting)
    MyListView lvFileKitting;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.et_conclusion)
    EditText etConclusion;
    @BindView(R.id.iv_checkPerson)
    ImageView ivCheckPerson;
    @BindView(R.id.iv_XX)
    ImageView ivXX;
    @BindView(R.id.tv_checkPerson)
    EditText tvCheckPerson;
    @BindView(R.id.et_checkTime)
    TextView etCheckTime;

    private List<DocumentBean> list = new ArrayList<>();
    private String dataPackageid;
    private String checkFileId;
    private String checkFileName;
    private LvFileAdapter lvFileAdapter;
    private String parentId;


    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        checkFileId = getArguments().getString("checkFileId");
        checkFileName= getArguments().getString("checkFileName");

        tvAdd.setOnClickListener(this);
        ivCheckPerson.setOnClickListener(this);
        ivXX.setOnClickListener(this);
        etCheckTime.setOnClickListener(this);

        etConclusion.addTextChangedListener(textWatcher);
        tvCheckPerson.addTextChangedListener(textWatcher);

        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();

        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> parentIdList = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.Project.eq("验收依据文件"))
                .list();

        parentId = parentIdList.get(0).getId();

        lvFileAdapter = new LvFileAdapter(getActivity(), list);
        lvFileKitting.setAdapter(lvFileAdapter);

        //加载数据
        setResume();

        lvFileKitting.setOnItemClickListener((adapterView, view, i, l) ->
                startActivityForResult(AddActivity.openIntent(getActivity(), dataPackageid, list.get(i).getId(), false, true), 1));

        lvFileKitting.setOnItemLongClickListener((adapterView, view, i, l) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否删除本条数据");
            builder.setPositiveButton("删除", (dialog, which) -> {

                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeans = fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(list.get(i).getId()))
                        .list();

                for (int j = 0; j < fileBeans.size(); j++) {
                    fileBeanDao.deleteByKey(fileBeans.get(j).getUId());
                }

                documentBeanDao.deleteByKey(list.get(i).getUId());

                setResume();
            });

            builder.setNegativeButton("取消", (dialog, which) -> {
            });
            builder.show();

            return true;
        });
    }


    private void setResume() {
        list.clear();
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();

        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();

        List<DeliveryListBean> deliveryListBeanList = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.ParentId.eq(parentId))
                .where(DeliveryListBeanDao.Properties.TypeDisplay.eq("技术"))
                .list();

        Comparator<DeliveryListBean> comparator = (o1, o2) -> {
            if (o1.getSort() != o2.getSort()) {
                return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
            } else {
                return 0;
            }
        };

        Collections.sort(deliveryListBeanList, comparator);

        for (int i = 0; i < deliveryListBeanList.size(); i++) {
            RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
            List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = relatedDocumentIdSetBeanDao.queryBuilder()
                    .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(deliveryListBeanList.get(i).getId()))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(deliveryListBeanList.get(i).getId()))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(deliveryListBeanList.get(i).getId()))
                    .list();
            for (int j = 0; j < relatedDocumentIdSetBeans.size(); j++) {
                List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                        .where(DocumentBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(DocumentBeanDao.Properties.Id.eq(relatedDocumentIdSetBeans.get(j).getRelatedDocumentId()))
                        .list();
                list.addAll(documentBeans);
            }
        }

        if (lvFileAdapter != null) {
            lvFileAdapter.notifyDataSetChanged();
        }

        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
        CheckFileBean checkFileBeans = checkFileBeanDao.queryBuilder()
                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                .unique();

        tvCheckPerson.setText(checkFileBeans.getCheckPerson());
        etConclusion.setText(checkFileBeans.getConclusion());
        etCheckTime.setText(checkFileBeans.getCheckTime());


        FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
        FileBean fileBeanList = fileBeanDao.queryBuilder()
                .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(FileBeanDao.Properties.DocumentId.eq(checkFileId))
                .unique();
        if (fileBeanList!=null){
            Glide.with(getActivity())
                    .load(SPUtils.get(getActivity(), "path", "") + File.separator +fileBeanList.getPath())
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(ivCheckPerson);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kitting_file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResume();

    }

    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                startActivityForResult(AddActivity.openIntent(getActivity(), dataPackageid, "", false, false), 1);
                break;
            case R.id.iv_checkPerson:
                pathPopu(ivCheckPerson);
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
                        .into(ivCheckPerson);
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
                ivXX.setVisibility(View.GONE);

                break;
            case R.id.et_checkTime:
                new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etCheckTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
                        CheckFileBean checkFileBeans = checkFileBeanDao.queryBuilder()
                                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                                .unique();
                        if (checkFileBeans!=null){
                            checkFileBeans.setCheckTime(etCheckTime.getText().toString().trim());
                        }
                        checkFileBeanDao.update(checkFileBeans);

                    }
                }, calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    private LinePathView mPathView;

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
                    ivXX.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "您没有签名~", Toast.LENGTH_SHORT).show();
            }
        });

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
                        tvCheckPerson.getText().toString().trim(),
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
}

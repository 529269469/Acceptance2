package com.example.acceptance2.ui.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.File2Adapter;
import com.example.acceptance2.adapter.home.FileAdapter;
import com.example.acceptance2.adapter.home.LegacyAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.CheckUnresolvedBean;
import com.example.acceptance2.greendao.bean.DataPackageDBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.UnresolvedBean;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckUnresolvedBeanDao;
import com.example.acceptance2.greendao.db.DataPackageDBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.UnresolvedBeanDao;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.OpenFileUtil;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.view.MyListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;

/**
 * 验收遗留问题落实
 */
public class LegacyFragment extends BaseFragment {

    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    private List<String> list = new ArrayList<>();
    private List<UnresolvedBean> beanList = new ArrayList<>();
    private LegacyAdapter legacyAdapter;
    private String dataPackageid;

    @Override
    public void onResume() {
        super.onResume();
        UnresolvedBeanDao unresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getUnresolvedBeanDao();
        List<UnresolvedBean> unresolvedBeans = unresolvedBeanDao.queryBuilder()
                .where(UnresolvedBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        beanList.clear();
        beanList.addAll(unresolvedBeans);
        if (legacyAdapter != null) {
            legacyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");

        CheckUnresolvedBeanDao checkUnresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getCheckUnresolvedBeanDao();
        CheckUnresolvedBean checkUnresolvedBean = checkUnresolvedBeanDao.queryBuilder()
                .where(CheckUnresolvedBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .unique();
        if (checkUnresolvedBean != null) {
            tvName.setText(checkUnresolvedBean.getName());
            tvCode.setText(checkUnresolvedBean.getCode());
        }


        UnresolvedBeanDao unresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getUnresolvedBeanDao();
        List<UnresolvedBean> unresolvedBeans = unresolvedBeanDao.queryBuilder()
                .where(UnresolvedBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .list();
        beanList.clear();
        beanList.addAll(unresolvedBeans);

        legacyAdapter = new LegacyAdapter(getActivity(), beanList);
        lvList.setAdapter(legacyAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addUnresolvedSet(false, i);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_legacy;

    }

    private FileAdapter fileAdapter;
    private List<FileBean> fileBeans = new ArrayList<>();
    String path = (String) SPUtils.get(getActivity(), "path", "") + "/";

    private void addUnresolvedSet(boolean isAdd, int pos) {
        View view = getLayoutInflater().inflate(R.layout.popup_add6, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(lvList, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });
        TextView tv_productCode = view.findViewById(R.id.tv_productCode);
        TextView tv_question = view.findViewById(R.id.tv_question);
        TextView tv_confirmer = view.findViewById(R.id.tv_confirmer);
        TextView tv_confirmTime = view.findViewById(R.id.tv_confirmTime);
        TextView tv_file = view.findViewById(R.id.tv_file);
        TextView tv_popup_quxiao = view.findViewById(R.id.tv_popup_quxiao);
        MyListView lv_file = view.findViewById(R.id.lv_file);
        TextView tv_popup_save = view.findViewById(R.id.tv_popup_save);

        EditText tv_description = view.findViewById(R.id.tv_description);

        fileBeans.clear();
        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        if (!isAdd) {
            tv_productCode.setText(beanList.get(pos).getProductCode());
            tv_question.setText(beanList.get(pos).getQuestion());
            tv_confirmer.setText(beanList.get(pos).getConfirmer());
            tv_confirmTime.setText(beanList.get(pos).getConfirmTime());
            tv_description.setText(beanList.get(pos).getDescription());
            try {
                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(beanList.get(pos).getId()))
                        .list();
                fileBeans.addAll(fileBeanList);
            } catch (Exception o) {

            }

        }

        tv_confirmTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tv_confirmTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, calendar.get(Calendar.YEAR)
                    , calendar.get(Calendar.MONTH)
                    , calendar.get(Calendar.DAY_OF_MONTH)).show();

        });

        tv_productCode.setOnClickListener(v -> {
            ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
            List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                    .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .list();
            String[] productCode = new String[applyItemBeans.size()];
            for (int i = 0; i < productCode.length; i++) {
                productCode[i] = applyItemBeans.get(i).getProductCode();
            }
            setDialog(tv_productCode, productCode, "请选择产品编号：");
        });

        fileAdapter = new FileAdapter(getActivity(), fileBeans);
        lv_file.setAdapter(fileAdapter);


        lv_file.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                fileBeans.remove(position);
                fileAdapter.notifyDataSetChanged();
                return true;
            }
        });

        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    startActivity(OpenFileUtil.openFile(path + fileBeans.get(i).getPath()));
                } catch (Exception o) {
                    startActivity(OpenFileUtil.openFile(fileBeans.get(i).getPath()));
                }


            }
        });
        tv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });


        tv_popup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnresolvedBeanDao unresolvedBeanDao = MyApplication.getInstances().getCheckUnresolvedDaoSession().getUnresolvedBeanDao();
                String unresolvedId = System.currentTimeMillis() + "";
                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(beanList.get(pos).getId()))
                        .list();
                for (int i = 0; i < fileBeanList.size(); i++) {
                    FileUtils.delFile(fileBeanList.get(i).getPath());
                    fileBeanDao.deleteByKey(fileBeanList.get(i).getUId());
                }
                if (isAdd) {
                    for (int i = 0; i < fileBeans.size(); i++) {
                        String end = fileBeans.get(i).getName().substring(fileBeans.get(i).getName().lastIndexOf(".") + 1,
                                fileBeans.get(i).getName().length()).toLowerCase(Locale.getDefault());
                        String filePath = System.currentTimeMillis() + "." + end;
                        FileBean fileBean = new FileBean(null,
                                dataPackageid,
                                unresolvedId,
                                fileBeans.get(i).getName(),
                                filePath,
                                fileBeans.get(i).getType(),
                                fileBeans.get(i).getSecret(),
                                fileBeans.get(i).getDisabledSecret());
                        fileBeanDao.insert(fileBean);
                        FileUtils.copyFile(fileBeans.get(i).getPath(), path + filePath);
                    }
                    UnresolvedBean unresolvedBean = new UnresolvedBean(null,
                            dataPackageid,
                            unresolvedId,
                            tv_productCode.getText().toString().trim(),
                            tv_question.getText().toString().trim(),
                            tv_confirmer.getText().toString().trim(),
                            tv_confirmTime.getText().toString().trim(),
                            "null",
                            UUID.randomUUID().toString(),
                            tv_description.getText().toString().trim());
                    unresolvedBeanDao.insert(unresolvedBean);
                } else {
                    for (int i = 0; i < fileBeans.size(); i++) {
                        String end = fileBeans.get(i).getName().substring(fileBeans.get(i).getName().lastIndexOf(".") + 1,
                                fileBeans.get(i).getName().length()).toLowerCase(Locale.getDefault());
                        String filePath = System.currentTimeMillis() + "." + end;
                        FileBean fileBean = new FileBean(null,
                                dataPackageid,
                                beanList.get(pos).getId(),
                                fileBeans.get(i).getName(),
                                filePath,
                                fileBeans.get(i).getType(),
                                fileBeans.get(i).getSecret(),
                                fileBeans.get(i).getDisabledSecret());
                        fileBeanDao.insert(fileBean);
                        FileUtils.copyFile(fileBeans.get(i).getPath(), path + filePath);
                    }
                    UnresolvedBean unresolvedBean = new UnresolvedBean(beanList.get(pos).getUId(),
                            dataPackageid,
                            beanList.get(pos).getId(),
                            tv_productCode.getText().toString().trim(),
                            tv_question.getText().toString().trim(),
                            tv_confirmer.getText().toString().trim(),
                            tv_confirmTime.getText().toString().trim(),
                            beanList.get(pos).getFileId(),
                            beanList.get(pos).getUniqueValue(),
                            tv_description.getText().toString().trim());
                    unresolvedBeanDao.update(unresolvedBean);
                }
                UnresolvedBeanDao unresolvedBeanDao2 = MyApplication.getInstances().getCheckUnresolvedDaoSession().getUnresolvedBeanDao();
                List<UnresolvedBean> unresolvedBeans = unresolvedBeanDao2.queryBuilder()
                        .where(UnresolvedBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();
                beanList.clear();
                beanList.addAll(unresolvedBeans);
                legacyAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });


    }


    private void setDialog(TextView textView, String[] dish, String title) {
        androidx.appcompat.app.AlertDialog.Builder builder2 = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder2.setTitle(title);
        int dishPos = 0;
        builder2.setSingleChoiceItems(dish, dishPos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(dish[which]);
                dialog.dismiss();
            }
        });
        builder2.setNegativeButton("取消", null);
        builder2.setCancelable(false);
        builder2.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = FileUtils.getPath(getActivity(), uri);
                    if (path != null) {
                        File file = new File(path);
                        if (file.exists()) {
                            String upLoadFilePath = file.toString();
                            String upLoadFileName = file.getName();
                            Log.e("TAG", "upLoadFilePath: " + upLoadFilePath);
                            Log.e("TAG", "upLoadFileName: " + upLoadFileName);
                            fileBeans.add(new FileBean(null, "", "", upLoadFileName, upLoadFilePath, "", "", ""));
                            fileAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }
}

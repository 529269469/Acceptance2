package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.acceptance2.R;
import com.example.acceptance2.adapter.ProductSetAdapter;
import com.example.acceptance2.adapter.home.AcceptDeviceAdapter;
import com.example.acceptance2.adapter.home.AcceptDeviceAdapter2;
import com.example.acceptance2.adapter.home.AddZu2Adapter;
import com.example.acceptance2.adapter.home.AddZuAdapter;
import com.example.acceptance2.adapter.home.ProductAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.AcceptDeviceBean;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.bean.CheckGroupBean;
import com.example.acceptance2.greendao.bean.CheckItemBean;
import com.example.acceptance2.greendao.bean.CheckItemRelateBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.PropertyBean;
import com.example.acceptance2.greendao.bean.PropertyBeanX;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.bean.TestTabBean;
import com.example.acceptance2.greendao.db.AcceptDeviceBeanDao;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.greendao.db.CheckGroupBeanDao;
import com.example.acceptance2.greendao.db.CheckItemBeanDao;
import com.example.acceptance2.greendao.db.CheckItemRelateBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanXDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.greendao.db.TestTabBeanDao;
import com.example.acceptance2.ui.MainActivity;
import com.example.acceptance2.ui.activity.main.Add2Activity;
import com.example.acceptance2.ui.activity.main.DeliveryActivity;
import com.example.acceptance2.utils.DataUtils;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.LinePathView;
import com.example.acceptance2.view.MyListView;
import com.leon.lfilepickerlibrary.LFilePicker;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import me.jessyan.autosize.utils.ScreenUtils;

/**
 * 齐套性检查——产品齐套性检查
 */
public class KittingProduct2Fragment extends BaseFragment implements ProductAdapter.Relevance, View.OnClickListener {


    @BindView(R.id.tv_del)
    TextView tvDel;
    @BindView(R.id.tv_xiugai)
    TextView tvXiugai;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.lv_product)
    MyListView lvProduct;
    @BindView(R.id.tv_add2)
    TextView tvAdd2;
    @BindView(R.id.ll_acceptDeviceSet)
    LinearLayout llAcceptDeviceSet;
    @BindView(R.id.tv_add3)
    TextView tvAdd3;
    @BindView(R.id.ll_acceptDeviceSet2)
    LinearLayout llAcceptDeviceSet2;
    @BindView(R.id.lv_acceptDeviceSet)
    MyListView lvAcceptDeviceSet;
    @BindView(R.id.lv_product_set)
    MyListView lvProductSet;
    @BindView(R.id.et_checkGroupConclusion)
    EditText etCheckGroupConclusion;
    @BindView(R.id.iv_checkPerson)
    ImageView ivCheckPerson;
    @BindView(R.id.iv_XX)
    ImageView ivXX;
    @BindView(R.id.et_checkPerson)
    EditText etCheckPerson;
    @BindView(R.id.et_checkTime)
    TextView etCheckTime;
    @BindView(R.id.et_conclusionF)
    EditText etConclusionF;
    @BindView(R.id.iv_checkPersonF)
    ImageView ivCheckPersonF;
    @BindView(R.id.iv_XXF)
    ImageView ivXXF;
    @BindView(R.id.et_checkPersonF)
    EditText etCheckPersonF;
    @BindView(R.id.et_checkTimeF)
    TextView etCheckTimeF;
    @BindView(R.id.ll_conclusion)
    LinearLayout llConclusion;
    @BindView(R.id.lv_testTable)
    MyListView lvTestTable;


    private String dataPackageid;
    private String checkFileId;
    private String checkGroupId;

    private String imgVideoId;
    private String imgVideoParentId;
    private String sortStr = "";


    private List<CheckItemBean> list = new ArrayList<>();
    private List<PropertyBean> propertyBeanArrayList = new ArrayList<>();
    private List<AcceptDeviceBean> acceptDeviceBeans = new ArrayList<>();
    private List<TestTabBean> tabBeans = new ArrayList<>();
    private AcceptDeviceAdapter acceptDeviceAdapter;
    private ProductAdapter productAdapter;
    private AcceptDeviceAdapter2 acceptDeviceAdapter2;
    private ProductSetAdapter productSetAdapter;

    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        checkFileId = getArguments().getString("checkFileId");
        checkGroupId = getArguments().getString("checkGroupId");

        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> parentIdList = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                .where(DeliveryListBeanDao.Properties.Project.eq("照片AND视频"))
                .list();

        List<DeliveryListBean> parentIdListSize = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                .list();

        Comparator<DeliveryListBean> comparator = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };

        Collections.sort(parentIdListSize, comparator);

        if (parentIdListSize != null && !parentIdListSize.isEmpty()) {
            sortStr = Integer.parseInt(parentIdListSize.get(parentIdListSize.size() - 1).getSort()) + 1 + "";
        } else {
            sortStr = "1";
        }


        if (parentIdList != null && !parentIdList.isEmpty()) {
            List<DeliveryListBean> parentIdList2 = deliveryListBeanDao.queryBuilder()
                    .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(DeliveryListBeanDao.Properties.IsParent.eq("false"))
                    .where(DeliveryListBeanDao.Properties.Project.eq("照片AND视频"))
                    .where(DeliveryListBeanDao.Properties.ParentId.eq(parentIdList.get(0).getId()))
                    .list();
            if (parentIdList2 != null && !parentIdList2.isEmpty()) {
                imgVideoParentId = parentIdList2.get(0).getId();
            } else {
                imgVideoParentId = System.currentTimeMillis() + "";
                DeliveryListBean deliveryListBean2 = new DeliveryListBean(null,
                        dataPackageid,
                        imgVideoParentId,
                        false + "",
                        "照片AND视频",
                        parentIdList.get(0).getId(),
                        UUID.randomUUID().toString(),
                        "其他",
                        sortStr,
                        sortStr);
                deliveryListBeanDao.insert(deliveryListBean2);
            }
        } else {
            imgVideoId = System.currentTimeMillis() + "";
            imgVideoParentId = System.currentTimeMillis() + "21";


            DeliveryListBean deliveryListBean = new DeliveryListBean(null,
                    dataPackageid,
                    imgVideoId,
                    true + "",
                    "照片AND视频", "", UUID.randomUUID().toString(), "", sortStr, sortStr);
            deliveryListBeanDao.insert(deliveryListBean);

            DeliveryListBean deliveryListBean2 = new DeliveryListBean(null,
                    dataPackageid,
                    imgVideoParentId,
                    false + "",
                    "照片AND视频", imgVideoId, UUID.randomUUID().toString(), "其他", sortStr, sortStr);
            deliveryListBeanDao.insert(deliveryListBean2);
        }


        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
        List<CheckGroupBean> checkGroupBeans = checkGroupBeanDao.queryBuilder()
                .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(CheckGroupBeanDao.Properties.Id.eq(checkGroupId))
                .list();
        CheckGroupBean checkGroupBean = checkGroupBeans.get(0);

        if (!StringUtils.isBlank(checkGroupBeans.get(0).getTestTable()) && checkGroupBeans.get(0).getTestTable().equals("true")) {
            tvAdd2.setVisibility(View.VISIBLE);
            llAcceptDeviceSet.setVisibility(View.VISIBLE);
            lvTestTable.setVisibility(View.VISIBLE);

            TestTabBeanDao testTabBeanDao = MyApplication.getInstances().getTestTabDaoSession().getTestTabBeanDao();
            List<TestTabBean> testTabBeans = testTabBeanDao.queryBuilder()
                    .where(TestTabBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(TestTabBeanDao.Properties.CheckFileId.eq(checkFileId))
                    .where(TestTabBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                    .list();
            tabBeans.clear();
            tabBeans.addAll(testTabBeans);

        } else {
            tvAdd2.setVisibility(View.GONE);
            llAcceptDeviceSet.setVisibility(View.GONE);
            lvTestTable.setVisibility(View.GONE);
        }
        if (!StringUtils.isBlank(checkGroupBeans.get(0).getIsTable()) && checkGroupBeans.get(0).getIsTable().equals("true")) {
            tvAdd3.setVisibility(View.VISIBLE);
            llAcceptDeviceSet2.setVisibility(View.VISIBLE);
            lvAcceptDeviceSet.setVisibility(View.VISIBLE);
            AcceptDeviceBeanDao acceptDeviceBeanDao = MyApplication.getInstances().getAcceptDeviceaDaoSession().getAcceptDeviceBeanDao();
            List<AcceptDeviceBean> acceptDeviceBeanList = acceptDeviceBeanDao.queryBuilder()
                    .where(AcceptDeviceBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(AcceptDeviceBeanDao.Properties.CheckFileId.eq(checkFileId))
                    .where(AcceptDeviceBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                    .list();
            acceptDeviceBeans.clear();
            acceptDeviceBeans.addAll(acceptDeviceBeanList);
        } else {
            tvAdd3.setVisibility(View.GONE);
            llAcceptDeviceSet2.setVisibility(View.GONE);
            lvAcceptDeviceSet.setVisibility(View.GONE);
        }


        if (!StringUtils.isBlank(checkGroupBeans.get(0).getIsConclusion()) && checkGroupBeans.get(0).getIsConclusion().equals("true")) {
            llConclusion.setVisibility(View.VISIBLE);

            etCheckGroupConclusion.setText(checkGroupBean.getCheckGroupConclusion());
            etCheckPerson.setText(checkGroupBean.getCheckPerson());
            etCheckTime.setText(checkGroupBean.getCheckTime());

            etConclusionF.setText(checkGroupBean.getConclusionF());
            etCheckPersonF.setText(checkGroupBean.getCheckPersonF());
            etCheckTimeF.setText(checkGroupBean.getCheckTimeF());

            FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
            List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                    .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(FileBeanDao.Properties.DocumentId.eq(checkGroupId))
                    .where(FileBeanDao.Properties.Type.eq("乙方"))
                    .list();
            if (!fileBeanList.isEmpty()) {
                if (StringUtils.isBlank(fileBeanList.get(0).getPath())) {
                    ivXX.setVisibility(View.GONE);
                } else {
                    ivXX.setVisibility(View.VISIBLE);
                }
                Glide.with(getActivity())
                        .load(new File(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList.get(0).getPath()))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivCheckPerson);
            } else {
                ivXX.setVisibility(View.GONE);
            }

            List<FileBean> fileBeanListF = fileBeanDao.queryBuilder()
                    .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(FileBeanDao.Properties.DocumentId.eq(checkGroupId))
                    .where(FileBeanDao.Properties.Type.eq("甲方"))
                    .list();
            if (!fileBeanListF.isEmpty()) {
                if (StringUtils.isBlank(fileBeanListF.get(0).getPath())) {
                    ivXXF.setVisibility(View.GONE);
                } else {
                    ivXXF.setVisibility(View.VISIBLE);
                }
                Glide.with(getActivity())
                        .load(new File(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanListF.get(0).getPath()))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivCheckPersonF);
            } else {
                ivXXF.setVisibility(View.GONE);
            }

        } else {
            llConclusion.setVisibility(View.GONE);
        }

        acceptDeviceAdapter2 = new AcceptDeviceAdapter2(getActivity(), tabBeans);
        lvTestTable.setAdapter(acceptDeviceAdapter2);

        lvTestTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addAcceptDevicea2(false, position);
            }
        });

        lvTestTable.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("是否删除此测试表");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TestTabBeanDao testTabBeanDao = MyApplication.getInstances().getTestTabDaoSession().getTestTabBeanDao();
                        testTabBeanDao.deleteByKey(tabBeans.get(position).getUId());
                        tabBeans.remove(position);
                        acceptDeviceAdapter2.notifyDataSetChanged();
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







        acceptDeviceAdapter = new AcceptDeviceAdapter(getActivity(), acceptDeviceBeans);
        lvAcceptDeviceSet.setAdapter(acceptDeviceAdapter);

        CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
        List<CheckItemBean> checkItemBeans = checkItemBeanDao.queryBuilder()
                .where(CheckItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                .list();

        Comparator<CheckItemBean> comparator2 = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };

        Collections.sort(checkItemBeans, comparator2);


        CheckItemRelateBeanDao checkItemRelateBeanDao = MyApplication.getInstances().getCheckItemRelateDaoSession().getCheckItemRelateBeanDao();


        for (int i = 0; i < checkItemBeans.size(); i++) {
            List<CheckItemRelateBean> checkItemRelateBeans = checkItemRelateBeanDao.queryBuilder()
                    .where(CheckItemRelateBeanDao.Properties.DataPackageId.eq(checkItemBeans.get(i).getDataPackageId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckFileId.eq(checkItemBeans.get(i).getCheckFileId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckGroupId.eq(checkItemBeans.get(i).getCheckGroupId()))
                    .where(CheckItemRelateBeanDao.Properties.TargetId.eq(checkItemBeans.get(i).getId()))
                    .list();

            for (int k = 0; k < checkItemRelateBeans.size(); k++) {
                List<CheckItemBean> checkItemBeans2 = checkItemBeanDao.queryBuilder()
                        .where(CheckItemBeanDao.Properties.DataPackageId.eq(checkItemBeans.get(i).getDataPackageId()))
                        .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkItemBeans.get(i).getCheckFileId()))
                        .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkItemBeans.get(i).getCheckGroupId()))
                        .where(CheckItemBeanDao.Properties.Id.eq(checkItemRelateBeans.get(k).getRelateItemId()))
                        .list();
                if (checkItemBeans2 != null && !checkItemBeans2.isEmpty()) {
                    String[] selected = checkItemRelateBeans.get(k).getSelected().split(",");
                    boolean isCheckItemRelate = false;
                    for (int j = 0; j < selected.length; j++) {
                        if (!StringUtils.isBlank(checkItemBeans.get(i).getSelected()) && checkItemBeans.get(i).getSelected().equals(selected[j])) {
                            isCheckItemRelate = true;
                        }
                    }

                    CheckItemBean checkItemBean = new CheckItemBean(checkItemBeans2.get(0).getUId(),
                            checkItemBeans2.get(0).getDataPackageId(),
                            checkItemBeans2.get(0).getCheckFileId(),
                            checkItemBeans2.get(0).getCheckGroupId(),
                            checkItemBeans2.get(0).getId(),
                            checkItemBeans2.get(0).getName(),
                            checkItemBeans2.get(0).getOptions(),
                            checkItemBeans2.get(0).getSelected(),
                            checkItemBeans2.get(0).getUniqueValue(),
                            checkItemBeans2.get(0).getSort(),
                            checkItemBeans2.get(0).getDescription(),
                            checkItemBeans2.get(0).getRelate(),
                            isCheckItemRelate);
                    checkItemBeanDao.update(checkItemBean);
                }
            }

        }


        List<CheckItemBean> checkItemBeans2 = checkItemBeanDao.queryBuilder()
                .where(CheckItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                .list();

        Comparator<CheckItemBean> comparator3 = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };

        Collections.sort(checkItemBeans2, comparator3);


        for (int i = 0; i < checkItemBeans2.size(); i++) {
            CheckItemBean checkItemBean = new CheckItemBean(checkItemBeans2.get(i).getUId(),
                    checkItemBeans2.get(i).getDataPackageId(),
                    checkItemBeans2.get(i).getCheckFileId(),
                    checkItemBeans2.get(i).getCheckGroupId(),
                    checkItemBeans2.get(i).getId(),
                    checkItemBeans2.get(i).getName(),
                    checkItemBeans2.get(i).getOptions(),
                    checkItemBeans2.get(i).getSelected(),
                    checkItemBeans2.get(i).getUniqueValue(),
                    i + 1 + "",
                    checkItemBeans2.get(i).getDescription(),
                    checkItemBeans2.get(i).getRelate(),
                    checkItemBeans2.get(i).getIsCheckItemRelate());
            checkItemBeanDao.update(checkItemBean);

            if (!checkItemBeans2.get(i).getIsCheckItemRelate()) {
                list.add(checkItemBeans2.get(i));
            }
        }

        lvAcceptDeviceSet.setOnItemClickListener((adapterView, view, i, l) ->
                addAcceptDevicea(false, i));

        lvAcceptDeviceSet.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否删除此专用仪表");
            builder.setPositiveButton("删除", (dialog, which) -> {
                AcceptDeviceBeanDao acceptDeviceBeanDao = MyApplication.getInstances().getAcceptDeviceaDaoSession().getAcceptDeviceBeanDao();
                acceptDeviceBeanDao.deleteByKey(acceptDeviceBeans.get(i).getUId());
                acceptDeviceBeans.remove(i);
                acceptDeviceAdapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("取消", (dialog, which) -> {
            });
            builder.show();
            return true;
        });


        productAdapter = new ProductAdapter(getActivity(), list);
        productAdapter.setRelevance(this);

        lvProduct.setAdapter(productAdapter);

        PropertyBeanDao propertyBeanDao = MyApplication.getInstances().getPropertyDaoSession().getPropertyBeanDao();
        List<PropertyBean> propertyBeans = propertyBeanDao.queryBuilder()
                .where(PropertyBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(PropertyBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(PropertyBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                .list();
        propertyBeanArrayList.clear();
        propertyBeanArrayList.addAll(propertyBeans);
        productSetAdapter = new ProductSetAdapter(getActivity(), propertyBeanArrayList);
        lvProductSet.setAdapter(productSetAdapter);


        etCheckGroupConclusion.addTextChangedListener(textWatcher);
        etCheckPerson.addTextChangedListener(textWatcher);
        etConclusionF.addTextChangedListener(textWatcher);
        etCheckPersonF.addTextChangedListener(textWatcher);

        etCheckTime.setOnClickListener(this);
        etCheckTimeF.setOnClickListener(this);

        ivCheckPersonF.setOnClickListener(this);
        ivCheckPerson.setOnClickListener(this);
        ivXX.setOnClickListener(this);
        ivXXF.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvAdd2.setOnClickListener(this);
        tvAdd3.setOnClickListener(this);



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
                CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                List<CheckGroupBean> checkGroupBeans = checkGroupBeanDao.queryBuilder()
                        .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                        .where(CheckGroupBeanDao.Properties.Id.eq(checkGroupId))
                        .list();
                CheckGroupBean checkGroupBean = new CheckGroupBean(
                        checkGroupBeans.get(0).getUId(),
                        checkGroupBeans.get(0).getDataPackageId(),
                        checkGroupBeans.get(0).getCheckFileId(),
                        checkGroupBeans.get(0).getId(),
                        checkGroupBeans.get(0).getGroupName(),
                        etCheckGroupConclusion.getText().toString().trim(),
                        etCheckPerson.getText().toString().trim(),
                        checkGroupBeans.get(0).getIsConclusion(),
                        checkGroupBeans.get(0).getIsTable(),
                        checkGroupBeans.get(0).getUniqueValue(),
                        etCheckTime.getText().toString().trim(),
                        etConclusionF.getText().toString().trim(),
                        etCheckPersonF.getText().toString().trim(),
                        checkGroupBeans.get(0).getSort(),
                        etCheckTimeF.getText().toString().trim(),
                        checkGroupBeans.get(0).getTestTable());
                checkGroupBeanDao.update(checkGroupBean);
            }

        }
    };


    //添加测试表
    private void addAcceptDevicea2(boolean isAdd, int positions) {
        View poview = getLayoutInflater().inflate(R.layout.add_accept_devicea2, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(tvAdd2, Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        EditText tv_name = poview.findViewById(R.id.tv_name);
        EditText tv_requiredVal = poview.findViewById(R.id.tv_requiredVal);
        EditText tv_testVal = poview.findViewById(R.id.tv_testVal);
        EditText tv_description = poview.findViewById(R.id.tv_description);
        TextView tv_save = poview.findViewById(R.id.tv_save);
        TextView tv_popup_quxiao = poview.findViewById(R.id.tv_popup_quxiao);

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        if (!isAdd) {
            tv_name.setText(tabBeans.get(positions).getName());
            tv_requiredVal.setText(tabBeans.get(positions).getRequiredVal());
            tv_testVal.setText(tabBeans.get(positions).getTestVal());
            tv_description.setText(tabBeans.get(positions).getDescription());
        }

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestTabBeanDao testTabBeanDao = MyApplication.getInstances().getTestTabDaoSession().getTestTabBeanDao();
                if (isAdd) {
                    TestTabBean testTabBean = new TestTabBean(null,
                            dataPackageid,
                            checkFileId,
                            checkGroupId,
                            System.currentTimeMillis() + "",
                            tv_name.getText().toString().trim(),
                            tv_requiredVal.getText().toString().trim(),
                            tv_testVal.getText().toString().trim(),
                            tv_description.getText().toString().trim(),
                            UUID.randomUUID()+"");
                    testTabBeanDao.insert(testTabBean);
                    tabBeans.add(testTabBean);
                    acceptDeviceAdapter2.notifyDataSetChanged();
                } else {
                    TestTabBean testTabBean = new TestTabBean(tabBeans.get(positions).getUId(),
                            dataPackageid,
                            checkFileId,
                            checkGroupId,
                            tabBeans.get(positions).getId(),
                            tv_name.getText().toString().trim(),
                            tv_requiredVal.getText().toString().trim(),
                            tv_testVal.getText().toString().trim(),
                            tv_description.getText().toString().trim(),
                            tabBeans.get(positions).getUniqueValue());
                    testTabBeanDao.update(testTabBean);

                    List<TestTabBean> testTabBeans = testTabBeanDao.queryBuilder()
                            .where(TestTabBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(TestTabBeanDao.Properties.CheckFileId.eq(checkFileId))
                            .where(TestTabBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                            .list();
                    tabBeans.clear();
                    tabBeans.addAll(testTabBeans);
                    acceptDeviceAdapter2.notifyDataSetChanged();
                }
                popupWindow.dismiss();
            }
        });


    }


    //添加仪器仪表专用设备表
    private void addAcceptDevicea(boolean isAdd, int positions) {
        View poview = getLayoutInflater().inflate(R.layout.add_accept_devicea, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
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

        EditText tv_name = poview.findViewById(R.id.tv_name);
        EditText tv_specification = poview.findViewById(R.id.tv_specification);
        EditText tv_accuracy = poview.findViewById(R.id.tv_accuracy);
        EditText tv_certificate = poview.findViewById(R.id.tv_certificate);
        EditText tv_description = poview.findViewById(R.id.tv_description);
        TextView tv_save = poview.findViewById(R.id.tv_save);
        TextView tv_popup_quxiao = poview.findViewById(R.id.tv_popup_quxiao);

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        if (!isAdd) {
            tv_name.setText(acceptDeviceBeans.get(positions).getName());
            tv_specification.setText(acceptDeviceBeans.get(positions).getSpecification());
            tv_accuracy.setText(acceptDeviceBeans.get(positions).getAccuracy());
            tv_certificate.setText(acceptDeviceBeans.get(positions).getCertificate());
            tv_description.setText(acceptDeviceBeans.get(positions).getDescription());
        }

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptDeviceBeanDao acceptDeviceBeanDao = MyApplication.getInstances().getAcceptDeviceaDaoSession().getAcceptDeviceBeanDao();
                if (isAdd) {
                    AcceptDeviceBean acceptDeviceBean = new AcceptDeviceBean(null,
                            dataPackageid,
                            checkFileId,
                            checkGroupId,
                            System.currentTimeMillis() + "",
                            tv_name.getText().toString().trim(),
                            tv_specification.getText().toString().trim(),
                            tv_accuracy.getText().toString().trim(),
                            tv_certificate.getText().toString().trim(),
                            tv_description.getText().toString().trim());
                    acceptDeviceBeanDao.insert(acceptDeviceBean);
                    acceptDeviceBeans.add(acceptDeviceBean);
                    acceptDeviceAdapter.notifyDataSetChanged();
                } else {
                    AcceptDeviceBean acceptDeviceBean = new AcceptDeviceBean(acceptDeviceBeans.get(positions).getUId(),
                            dataPackageid,
                            checkFileId,
                            checkGroupId,
                            acceptDeviceBeans.get(positions).getId(),
                            tv_name.getText().toString().trim(),
                            tv_specification.getText().toString().trim(),
                            tv_accuracy.getText().toString().trim(),
                            tv_certificate.getText().toString().trim(),
                            tv_description.getText().toString().trim());
                    acceptDeviceBeanDao.update(acceptDeviceBean);
                    AcceptDeviceBeanDao acceptDeviceBeanDao2 = MyApplication.getInstances().getAcceptDeviceaDaoSession().getAcceptDeviceBeanDao();
                    List<AcceptDeviceBean> acceptDeviceBeanList = acceptDeviceBeanDao2.queryBuilder()
                            .where(AcceptDeviceBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(AcceptDeviceBeanDao.Properties.CheckFileId.eq(checkFileId))
                            .where(AcceptDeviceBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                            .list();
                    acceptDeviceBeans.clear();
                    acceptDeviceBeans.addAll(acceptDeviceBeanList);
                    acceptDeviceAdapter.notifyDataSetChanged();
                }
                popupWindow.dismiss();
            }
        });


    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kitting_product2;
    }


    @Override
    public void setRelevance(int pos, View view) {
        showListDialog(pos, view);
    }

    @Override
    public void setResume() {
        list.clear();
        CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
        List<CheckItemBean> checkItemBeans = checkItemBeanDao.queryBuilder()
                .where(CheckItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                .list();

        Comparator<CheckItemBean> comparator2 = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };

        Collections.sort(checkItemBeans, comparator2);

        CheckItemRelateBeanDao checkItemRelateBeanDao = MyApplication.getInstances().getCheckItemRelateDaoSession().getCheckItemRelateBeanDao();


        for (int i = 0; i < checkItemBeans.size(); i++) {
            List<CheckItemRelateBean> checkItemRelateBeans = checkItemRelateBeanDao.queryBuilder()
                    .where(CheckItemRelateBeanDao.Properties.DataPackageId.eq(checkItemBeans.get(i).getDataPackageId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckFileId.eq(checkItemBeans.get(i).getCheckFileId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckGroupId.eq(checkItemBeans.get(i).getCheckGroupId()))
                    .where(CheckItemRelateBeanDao.Properties.TargetId.eq(checkItemBeans.get(i).getId()))
                    .list();

            for (int k = 0; k < checkItemRelateBeans.size(); k++) {
                List<CheckItemBean> checkItemBeans2 = checkItemBeanDao.queryBuilder()
                        .where(CheckItemBeanDao.Properties.DataPackageId.eq(checkItemBeans.get(i).getDataPackageId()))
                        .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkItemBeans.get(i).getCheckFileId()))
                        .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkItemBeans.get(i).getCheckGroupId()))
                        .where(CheckItemBeanDao.Properties.Id.eq(checkItemRelateBeans.get(k).getRelateItemId()))
                        .list();
                if (checkItemBeans2 != null && !checkItemBeans2.isEmpty()) {
                    String[] selected = checkItemRelateBeans.get(k).getSelected().split(",");
                    boolean isCheckItemRelate = false;
                    for (int j = 0; j < selected.length; j++) {
                        if (!StringUtils.isBlank(checkItemBeans.get(i).getSelected())&&
                                checkItemBeans.get(i).getSelected().equals(selected[j])) {
                            isCheckItemRelate = true;
                        }
                    }

                    CheckItemBean checkItemBean = new CheckItemBean(checkItemBeans2.get(0).getUId(),
                            checkItemBeans2.get(0).getDataPackageId(),
                            checkItemBeans2.get(0).getCheckFileId(),
                            checkItemBeans2.get(0).getCheckGroupId(),
                            checkItemBeans2.get(0).getId(),
                            checkItemBeans2.get(0).getName(),
                            checkItemBeans2.get(0).getOptions(),
                            checkItemBeans2.get(0).getSelected(),
                            checkItemBeans2.get(0).getUniqueValue(),
                            checkItemBeans2.get(0).getSort(),
                            checkItemBeans2.get(0).getDescription(),
                            checkItemBeans2.get(0).getRelate(),
                            isCheckItemRelate);
                    checkItemBeanDao.update(checkItemBean);
                }
            }


        }


        List<CheckItemBean> checkItemBeans2 = checkItemBeanDao.queryBuilder()
                .where(CheckItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkFileId))
                .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                .list();

        Comparator<CheckItemBean> comparator3 = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };

        Collections.sort(checkItemBeans2, comparator3);


        for (int i = 0; i < checkItemBeans2.size(); i++) {
            CheckItemBean checkItemBean = new CheckItemBean(checkItemBeans2.get(i).getUId(),
                    checkItemBeans2.get(i).getDataPackageId(),
                    checkItemBeans2.get(i).getCheckFileId(),
                    checkItemBeans2.get(i).getCheckGroupId(),
                    checkItemBeans2.get(i).getId(),
                    checkItemBeans2.get(i).getName(),
                    checkItemBeans2.get(i).getOptions(),
                    checkItemBeans2.get(i).getSelected(),
                    checkItemBeans2.get(i).getUniqueValue(),
                    i + 1 + "",
                    checkItemBeans2.get(i).getDescription(),
                    checkItemBeans2.get(i).getRelate(),
                    checkItemBeans2.get(i).getIsCheckItemRelate());
            checkItemBeanDao.update(checkItemBean);
            if (!checkItemBeans2.get(i).getIsCheckItemRelate()) {
                list.add(checkItemBeans2.get(i));
            }
        }
        productAdapter.notifyDataSetChanged();
    }

    private File cameraSavePath;
    private Uri uri;
    private int pos = 0;

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     * @param anchorView  呼出window的view
     * @param contentView   window的内容布局
     * @return window显示的左上角的xOff,yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
         // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }
    private void showListDialog(int pos, View view) {
        this.pos = pos;
        View poview = LayoutInflater.from(getActivity()).inflate(R.layout.relevance, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        int windowPos[] = calculatePopWindowPos(view, poview);
        int xOff = 20;// 可以自己调整偏移
        windowPos[0] -= xOff;
        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);

        TextView tv_file = poview.findViewById(R.id.tv_file);
        TextView tv_del = poview.findViewById(R.id.tv_del);
        TextView tv_img = poview.findViewById(R.id.tv_img);
        TextView tv_video = poview.findViewById(R.id.tv_video);
        TextView tv_file2 = poview.findViewById(R.id.tv_file2);

        tv_file2.setOnClickListener(view14 -> {
            popupWindow.dismiss();
            startActivityForResult(DeliveryActivity.openIntent(getActivity(), dataPackageid), 100);
        });

        tv_file.setOnClickListener(view1 -> {
            popupWindow.dismiss();
            startActivityForResult(Add2Activity.openIntent(getActivity(), dataPackageid, "", false, list.get(pos)), 111);
        });

        tv_del.setOnClickListener(view12 -> {
            CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();

            RelatedDocumentIdSetBeanDao documentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
            List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeanList = documentIdSetBeanDao.queryBuilder()
                    .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(checkFileId))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(checkGroupId))
                    .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(list.get(pos).getId()))
                    .list();

            if (relatedDocumentIdSetBeanList != null && !relatedDocumentIdSetBeanList.isEmpty()) {
                for (int i = 0; i < relatedDocumentIdSetBeanList.size(); i++) {
                    documentIdSetBeanDao.deleteByKey(relatedDocumentIdSetBeanList.get(i).getUId());
                }
            }
            CheckItemRelateBeanDao checkItemRelateBeanDao = MyApplication.getInstances().getCheckItemRelateDaoSession().getCheckItemRelateBeanDao();

            List<CheckItemRelateBean> checkItemRelateBeans = checkItemRelateBeanDao.queryBuilder()
                    .where(CheckItemRelateBeanDao.Properties.DataPackageId.eq(list.get(pos).getDataPackageId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckFileId.eq(list.get(pos).getCheckFileId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckGroupId.eq(list.get(pos).getCheckGroupId()))
                    .where(CheckItemRelateBeanDao.Properties.TargetId.eq(list.get(pos).getId()))
                    .list();
            List<CheckItemRelateBean> checkItemRelateBeans2 = checkItemRelateBeanDao.queryBuilder()
                    .where(CheckItemRelateBeanDao.Properties.DataPackageId.eq(list.get(pos).getDataPackageId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckFileId.eq(list.get(pos).getCheckFileId()))
                    .where(CheckItemRelateBeanDao.Properties.CheckGroupId.eq(list.get(pos).getCheckGroupId()))
                    .where(CheckItemRelateBeanDao.Properties.RelateItemId.eq(list.get(pos).getId()))
                    .list();
            if (checkItemRelateBeans != null && !checkItemRelateBeans.isEmpty()) {
                checkItemRelateBeanDao.deleteByKey(checkItemRelateBeans.get(0).getUId());
            }
            if (checkItemRelateBeans2 != null && !checkItemRelateBeans2.isEmpty()) {
                checkItemRelateBeanDao.deleteByKey(checkItemRelateBeans2.get(0).getUId());
            }
            checkItemBeanDao.deleteByKey(list.get(pos).getUId());
            setResume();
            popupWindow.dismiss();
        });

        tv_img.setOnClickListener(view13 -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraSavePath = new File(SPUtils.get(getActivity(), "path", "") + "/" + System.currentTimeMillis() + ".jpg");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 2);
            popupWindow.dismiss();
        });

        tv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                cameraSavePath = new File(SPUtils.get(getActivity(), "path", "") + "/" + System.currentTimeMillis() + ".mp4");
                uri = Uri.fromFile(cameraSavePath);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 3);
                popupWindow.dismiss();
            }
        });

    }

    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_checkTime:
                new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etCheckTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                        List<CheckGroupBean> checkGroupBeans = checkGroupBeanDao.queryBuilder()
                                .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                                .where(CheckGroupBeanDao.Properties.Id.eq(checkGroupId))
                                .list();
                        CheckGroupBean checkGroupBean = new CheckGroupBean(
                                checkGroupBeans.get(0).getUId(),
                                checkGroupBeans.get(0).getDataPackageId(),
                                checkGroupBeans.get(0).getCheckFileId(),
                                checkGroupBeans.get(0).getId(),
                                checkGroupBeans.get(0).getGroupName(),
                                etCheckGroupConclusion.getText().toString().trim(),
                                etCheckPerson.getText().toString().trim(),
                                checkGroupBeans.get(0).getIsConclusion(),
                                checkGroupBeans.get(0).getIsTable(),
                                checkGroupBeans.get(0).getUniqueValue(),
                                etCheckTime.getText().toString().trim(),
                                etConclusionF.getText().toString().trim(),
                                etCheckPersonF.getText().toString().trim(),
                                checkGroupBeans.get(0).getSort(),
                                etCheckTimeF.getText().toString().trim(),
                                checkGroupBeans.get(0).getTestTable());
                        checkGroupBeanDao.update(checkGroupBean);
                    }
                }, calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.et_checkTimeF:
                new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etCheckTimeF.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                        List<CheckGroupBean> checkGroupBeans = checkGroupBeanDao.queryBuilder()
                                .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                                .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                                .where(CheckGroupBeanDao.Properties.Id.eq(checkGroupId))
                                .list();
                        CheckGroupBean checkGroupBean = new CheckGroupBean(
                                checkGroupBeans.get(0).getUId(),
                                checkGroupBeans.get(0).getDataPackageId(),
                                checkGroupBeans.get(0).getCheckFileId(),
                                checkGroupBeans.get(0).getId(),
                                checkGroupBeans.get(0).getGroupName(),
                                etCheckGroupConclusion.getText().toString().trim(),
                                etCheckPerson.getText().toString().trim(),
                                checkGroupBeans.get(0).getIsConclusion(),
                                checkGroupBeans.get(0).getIsTable(),
                                checkGroupBeans.get(0).getUniqueValue(),
                                etCheckTime.getText().toString().trim(),
                                etConclusionF.getText().toString().trim(),
                                etCheckPersonF.getText().toString().trim(),
                                checkGroupBeans.get(0).getSort(),
                                etCheckTimeF.getText().toString().trim(),
                                checkGroupBeans.get(0).getTestTable());
                        checkGroupBeanDao.update(checkGroupBean);
                    }
                }, calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.iv_checkPerson:
                pathPopu(ivCheckPerson, "乙方", ivXX);
                break;
            case R.id.iv_XX:
                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(checkGroupId))
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
                            checkGroupId,
                            "",
                            "",
                            "乙方",
                            "非密", "");
                    fileBeanDao.update(fileBean);
                }
                ivXX.setVisibility(View.GONE);
                break;
            case R.id.iv_checkPersonF:
                pathPopu(ivCheckPersonF, "甲方", ivXXF);
                break;
            case R.id.iv_XXF:
                FileBeanDao fileBeanDao2 = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                List<FileBean> fileBeanList2 = fileBeanDao2.queryBuilder()
                        .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(FileBeanDao.Properties.DocumentId.eq(checkGroupId))
                        .list();
                Glide.with(getActivity())
                        .load("")
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivCheckPersonF);
                if (fileBeanList2 != null && !fileBeanList2.isEmpty()) {
                    FileUtils.delFile(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList2.get(0).getPath());
                    FileBean fileBean = new FileBean(fileBeanList2.get(0).getUId(),
                            dataPackageid,
                            checkGroupId,
                            "",
                            "",
                            "甲方",
                            "非密", "");
                    fileBeanDao2.update(fileBean);
                }
                ivXXF.setVisibility(View.GONE);
                break;
            case R.id.tv_add:
                addPopup();

                break;
            case R.id.tv_add3:
                addAcceptDevicea(true, 0);
                break;
            case R.id.tv_add2:
                addAcceptDevicea2(true, 0);
                break;

        }
    }

    private void addPopup() {
        View poview = getLayoutInflater().inflate(R.layout.add_view3, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
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

        EditText tv_name = poview.findViewById(R.id.tv_name);
        EditText tv_sort = poview.findViewById(R.id.tv_sort);

        MyListView lv_options = poview.findViewById(R.id.lv_options);
        MyListView lv_Property = poview.findViewById(R.id.lv_Property);
        TextView tv_options = poview.findViewById(R.id.tv_options);
        TextView tv_Property = poview.findViewById(R.id.tv_Property);
        TextView tv_save = poview.findViewById(R.id.tv_save);

        TextView tv_popup_quxiao = poview.findViewById(R.id.tv_popup_quxiao);

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        List<String> optionsList = new ArrayList<>();
        optionsList.add("是");
        optionsList.add("否");
        AddZu2Adapter addZuAdapter = new AddZu2Adapter(getActivity(), optionsList);
        lv_options.setAdapter(addZuAdapter);
        addZuAdapter.setOnDel(new AddZu2Adapter.OnDel() {
            @Override
            public void onDel(int position) {
                optionsList.remove(position);
                addZuAdapter.notifyDataSetChanged();


            }
        });
        tv_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionsList.add("");
                addZuAdapter.notifyDataSetChanged();
            }
        });


        List<String> propertyList = new ArrayList<>();
        AddZuAdapter addZuAdapter2 = new AddZuAdapter(getActivity(), propertyList);
        lv_Property.setAdapter(addZuAdapter2);
        addZuAdapter2.setOnDel(new AddZuAdapter.OnDel() {
            @Override
            public void onDel(int position) {
                propertyList.remove(position);
                addZuAdapter2.notifyDataSetChanged();


            }
        });
        tv_Property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertyList.add("");
                addZuAdapter2.notifyDataSetChanged();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isBlank(tv_name.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请输入检查项名称");
                    return;
                }
                if (StringUtils.isBlank(tv_sort.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请输入排序号");
                    return;
                }
                String optionsString = "";
                StringBuffer optionsBuffer = new StringBuffer();
                for (int i = 0; i < optionsList.size(); i++) {
                    optionsBuffer.append(optionsList.get(i)).append(",");
                }
                if (!StringUtils.isBlank(optionsBuffer.toString())) {
                    optionsString = optionsBuffer.toString().substring(0, optionsBuffer.toString().length() - 1);
                }
                String CheckItemId = System.currentTimeMillis() + "";
                for (int i = 0; i < list.size(); i++) {
                    int a = Integer.parseInt(tv_sort.getText().toString().trim());
                    int b = Integer.parseInt(StringUtils.isBlank(list.get(i).getSort()) ? "0" : list.get(i).getSort());
                    if (a <= b) {
                        CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
                        CheckItemBean checkItemBean = new CheckItemBean(list.get(i).getUId(),
                                list.get(i).getDataPackageId(),
                                list.get(i).getCheckFileId(),
                                list.get(i).getCheckGroupId(),
                                list.get(i).getId(),
                                list.get(i).getName(),
                                list.get(i).getOptions(),
                                list.get(i).getSelected(),
                                list.get(i).getUniqueValue(),
                                b + 1 + "",
                                list.get(i).getDescription(),
                                list.get(i).getRelate(),
                                false);
                        checkItemBeanDao.update(checkItemBean);

                    }
                }


                CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
                CheckItemBean checkGroupBean = new CheckItemBean(null,
                        dataPackageid,
                        checkFileId,
                        checkGroupId,
                        CheckItemId,
                        tv_name.getText().toString().trim(),
                        optionsString,
                        "",
                        UUID.randomUUID().toString(),
                        tv_sort.getText().toString().trim(),
                        "",
                        "",
                        false);
                checkItemBeanDao.insert(checkGroupBean);

                PropertyBeanXDao propertyBeanXDao = MyApplication.getInstances().getPropertyXDaoSession().getPropertyBeanXDao();
                for (int j = 0; j < propertyList.size(); j++) {
                    if (!StringUtils.isBlank(addZuAdapter.getList().get(j))) {
                        String name = addZuAdapter2.getList().get(j);
                        PropertyBeanX propertyBeanX = new PropertyBeanX(null,
                                dataPackageid,
                                checkFileId,
                                checkGroupId,
                                CheckItemId,
                                name,
                                "");
                        propertyBeanXDao.insert(propertyBeanX);
                    }
                }

                setResume();
                popupWindow.dismiss();
                ToastUtils.getInstance().showTextToast(getActivity(), "保存成功");
            }
        });


    }

    /**
     * 签名
     */
    private void pathPopu(ImageView iv, String name, ImageView xx) {
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

        LinePathView mPathView = poview.findViewById(R.id.path_view);
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
                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                            .where(FileBeanDao.Properties.DocumentId.eq(checkGroupId))
                            .where(FileBeanDao.Properties.Type.eq(name))
                            .list();

                    if (fileBeanList != null && !fileBeanList.isEmpty()) {
                        FileUtils.delFile(SPUtils.get(getActivity(), "path", "") + File.separator + fileBeanList.get(0).getPath());
                        FileBean fileBean = new FileBean(fileBeanList.get(0).getUId(),
                                dataPackageid,
                                checkGroupId,
                                path,
                                path,
                                name,
                                "非密", "");
                        fileBeanDao.update(fileBean);
                    } else {
                        FileBean fileBean = new FileBean(null,
                                dataPackageid,
                                checkGroupId,
                                path,
                                path,
                                name,
                                "非密", "");
                        fileBeanDao.insert(fileBean);
                    }

                    xx.setVisibility(View.VISIBLE);

                    Toast.makeText(getActivity(), "签名成功~", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "您没有签名~", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 2 || requestCode == 3) {
                String photoPath = String.valueOf(cameraSavePath);
                File file = new File(photoPath);
                String relatedDocumentId = System.currentTimeMillis() + "";

                RelatedDocumentIdSetBeanDao documentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                RelatedDocumentIdSetBean relatedDocumentIdSetBean = new RelatedDocumentIdSetBean(null,
                        dataPackageid,
                        checkFileId,
                        checkGroupId,
                        list.get(pos).getId(),
                        relatedDocumentId);
                documentIdSetBeanDao.insert(relatedDocumentIdSetBean);

                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                        .where(DocumentBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();
                String code = "";
                if (documentBeans != null && !documentBeans.isEmpty()) {
                    String strThem = documentBeans.get(documentBeans.size() - 1).getCode();
                    String strThem2 = strThem.substring(strThem.length() - 3, strThem.length());

                    DecimalFormat decimalFormat = new DecimalFormat("000");
                    String string2 = decimalFormat.format(Integer.parseInt(strThem2) + 1);
                    code = (String) SPUtils.get(getActivity(), "code", "") + "-D" + string2;
                } else {
                    code = (String) SPUtils.get(getActivity(), "code", "") + "-D001";
                }


                ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                List<ApplyItemBean> applyItemBeans = applyItemBeanDao.queryBuilder()
                        .where(ApplyItemBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .list();

                DocumentBean documentBean = new DocumentBean(null,
                        dataPackageid,
                        relatedDocumentId,
                        code,
                        list.get(pos).getName(),
                        "非密",
                        imgVideoParentId,
                        "照片AND视频",
                        (String) SPUtils.get(getActivity(), "modelCode", ""),
                        (String) SPUtils.get(getActivity(), "productCode", ""),
                        applyItemBeans.isEmpty() ? "" : applyItemBeans.get(0).getId(),
                        "方案阶段(M)",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "false",
                        "",
                        UUID.randomUUID().toString());
                documentBeanDao.insert(documentBean);


                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                FileBean fileBean = new FileBean(null,
                        dataPackageid,
                        relatedDocumentId,
                        file.getName(),
                        file.getName(),
                        "主内容",
                        "非密", "");
                fileBeanDao.insert(fileBean);
                setResume();
                startActivityForResult(Add2Activity.openIntent(getActivity(), dataPackageid, relatedDocumentId, true, list.get(pos)), 111);
                Log.e("拍照返回图片路径:", photoPath);
            } else if (requestCode == 100) {
                String DocumentId = data.getStringExtra("DocumentId");

                RelatedDocumentIdSetBeanDao documentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                RelatedDocumentIdSetBean relatedDocumentIdSetBean = new RelatedDocumentIdSetBean(null,
                        dataPackageid,
                        checkFileId,
                        checkGroupId,
                        list.get(pos).getId(),
                        DocumentId);
                documentIdSetBeanDao.insert(relatedDocumentIdSetBean);

                setResume();

            } else {
                setResume();
            }
        }


    }

}

package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.AddZuAdapter;
import com.example.acceptance2.adapter.home.Kitting2Adapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.bean.TitleBean;
import com.example.acceptance2.greendao.bean.CheckFileBean;
import com.example.acceptance2.greendao.bean.CheckGroupBean;
import com.example.acceptance2.greendao.bean.CheckItemBean;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.PropertyBean;
import com.example.acceptance2.greendao.db.CheckFileBeanDao;
import com.example.acceptance2.greendao.db.CheckGroupBeanDao;
import com.example.acceptance2.greendao.db.CheckItemBeanDao;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanDao;
import com.example.acceptance2.utils.DataUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.MyListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * 齐套性检查——产品齐套性检查
 */
public class KittingProductFragment extends BaseFragment implements Kitting2Adapter.OntvTb {

    @BindView(R.id.tv_add)
    ImageView tvAdd;
    @BindView(R.id.re_tb)
    RecyclerView reTb;
    @BindView(R.id.fl_vp)
    FrameLayout flVp;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;


    private String dataPackageid;
    private String checkFileId;
    private String checkFileName;
    private List<CheckGroupBean> checkGroupBeans;

    private List<TitleBean> list = new ArrayList<>();
    private Kitting2Adapter adapter;
    //    private KittingProduct2Fragment kittingProduct2Fragment;
    private FragmentTransaction transaction;
    private KittingProduct2Fragment kittingProduct2Fragment;

    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        checkFileId = getArguments().getString("checkFileId");
        checkFileName = getArguments().getString("checkFileName");


        adapter = new Kitting2Adapter(list, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reTb.setLayoutManager(manager);
        reTb.setAdapter(adapter);

        addData();

        tvAdd.setOnClickListener(view -> {
            addPopup();
        });
        adapter.setOntvTb(this);
    }


    /**
     *  添加检查组
     */
    private void addPopup() {
        View poview = getLayoutInflater().inflate(R.layout.add_view, null);
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

        EditText tv_groupName = poview.findViewById(R.id.tv_groupName);
        EditText tv_sort = poview.findViewById(R.id.tv_sort);
        MyListView lv_PropertySet = poview.findViewById(R.id.lv_PropertySet);
        Switch tv_isConclusion = poview.findViewById(R.id.tv_isConclusion);
        Switch tv_isTable = poview.findViewById(R.id.tv_isTable);
        Switch tv_textTable = poview.findViewById(R.id.tv_textTable);
        TextView tv_PropertySet = poview.findViewById(R.id.tv_PropertySet);
        TextView tv_save = poview.findViewById(R.id.tv_save);
        TextView tv_popup_quxiao = poview.findViewById(R.id.tv_popup_quxiao);

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        List<String> propertyList = new ArrayList<>();
        AddZuAdapter addZuAdapter = new AddZuAdapter(getActivity(), propertyList);
        lv_PropertySet.setAdapter(addZuAdapter);
        addZuAdapter.setOnDel(new AddZuAdapter.OnDel() {
            @Override
            public void onDel(int position) {
                propertyList.remove(position);
                addZuAdapter.notifyDataSetChanged();


            }
        });
        tv_PropertySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertyList.add("");
                addZuAdapter.notifyDataSetChanged();
            }
        });


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isBlank(tv_groupName.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请输入检查组名称");
                    return;
                }

                if (StringUtils.isBlank(tv_sort.getText().toString().trim())) {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请输入排序号");
                    return;
                }

                for (int i = 0; i < checkGroupBeans.size(); i++) {
                    int a = Integer.parseInt(tv_sort.getText().toString().trim());
                    int b = Integer.parseInt(StringUtils.isBlank(checkGroupBeans.get(i).getSort()) ? "0" : checkGroupBeans.get(i).getSort());
                    if (a <= b) {
                        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                        CheckGroupBean checkGroupBean = new CheckGroupBean(checkGroupBeans.get(i).getUId(),
                                checkGroupBeans.get(i).getDataPackageId(),
                                checkGroupBeans.get(i).getCheckFileId(),
                                checkGroupBeans.get(i).getId(),
                                checkGroupBeans.get(i).getGroupName(),
                                checkGroupBeans.get(i).getCheckGroupConclusion(),
                                checkGroupBeans.get(i).getCheckPerson(),
                                checkGroupBeans.get(i).getIsConclusion(),
                                checkGroupBeans.get(i).getIsTable(),
                                checkGroupBeans.get(i).getUniqueValue(),
                                checkGroupBeans.get(i).getCheckTime(),
                                checkGroupBeans.get(i).getConclusionF(),
                                checkGroupBeans.get(i).getCheckPersonF(),
                                b + 1 + "",
                                checkGroupBeans.get(i).getCheckTimeF(),
                                checkGroupBeans.get(i).getTestTable());
                        checkGroupBeanDao.update(checkGroupBean);

                    }
                }


                CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                String CheckGroupId = System.currentTimeMillis() + "";
                CheckGroupBean checkGroupBean = new CheckGroupBean(null,
                        dataPackageid,
                        checkFileId,
                        CheckGroupId,
                        tv_groupName.getText().toString().trim(),
                        "", "",
                        tv_isConclusion.isChecked() + "",
                        tv_isTable.isChecked() + "",
                        UUID.randomUUID().toString(),
                        "",
                        "",
                        "",
                        tv_sort.getText().toString().trim(),
                        "",
                        tv_textTable.isChecked() + "");
                checkGroupBeanDao.insert(checkGroupBean);

                PropertyBeanDao propertyBeanDao = MyApplication.getInstances().getPropertyDaoSession().getPropertyBeanDao();

                for (int j = 0; j < propertyList.size(); j++) {
                    if (!StringUtils.isBlank(addZuAdapter.getList().get(j))) {
                        PropertyBean propertyBean = new PropertyBean(null, dataPackageid, checkFileId, CheckGroupId, addZuAdapter.getList().get(j), "");
                        propertyBeanDao.insert(propertyBean);
                    }
                }
                popupWindow.dismiss();

                addData();

                ToastUtils.getInstance().showTextToast(getActivity(), "保存成功");
            }
        });


    }

    private void addData() {
        CheckFileBeanDao checkFileBeanDao = MyApplication.getInstances().getCheckFileDaoSession().getCheckFileBeanDao();
        List<CheckFileBean> checkFileBeans = checkFileBeanDao.queryBuilder()
                .where(CheckFileBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckFileBeanDao.Properties.Id.eq(checkFileId))
                .list();
        tvCode.setText("编号：" + checkFileBeans.get(0).getCode());
        tvName.setText("名称：" + checkFileBeans.get(0).getName());
        list.clear();

        CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
        checkGroupBeans = checkGroupBeanDao.queryBuilder()
                .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                .list();

        Comparator<CheckGroupBean> comparator = (o1, o2) -> {

            return Integer.parseInt(StringUtils.isBlank(o1.getSort()) ? "0" : o1.getSort()) - Integer.parseInt(StringUtils.isBlank(o2.getSort()) ? "0" : o2.getSort());

        };

        Collections.sort(checkGroupBeans, comparator);

        for (int i = 0; i < checkGroupBeans.size(); i++) {
            CheckGroupBean checkGroupBean = new CheckGroupBean(checkGroupBeans.get(i).getUId(),
                    checkGroupBeans.get(i).getDataPackageId(),
                    checkGroupBeans.get(i).getCheckFileId(),
                    checkGroupBeans.get(i).getId(),
                    checkGroupBeans.get(i).getGroupName(),
                    checkGroupBeans.get(i).getCheckGroupConclusion(),
                    checkGroupBeans.get(i).getCheckPerson(),
                    checkGroupBeans.get(i).getIsConclusion(),
                    checkGroupBeans.get(i).getIsTable(),
                    checkGroupBeans.get(i).getUniqueValue(),
                    checkGroupBeans.get(i).getCheckTime(),
                    checkGroupBeans.get(i).getConclusionF(),
                    checkGroupBeans.get(i).getCheckPersonF(),
                    i + 1 + "",
                    checkGroupBeans.get(i).getCheckTimeF(),
                    checkGroupBeans.get(i).getTestTable());
            checkGroupBeanDao.update(checkGroupBean);
        }
        checkGroupBeans = checkGroupBeanDao.queryBuilder()
                .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                .list();

        Comparator<CheckGroupBean> comparator2 = (o1, o2) -> {
            return Integer.parseInt(StringUtils.isBlank(o1.getSort()) ? "0" : o1.getSort()) - Integer.parseInt(StringUtils.isBlank(o2.getSort()) ? "0" : o2.getSort());
        };

        Collections.sort(checkGroupBeans, comparator2);

        adapter.notifyDataSetChanged();
        for (int i = 0; i < checkGroupBeans.size(); i++) {
            if (i == 0) {
                list.add(new TitleBean(checkGroupBeans.get(i).getGroupName(), true));
            } else {
                list.add(new TitleBean(checkGroupBeans.get(i).getGroupName(), false));
            }
        }

        if (list.size() > 0) {
            transaction = getChildFragmentManager().beginTransaction();
            kittingProduct2Fragment = new KittingProduct2Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("dataPackageid", dataPackageid);
            bundle.putString("checkFileId", checkFileId);
            bundle.putString("checkGroupId", checkGroupBeans.get(0).getId());
            kittingProduct2Fragment.setArguments(bundle);
            transaction.replace(R.id.fl_vp, kittingProduct2Fragment);
            transaction.commit();
        }


        list.add(new TitleBean("结论", false));

        adapter.notifyDataSetChanged();
        reTb.scrollToPosition(0);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kitting_product;
    }


    @Override
    public void setTvTb(int position) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(false);
        }
        list.get(position).setCheck(true);
        adapter.notifyDataSetChanged();

        transaction = getChildFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("dataPackageid", dataPackageid);
        bundle.putString("checkFileId", checkFileId);
        if (list.get(position).getTitle().equals("结论")) {
            SignatureFragment signatureFragment = new SignatureFragment();
            signatureFragment.setArguments(bundle);
            transaction.replace(R.id.fl_vp, signatureFragment);
        } else {
            bundle.putString("checkGroupId", checkGroupBeans.get(position).getId());
            kittingProduct2Fragment = new KittingProduct2Fragment();
            kittingProduct2Fragment.setArguments(bundle);
            transaction.replace(R.id.fl_vp, kittingProduct2Fragment);
        }
        transaction.commit();
    }

    @Override
    public void setLongTvTb(int position) {
        if (list.get(position).getTitle().equals("结论")) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("是否删除此检查组");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CheckGroupBeanDao checkGroupBeanDao = MyApplication.getInstances().getCheckGroupDaoSession().getCheckGroupBeanDao();
                checkGroupBeans = checkGroupBeanDao.queryBuilder()
                        .where(CheckGroupBeanDao.Properties.DataPackageId.eq(dataPackageid))
                        .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkFileId))
                        .list();
                Comparator<CheckGroupBean> comparator = (o1, o2) -> {
                    return Integer.parseInt(StringUtils.isBlank(o1.getSort()) ? "0" : o1.getSort()) - Integer.parseInt(StringUtils.isBlank(o2.getSort()) ? "0" : o2.getSort());
                };
                Collections.sort(checkGroupBeans, comparator);

                if (checkGroupBeans.size() <= 1) {
                    ToastUtils.getInstance().showTextToast(getActivity(), "不可全部删除");
                    return;
                }
                List<CheckGroupBean> checkGroupBeanList = checkGroupBeanDao.queryBuilder()
                        .where(CheckGroupBeanDao.Properties.DataPackageId.eq(checkGroupBeans.get(position).getDataPackageId()))
                        .where(CheckGroupBeanDao.Properties.CheckFileId.eq(checkGroupBeans.get(position).getCheckFileId()))
                        .where(CheckGroupBeanDao.Properties.Id.eq(checkGroupBeans.get(position).getId()))
                        .list();

                CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
                List<CheckItemBean> checkItemBeanList = checkItemBeanDao.queryBuilder()
                        .where(CheckItemBeanDao.Properties.DataPackageId.eq(checkGroupBeanList.get(0).getDataPackageId()))
                        .where(CheckItemBeanDao.Properties.CheckFileId.eq(checkGroupBeanList.get(0).getCheckFileId()))
                        .where(CheckItemBeanDao.Properties.CheckGroupId.eq(checkGroupBeanList.get(0).getId()))
                        .list();

                for (int i = 0; i < checkItemBeanList.size(); i++) {
                    checkItemBeanDao.deleteByKey(checkItemBeanList.get(i).getUId());
                }

                checkGroupBeanDao.deleteByKey(checkGroupBeans.get(position).getUId());

                addData();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }

}

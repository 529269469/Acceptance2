package com.example.acceptance2.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.home.DeliveryAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.ui.MainActivity;
import com.example.acceptance2.ui.activity.main.Add2Activity;
import com.example.acceptance2.utils.ToastUtils;
import com.example.acceptance2.view.AddPrijectPopupWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * 交付清单
 */
public class DeliveryFragment extends BaseFragment implements DeliveryAdapter.OnItemClick {


    @BindView(R.id.tv_add_project)
    TextView tvAddProject;
    @BindView(R.id.lv_list)
    ExpandableListView lvList;
    private String dataPackageid;
    private List<DeliveryListBean> listBeans = new ArrayList<>();

    private List<List<DeliveryListBean>> list2= new ArrayList<>();
    private DeliveryAdapter adapter;


    @Override
    protected void initEventAndData() {
        dataPackageid = getArguments().getString("dataPackageid");
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();

        List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                .list();
        Comparator<DeliveryListBean> comparator = (o1, o2) -> {
            return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
        };
        Collections.sort(deliveryListBeans, comparator);


        List<List<DeliveryListBean>> list2 = new ArrayList<>();

        for (int i = 0; i < deliveryListBeans.size(); i++) {
            List<DeliveryListBean> deliveryListBeans3 = deliveryListBeanDao.queryBuilder()
                    .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                    .where(DeliveryListBeanDao.Properties.IsParent.eq("false"))
                    .where(DeliveryListBeanDao.Properties.ParentId.eq(deliveryListBeans.get(i).getId()))
                    .list();
            Comparator<DeliveryListBean> comparator2 = (o1, o2) -> {
                return Integer.parseInt(o1.getSort()) - Integer.parseInt(o2.getSort());
            };
            Collections.sort(deliveryListBeans3, comparator2);
            list2.add(deliveryListBeans3);
        }

        adapter = new DeliveryAdapter(getActivity(), deliveryListBeans,list2);
        lvList.setAdapter(adapter);

        tvAddProject.setOnClickListener(view -> addProject());


        adapter.setOnItemClick(this);
    }

    private void addProject() {
        AddPrijectPopupWindow addPrijectPopupWindow=new AddPrijectPopupWindow(getActivity(),tvAddProject);
        addPrijectPopupWindow.setAddFile(() -> {
            MainActivity mainActivity= (MainActivity) getActivity();
            mainActivity.setDeliveryFragment();
        });


    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_delivery;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(dataPackageid))
                .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                .list();
        listBeans.clear();
        adapter.notifyDataSetChanged();
        listBeans.addAll(deliveryListBeans);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void ItemClickName(String id) {
        startActivityForResult(Add2Activity.openIntent(getActivity(),dataPackageid,"",false,null),100);
    }

    @Override
    public void ItemClick(String id) {
        startActivityForResult(Add2Activity.openIntent(getActivity(),dataPackageid,id,true,null),100);
    }

    @Override
    public void LongItemClick(String id) {



    }

}

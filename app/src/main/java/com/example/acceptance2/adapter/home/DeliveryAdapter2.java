package com.example.acceptance2.adapter.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/19 10
 */
public class DeliveryAdapter2 extends BaseAdapter {

    private Activity context;
    private List<DeliveryListBean> list;

    public DeliveryAdapter2(Activity context, List<DeliveryListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.delivery_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvProject.setText(list.get(i).getProject());

        DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
        List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                .where(DeliveryListBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                .where(DeliveryListBeanDao.Properties.ParentId.eq(list.get(i).getId()))
                .list();

        Delivery3Adapter legacyAdapter = new Delivery3Adapter(context, deliveryListBeans);
        viewHolder.lvlvDoc.setAdapter(legacyAdapter);


        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_project)
        TextView tvProject;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_typeDisplaye)
        TextView tvTypeDisplaye;
        @BindView(R.id.tv_secret)
        TextView tvSecret;
        @BindView(R.id.tv_stage)
        TextView tvStage;
        @BindView(R.id.tv_productCode)
        TextView tvProductCode;
        @BindView(R.id.tv_name_file)
        TextView tvNameFile;
        @BindView(R.id.lvlv_doc)
        MyListView lvlvDoc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

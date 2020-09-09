package com.example.acceptance2.adapter.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.ui.activity.main.Add2Activity;
import com.example.acceptance2.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/19 10
 */
public class DeliveryAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DeliveryListBean> list1;
    private List<List<DeliveryListBean>> list2;


    public DeliveryAdapter(Context context, List<DeliveryListBean> list, List<List<DeliveryListBean>> list2) {
        this.context = context;
        this.list1 = list;
        this.list2 = list2;

    }


    @Override
    public int getGroupCount() {
        return list1.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list2.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list1.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list2.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvProject.setText(list1.get(groupPosition).getProject());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childViewHolder.tvName.setText(list2.get(groupPosition).get(childPosition).getProject());
        childViewHolder.tvTypeDisplaye.setText(list2.get(groupPosition).get(childPosition).getTypeDisplay());
        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();

        List<DocumentBean> documentBeanList = new ArrayList<>();
        documentBeanList.clear();


        List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                .where(DocumentBeanDao.Properties.DataPackageId.eq(list2.get(groupPosition).get(childPosition).getDataPackageId()))
                .where(DocumentBeanDao.Properties.PayClassify.eq(list2.get(groupPosition).get(childPosition).getId()))
                .list();
        documentBeanList.addAll(documentBeans);


        Delivery2Adapter adapter = new Delivery2Adapter(context, documentBeanList, list2.get(groupPosition).get(childPosition).getTypeDisplay());
        childViewHolder.lvlv_doc.setAdapter(adapter);

        childViewHolder.lvlv_doc.setOnItemClickListener((parent1, view, position, id) -> {
            onItemClick.ItemClick(documentBeanList.get(position).getId());
        });

        childViewHolder.lvlv_doc.setOnItemLongClickListener((parent12, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("是否删除本条数据");
            builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeans = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(list2.get(groupPosition).get(childPosition).getDataPackageId()))
                            .where(FileBeanDao.Properties.DocumentId.eq(documentBeanList.get(position).getId()))
                            .list();
                    for (int k = 0; k < fileBeans.size(); k++) {
                        fileBeanDao.deleteByKey(fileBeans.get(k).getUId());
                    }
                    documentBeanDao.deleteByKey(documentBeanList.get(0).getUId());

                    documentBeanList.remove(position);
                    adapter.notifyDataSetChanged();

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

        childViewHolder.tvName.setOnClickListener(v -> {
            onItemClick.ItemClickName(list2.get(groupPosition).get(childPosition).getId());

        });

        return convertView;
    }

    public interface OnItemClick {
        void ItemClickName(String id);

        void ItemClick(String id);

        void LongItemClick(String id);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @BindView(R.id.tv_project)
        TextView tvProject;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
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
        MyListView lvlv_doc;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

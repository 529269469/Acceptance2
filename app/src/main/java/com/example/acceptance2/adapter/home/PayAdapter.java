package com.example.acceptance2.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.DeliveryListBean;

import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2020/5/9 09
 */
public class PayAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<DeliveryListBean> list1;
    private List<List<DeliveryListBean>> list2;

    public PayAdapter(Context context, List<DeliveryListBean> list1, List<List<DeliveryListBean>> list2) {
        this.context = context;
        this.list1 = list1;
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
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_layout1,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.lv_lv_text1);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(list1.get(groupPosition).getProject());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_layout2,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.lv_lv_text2);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(list2.get(groupPosition).get(childPosition).getProject());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        TextView tvTitle;

    }
}

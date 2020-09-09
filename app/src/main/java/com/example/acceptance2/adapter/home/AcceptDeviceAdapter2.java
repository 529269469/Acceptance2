package com.example.acceptance2.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.TestTabBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/16 10
 */
public class AcceptDeviceAdapter2 extends BaseAdapter {
    private Context context;
    private List<TestTabBean> list;

    public AcceptDeviceAdapter2(Context context, List<TestTabBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.accept_device_item2, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvNum.setText(i + 1 + "");
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvRequiredVal.setText(list.get(i).getRequiredVal());
        viewHolder.tvTestVal.setText(list.get(i).getTestVal());
        viewHolder.tvDescription.setText(list.get(i).getDescription());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_requiredVal)
        TextView tvRequiredVal;
        @BindView(R.id.tv_testVal)
        TextView tvTestVal;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

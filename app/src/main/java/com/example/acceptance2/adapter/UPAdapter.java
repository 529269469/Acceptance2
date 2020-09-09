package com.example.acceptance2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.bean.TitleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2020/8/5 11
 */
public class UPAdapter extends BaseAdapter {
    private List<TitleBean> list;
    private Context context;

    public UPAdapter(List<TitleBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.u_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvU.setText(list.get(position).getTitle());

        if (list.get(position).isCheck()){
            viewHolder.tvU.setBackground(context.getResources().getDrawable(R.drawable.shape_text2));
            viewHolder.tvU.setTextColor(context.getResources().getColor(R.color.color_FFFFFF));
        }else {
            viewHolder.tvU.setBackgroundColor(context.getResources().getColor(R.color.color_474747));
            viewHolder.tvU.setTextColor(context.getResources().getColor(R.color.color_FFFFFF));
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_u)
        TextView tvU;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

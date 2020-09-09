package com.example.acceptance2.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.DocumentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/10 10
 */
public class File3Adapter extends BaseAdapter {
    private Context context;
    private List<DocumentBean> list;

    public File3Adapter(Context context, List<DocumentBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lv_file2_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(list.get(i).getName());

        viewHolder.iv_del.setOnClickListener(view1 -> {
            if (onDel!=null){
                onDel.onDel(i);
            }
        });

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_del)
        ImageView iv_del;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnDel{
      void onDel(int position);
    }

    private OnDel onDel;

    public void setOnDel(OnDel onDel) {
        this.onDel = onDel;
    }
}

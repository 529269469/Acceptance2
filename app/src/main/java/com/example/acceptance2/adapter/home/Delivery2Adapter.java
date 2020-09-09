package com.example.acceptance2.adapter.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/19 10
 */
public class Delivery2Adapter extends BaseAdapter {

    private Context context;
    private List<DocumentBean> list;
    private String typeDisplay;

    public Delivery2Adapter(Context context, List<DocumentBean> list, String typeDisplay) {
        this.context = context;
        this.list = list;
        this.typeDisplay = typeDisplay;
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
        viewHolder.tvSecret.setText(list.get(i).getSecret());
        viewHolder.tvStage.setText(list.get(i).getStage());
        viewHolder.tvProductCode.setText(list.get(i).getCode());
        viewHolder.tvNameFile.setText(list.get(i).getName());

        viewHolder.tvTypeDisplaye.setText(typeDisplay);

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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

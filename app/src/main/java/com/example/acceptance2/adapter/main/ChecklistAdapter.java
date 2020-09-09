package com.example.acceptance2.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.DataPackageDBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/15 13
 */
public class ChecklistAdapter extends BaseAdapter {
    private Context context;
    private List<DataPackageDBean> list;

    public ChecklistAdapter(Context context, List<DataPackageDBean> list) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.checklist_item, viewGroup, false);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvXuhao.setText(position + 1 + "");
        viewHolder.tvName.setText(list.get(position).getName());
        viewHolder.tvCode.setText(list.get(position).getCode());
        viewHolder.tvVersionInfo.setText(list.get(position).getVersionInfo());
        viewHolder.tvAcceptorUnit.setText(list.get(position).getAcceptorUnit());
        viewHolder.tvApplyCompany.setText(list.get(position).getApplyCompany());
        viewHolder.tvModelSeriesName.setText(list.get(position).getModelSeriesName());
        viewHolder.tvProductName.setText(list.get(position).getProductName());
        viewHolder.tvProductCode.setText(list.get(position).getProductCode());
        viewHolder.tvProductType.setText(list.get(position).getProductType());
        viewHolder.tvBatch.setText(list.get(position).getBatch());
        viewHolder.tvCreator.setText(list.get(position).getCreator());
        viewHolder.tvCreateTime.setText(list.get(position).getCreateTime());
        viewHolder.tv_versionInfo2.setText(list.get(position).getVersionInfo2());

        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_xuhao)
        TextView tvXuhao;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_versionInfo)
        TextView tvVersionInfo;
        @BindView(R.id.tv_acceptorUnit)
        TextView tvAcceptorUnit;
        @BindView(R.id.tv_applyCompany)
        TextView tvApplyCompany;
        @BindView(R.id.tv_modelSeriesName)
        TextView tvModelSeriesName;
        @BindView(R.id.tv_productName)
        TextView tvProductName;
        @BindView(R.id.tv_productCode)
        TextView tvProductCode;
        @BindView(R.id.tv_productType)
        TextView tvProductType;
        @BindView(R.id.tv_batch)
        TextView tvBatch;
        @BindView(R.id.tv_creator)
        TextView tvCreator;
        @BindView(R.id.tv_createTime)
        TextView tvCreateTime;
        @BindView(R.id.tv_versionInfo2)
        TextView tv_versionInfo2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

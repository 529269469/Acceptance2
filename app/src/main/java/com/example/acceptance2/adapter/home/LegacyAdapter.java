package com.example.acceptance2.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.UnresolvedBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/19 09
 */
public class LegacyAdapter extends BaseAdapter {

    private Context context;
    private List<UnresolvedBean> list;

    public LegacyAdapter(Context context, List<UnresolvedBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.legacy_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvNub.setText(i + 1 + "");
        viewHolder.tvProductCode.setText(list.get(i).getProductCode());
        viewHolder.tvQuestion.setText(list.get(i).getQuestion());
        viewHolder.tvConfirmer.setText(list.get(i).getConfirmer());
        viewHolder.tvConfirmTime.setText(list.get(i).getConfirmTime());
        viewHolder.tvDescription.setText(list.get(i).getDescription());

        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_nub)
        TextView tvNub;
        @BindView(R.id.tv_productCode)
        TextView tvProductCode;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_confirmer)
        TextView tvConfirmer;
        @BindView(R.id.tv_confirmTime)
        TextView tvConfirmTime;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_file)
        TextView tvFile;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.example.acceptance2.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


import com.example.acceptance2.R;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.ApplyItemBean;
import com.example.acceptance2.greendao.db.ApplyItemBeanDao;
import com.example.acceptance2.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/10 14
 */
public class UnresolvedAdapter2 extends BaseAdapter {
    private Context context;
    private List<ApplyItemBean> list;

    public UnresolvedAdapter2(Context context, List<ApplyItemBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.unresolve_item2, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvNum.setText(i+1+"");
        viewHolder.tvProductCode.setText(list.get(i).getProductCode());
        viewHolder.tvQuestion.setText(list.get(i).getProductName());

        if (!StringUtils.isBlank(list.get(i).getPassCheck())&&list.get(i).getPassCheck().equals("通过")){
            viewHolder.tvConfirmer.setChecked(true);
            viewHolder.tv_yes.setTextColor(context.getResources().getColor(R.color.color_5398F7));
            viewHolder.tv_no.setTextColor(context.getResources().getColor(R.color.color_D0D0D0));
        }else {
            viewHolder.tvConfirmer.setChecked(false);
            viewHolder.tv_yes.setTextColor(context.getResources().getColor(R.color.color_D0D0D0));
            viewHolder.tv_no.setTextColor(context.getResources().getColor(R.color.colorAccent2));
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.tvConfirmer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    finalViewHolder.tv_yes.setTextColor(context.getResources().getColor(R.color.color_5398F7));
                    finalViewHolder.tv_no.setTextColor(context.getResources().getColor(R.color.color_D0D0D0));
                }else {
                    finalViewHolder.tv_yes.setTextColor(context.getResources().getColor(R.color.color_D0D0D0));
                    finalViewHolder.tv_no.setTextColor(context.getResources().getColor(R.color.colorAccent2));
                }
                ApplyItemBeanDao applyItemBeanDao = MyApplication.getInstances().getApplyItemDaoSession().getApplyItemBeanDao();
                ApplyItemBean applyItemBean=new ApplyItemBean(list.get(i).getUId(),
                        list.get(i).getDataPackageId(),
                        list.get(i).getId(),
                        list.get(i).getProductCodeName(),
                        list.get(i).getProductCode(),
                        list.get(i).getProductStatus(),
                        list.get(i).getCheckCount(),
                        list.get(i).getIsPureCheck(),
                        list.get(i).getIsArmyCheck(),
                        list.get(i).getIsCompleteChoice(),
                        list.get(i).getIsCompleteRoutine(),
                        list.get(i).getIsSatisfyRequire(),
                        list.get(i).getDescription(),
                        list.get(i).getProductName(),
                        b?"通过":"不通过",
                        list.get(i).getUniqueValue());
                applyItemBeanDao.update(applyItemBean);

            }
        });


        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_productCode)
        TextView tvProductCode;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_no)
        TextView tv_no;
        @BindView(R.id.tv_yes)
        TextView tv_yes;
        @BindView(R.id.tv_confirmer)
        Switch tvConfirmer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

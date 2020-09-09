package com.example.acceptance2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.acceptance2.R;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.utils.OpenFileUtil;
import com.example.acceptance2.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/10 10
 */
public class FileApplyForAdapter extends BaseAdapter {
    private Context context;
    private List<FileBean> list;

    public FileApplyForAdapter(Context context, List<FileBean> list) {
        this.context = context;
        this.list = list;
    }

    public List<FileBean> getList() {
        return list;
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
    private String path = (String) SPUtils.get(context, "path", "") + "/";
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_file_add_item, viewGroup, false);
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
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {
                        context.startActivity(OpenFileUtil.openFile(path + list.get(i).getPath()));
                    } catch (Exception o) {
                        context.startActivity(OpenFileUtil.openFile(list.get(i).getPath()));
                    }
            }
        });

        viewHolder.tv_secret.setVisibility(View.GONE);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_del)
        ImageView iv_del;
        @BindView(R.id.tv_secret)
        TextView tv_secret;
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

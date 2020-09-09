package com.example.acceptance2.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.greendao.bean.DeliveryListBean;
import com.example.acceptance2.greendao.db.DeliveryListBeanDao;
import com.example.acceptance2.utils.SPUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.utils.ToastUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/4 15
 */
public class AddPrijectPopupWindow extends PopupWindow {
    private Activity context;
    private View view;

    private String typeDisplay;
    public AddPrijectPopupWindow(Activity context, View tvAdd) {
        super(context);
        this.context = context;
        view = context.getLayoutInflater().inflate(R.layout.popup_add_project, null);
        this.setContentView(view);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        this.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setOutsideTouchable(true);
        this.setFocusable(true);
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        this.showAtLocation(tvAdd, Gravity.CENTER, 0, 0);
        this.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = context.getWindow().getAttributes();
            lp1.alpha = 1f;
            context.getWindow().setAttributes(lp1);

        });
        typeDisplay="";
        EditText et_project=view.findViewById(R.id.et_project);
        EditText tv_name=view.findViewById(R.id.et_name);
        EditText et_sort=view.findViewById(R.id.et_sort);
        TextView tv_popup_save=view.findViewById(R.id.tv_popup_save);
        TextView tv_popup_quxiao=view.findViewById(R.id.tv_popup_quxiao);

        RadioGroup rg_project=view.findViewById(R.id.rg_project);

        tv_popup_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        rg_project.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_manage:
                        typeDisplay="管理";
                        break;
                    case R.id.rb_technology:
                        typeDisplay="技术";
                        break;
                    case R.id.rb_else:
                        typeDisplay="其他";
                        break;
                }
            }
        });

        tv_popup_save.setOnClickListener(view -> {
            String projectStr=et_project.getText().toString().trim();
            if (StringUtils.isBlank(projectStr)){
                ToastUtils.getInstance().showTextToast(context,"请输入项目分类");
                return;
            }
            String nameStr=tv_name.getText().toString().trim();
            if (StringUtils.isBlank(nameStr)){
                ToastUtils.getInstance().showTextToast(context,"请输入项目名称");
                return;
            }
            String sortStr=et_sort.getText().toString().trim();
            if (StringUtils.isBlank(sortStr)){
                ToastUtils.getInstance().showTextToast(context,"请输入排序号");
                return;
            }

            if (StringUtils.isBlank(typeDisplay)){
                ToastUtils.getInstance().showTextToast(context,"请选择类型");
                return;
            }

            DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();

            List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao.queryBuilder()
                    .where(DeliveryListBeanDao.Properties.DataPackageId.eq((String)SPUtils.get(context,"id","")))
                    .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                    .where(DeliveryListBeanDao.Properties.Project.eq(projectStr))
                    .list();
            if (deliveryListBeans!=null&&!deliveryListBeans.isEmpty()){
                List<DeliveryListBean> deliveryListBeans2 = deliveryListBeanDao.queryBuilder()
                        .where(DeliveryListBeanDao.Properties.DataPackageId.eq((String)SPUtils.get(context,"id","")))
                        .where(DeliveryListBeanDao.Properties.IsParent.eq("false"))
                        .where(DeliveryListBeanDao.Properties.Project.eq(nameStr))
                        .list();
                if (deliveryListBeans2!=null&&!deliveryListBeans2.isEmpty()){
                  ToastUtils.getInstance().showTextToast(context,"项目名称已存在");
                  return;
                }else {
                    List<DeliveryListBean> deliveryListBeans3 = deliveryListBeanDao.queryBuilder()
                            .where(DeliveryListBeanDao.Properties.DataPackageId.eq((String)SPUtils.get(context,"id","")))
                            .where(DeliveryListBeanDao.Properties.IsParent.eq("false"))
                            .where(DeliveryListBeanDao.Properties.ParentId.eq(deliveryListBeans.get(0).getId()))
                            .list();

                    for (int i = 0; i < deliveryListBeans3.size(); i++) {
                        int a=Integer.parseInt(sortStr);
                        int b=Integer.parseInt(StringUtils.isBlank(deliveryListBeans3.get(i).getSort())?"0":deliveryListBeans3.get(i).getSort());
                        if (a<=b){
                            DeliveryListBean deliveryListBean=new DeliveryListBean(deliveryListBeans3.get(i).getUId(),
                                    deliveryListBeans3.get(i).getDataPackageId(),
                                    deliveryListBeans3.get(i).getId(),
                                    deliveryListBeans3.get(i).getIsParent(),
                                    deliveryListBeans3.get(i).getProject(),
                                    deliveryListBeans3.get(i).getId(),
                                    deliveryListBeans3.get(i).getUniqueValue(),
                                    deliveryListBeans3.get(i).getTypeDisplay(),
                                    b+1+"",
                                    b+1+"");
                            deliveryListBeanDao.update(deliveryListBean);
                        }
                    }
                    DeliveryListBean deliveryListBean=new DeliveryListBean(null,
                            (String)SPUtils.get(context,"id",""),
                            System.currentTimeMillis()+"",
                            "false",
                            nameStr,
                            deliveryListBeans.get(0).getId(),
                            UUID.randomUUID()+"",
                            typeDisplay,
                            sortStr,
                            sortStr);
                    deliveryListBeanDao.insert(deliveryListBean);
                }

            }else {
                List<DeliveryListBean> deliveryListBeans3 = deliveryListBeanDao.queryBuilder()
                        .where(DeliveryListBeanDao.Properties.DataPackageId.eq((String)SPUtils.get(context,"id","")))
                        .where(DeliveryListBeanDao.Properties.IsParent.eq("true"))
                        .list();
                for (int i = 0; i < deliveryListBeans3.size(); i++) {
                    int a=Integer.parseInt(sortStr);
                    int b=Integer.parseInt(StringUtils.isBlank(deliveryListBeans3.get(i).getSort())?"0":deliveryListBeans3.get(i).getSort());
                    if (a<=b){
                        DeliveryListBean deliveryListBean=new DeliveryListBean(deliveryListBeans3.get(i).getUId(),
                                deliveryListBeans3.get(i).getDataPackageId(),
                                deliveryListBeans3.get(i).getId(),
                                deliveryListBeans3.get(i).getIsParent(),
                                deliveryListBeans3.get(i).getProject(),
                                deliveryListBeans3.get(i).getId(),
                                deliveryListBeans3.get(i).getUniqueValue(),
                                deliveryListBeans3.get(i).getTypeDisplay(),
                                b+1+"",
                                b+1+"");
                        deliveryListBeanDao.update(deliveryListBean);
                    }
                }
                String id =System.currentTimeMillis()+"";
                DeliveryListBean deliveryListBean=new DeliveryListBean(null,
                        (String)SPUtils.get(context,"id",""),
                        id,
                        "true",
                        nameStr,
                        "",
                        UUID.randomUUID()+"",
                        "",
                        sortStr,
                        sortStr);
                deliveryListBeanDao.insert(deliveryListBean);

                DeliveryListBean deliveryListBean2=new DeliveryListBean(null,
                        (String)SPUtils.get(context,"id",""),
                        System.currentTimeMillis()+"",
                        "false",
                        nameStr,
                        id,
                        UUID.randomUUID()+"",
                        typeDisplay,
                        "1",
                        "1");
                deliveryListBeanDao.insert(deliveryListBean2);

            }
            addFile.addResult();
            dismiss();
        });

    }

    public interface AddFile{
        void addResult();
    }

    private AddFile addFile;

    public void setAddFile(AddFile addFile) {
        this.addFile = addFile;
    }




}

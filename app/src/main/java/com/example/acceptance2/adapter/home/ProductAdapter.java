package com.example.acceptance2.adapter.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.GridAdapter;
import com.example.acceptance2.base.MyApplication;
import com.example.acceptance2.bean.TitleBean;
import com.example.acceptance2.greendao.bean.CheckItemBean;
import com.example.acceptance2.greendao.bean.CheckItemRelateBean;
import com.example.acceptance2.greendao.bean.DocumentBean;
import com.example.acceptance2.greendao.bean.FileBean;
import com.example.acceptance2.greendao.bean.PropertyBeanX;
import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;
import com.example.acceptance2.greendao.db.CheckItemBeanDao;
import com.example.acceptance2.greendao.db.CheckItemRelateBeanDao;
import com.example.acceptance2.greendao.db.DocumentBeanDao;
import com.example.acceptance2.greendao.db.FileBeanDao;
import com.example.acceptance2.greendao.db.PropertyBeanXDao;
import com.example.acceptance2.greendao.db.RelatedDocumentIdSetBeanDao;
import com.example.acceptance2.ui.activity.main.Add2Activity;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.StringUtils;
import com.example.acceptance2.view.MyGridView;
import com.example.acceptance2.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/12 13
 */
public class ProductAdapter extends BaseAdapter {
    private Activity context;
    private List<CheckItemBean> list;
    private GVproAdapetr gVproAdapetr;

    public ProductAdapter(Activity context, List<CheckItemBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lv_product_kitting, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i%2==0){
            viewHolder.ll_pro.setBackgroundColor(context.getResources().getColor(R.color.color_2B2B2B));
        }else {
            viewHolder.ll_pro.setBackgroundColor(context.getResources().getColor(R.color.color_3C3F41));
        }

        viewHolder.btRelevance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (relevance != null) {
                    relevance.setRelevance(i, view);
                }
            }
        });
        viewHolder.tvName.setText(list.get(i).getName());

        String[] options = list.get(i).getOptions().split(",");
        List<TitleBean> titleBeans = new ArrayList<>();

        for (int j = 0; j < options.length; j++) {
            titleBeans.add(new TitleBean(options[j]));

            if (!StringUtils.isBlank(list.get(i).getSelected()) && list.get(i).getSelected().equals(options[j])) {
                titleBeans.get(j).setCheck(true);
            }
        }
        OptionsAdapter optionsAdapter = new OptionsAdapter(context, titleBeans);
        viewHolder.lvYesNo.setAdapter(optionsAdapter);

        viewHolder.lvYesNo.setOnItemClickListener((adapterView, view1, pos, l) -> {

            CheckItemBeanDao checkItemBeanDao = MyApplication.getInstances().getCheckItemDaoSession().getCheckItemBeanDao();
            CheckItemBean checkItemBean = new CheckItemBean(list.get(i).getUId(),
                    list.get(i).getDataPackageId(),
                    list.get(i).getCheckFileId(),
                    list.get(i).getCheckGroupId(),
                    list.get(i).getId(),
                    list.get(i).getName(),
                    list.get(i).getOptions(),
                    titleBeans.get(pos).getTitle().equals(list.get(i).getSelected())?"":titleBeans.get(pos).getTitle(),
                    list.get(i).getUniqueValue(),
                    list.get(i).getSort(),
                    list.get(i).getDescription(),
                    list.get(i).getRelate(), false);
            checkItemBeanDao.update(checkItemBean);

            relevance.setResume();

        });

        PropertyBeanXDao propertyBeanXDao = MyApplication.getInstances().getPropertyXDaoSession().getPropertyBeanXDao();
        List<PropertyBeanX> propertyBeans = propertyBeanXDao.queryBuilder()
                .where(PropertyBeanXDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                .where(PropertyBeanXDao.Properties.CheckFileId.eq(list.get(0).getCheckFileId()))
                .where(PropertyBeanXDao.Properties.CheckGroupId.eq(list.get(0).getCheckGroupId()))
                .where(PropertyBeanXDao.Properties.CheckItemId.eq(list.get(i).getId()))
                .list();

        gVproAdapetr = new GVproAdapetr(context, propertyBeans);
        viewHolder.gvPro.setAdapter(gVproAdapetr);

        RelatedDocumentIdSetBeanDao documentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
        List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = documentIdSetBeanDao.queryBuilder()
                .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(list.get(i).getCheckFileId()))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(list.get(i).getCheckGroupId()))
                .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(list.get(i).getId()))
                .list();
        List<DocumentBean> fileBeanList = new ArrayList<>();
        List<FileBean> gridList = new ArrayList<>();
        if (relatedDocumentIdSetBeans != null && !relatedDocumentIdSetBeans.isEmpty()) {
            for (int j = 0; j < relatedDocumentIdSetBeans.size(); j++) {
                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
                        .where(DocumentBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                        .where(DocumentBeanDao.Properties.Id.eq(relatedDocumentIdSetBeans.get(j).getRelatedDocumentId()))
                        .where(DocumentBeanDao.Properties.PayClassifyName.notEq("照片AND视频"))
                        .list();
                if (documentBeans != null && !documentBeans.isEmpty()) {
                    fileBeanList.add(documentBeans.get(0));
                }
            }
        }
        gridList.clear();
        if (relatedDocumentIdSetBeans != null && !relatedDocumentIdSetBeans.isEmpty()) {
            for (int j = 0; j < relatedDocumentIdSetBeans.size(); j++) {
                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                List<DocumentBean> documentBeans2 = documentBeanDao.queryBuilder()
                        .where(DocumentBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                        .where(DocumentBeanDao.Properties.Id.eq(relatedDocumentIdSetBeans.get(j).getRelatedDocumentId()))
                        .where(DocumentBeanDao.Properties.PayClassifyName.eq("照片AND视频"))
                        .list();
                if (documentBeans2 != null && !documentBeans2.isEmpty()) {
                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeans = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                            .where(FileBeanDao.Properties.DocumentId.eq(documentBeans2.get(0).getId()))
                            .list();
                    gridList.addAll(fileBeans);

                }

            }
        }

        File3Adapter fileAdapter = new File3Adapter(context, fileBeanList);
        viewHolder.lvFile.setAdapter(fileAdapter);

        fileAdapter.setOnDel(new File3Adapter.OnDel() {
            @Override
            public void onDel(int position) {
                RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = relatedDocumentIdSetBeanDao.queryBuilder()
                        .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(list.get(i).getCheckFileId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(list.get(i).getCheckGroupId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(list.get(i).getId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.RelatedDocumentId.eq(fileBeanList.get(position).getId()))
                        .list();
                relatedDocumentIdSetBeanDao.deleteByKey(relatedDocumentIdSetBeans.get(0).getUId());
                fileBeanList.remove(position);
                fileAdapter.notifyDataSetChanged();
            }
        });


        GridAdapter gridAdapter = new GridAdapter(gridList, context);
        viewHolder.gvVideo.setAdapter(gridAdapter);
        gridAdapter.setOnDel(new GridAdapter.OnDel() {
            @Override
            public void onDel(int position) {

                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
                List<DocumentBean> documentBeans2 = documentBeanDao.queryBuilder()
                        .where(DocumentBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                        .where(DocumentBeanDao.Properties.Id.eq(gridList.get(position).getDocumentId()))
                        .where(DocumentBeanDao.Properties.PayClassifyName.eq("照片AND视频"))
                        .list();
                if (documentBeans2 != null && !documentBeans2.isEmpty()) {
                    FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
                    List<FileBean> fileBeans = fileBeanDao.queryBuilder()
                            .where(FileBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                            .where(FileBeanDao.Properties.DocumentId.eq(documentBeans2.get(0).getId()))
                            .list();
                    for (int k = 0; k < fileBeans.size(); k++) {
                        if (gridList.get(position).equals(fileBeans.get(k).getPath())) {
                            fileBeanDao.deleteByKey(fileBeans.get(k).getUId());
                            FileUtils.delFile(gridList.get(position).getPath());
                            break;
                        }
                    }
                    RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                    List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = relatedDocumentIdSetBeanDao.queryBuilder()
                            .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                            .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(list.get(i).getCheckFileId()))
                            .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(list.get(i).getCheckGroupId()))
                            .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(list.get(i).getId()))
                            .where(RelatedDocumentIdSetBeanDao.Properties.RelatedDocumentId.eq(documentBeans2.get(0).getId()))
                            .list();
                    relatedDocumentIdSetBeanDao.deleteByKey(relatedDocumentIdSetBeans.get(0).getUId());

                    documentBeanDao.deleteByKey(documentBeans2.get(0).getUId());
                }

                gridList.remove(position);
                gridAdapter.notifyDataSetChanged();
            }
        });


        viewHolder.lvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int iiii, long l) {

                RelatedDocumentIdSetBeanDao relatedDocumentIdSetBeanDao = MyApplication.getInstances().getRelatedDocumentIdSetDaoSession().getRelatedDocumentIdSetBeanDao();
                List<RelatedDocumentIdSetBean> relatedDocumentIdSetBeans = relatedDocumentIdSetBeanDao.queryBuilder()
                        .where(RelatedDocumentIdSetBeanDao.Properties.DataPackageId.eq(list.get(i).getDataPackageId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckFileId.eq(list.get(i).getCheckFileId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckGroupId.eq(list.get(i).getCheckGroupId()))
                        .where(RelatedDocumentIdSetBeanDao.Properties.CheckItemId.eq(list.get(i).getId()))
                        .list();
                context.startActivityForResult(Add2Activity.openIntent(context,
                        list.get(i).getDataPackageId(),
                        fileBeanList.get(iiii).getId(),
                        true, list.get(iiii)), 111);

            }
        });

        return view;


    }


    public interface Relevance {
        void setRelevance(int pos, View view);

        void setResume();
    }

    private Relevance relevance;

    public void setRelevance(Relevance relevance) {
        this.relevance = relevance;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.bt_relevance)
        TextView btRelevance;
        @BindView(R.id.lv_yes_no)
        MyGridView lvYesNo;
        @BindView(R.id.gv_pro)
        MyGridView gvPro;
        @BindView(R.id.lv_file)
        MyListView lvFile;
        @BindView(R.id.gv_video)
        MyGridView gvVideo;
        @BindView(R.id.ll_pro)
        LinearLayout ll_pro;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

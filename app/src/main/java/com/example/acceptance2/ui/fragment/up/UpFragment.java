package com.example.acceptance2.ui.fragment.up;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.acceptance2.R;
import com.example.acceptance2.adapter.UPAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.bean.TitleBean;
import com.example.acceptance2.ui.activity.main.UPActivity;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.ToastUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class UpFragment extends BaseFragment {

    @BindView(R.id.lv_up)
    ListView lvUp;
    @BindView(R.id.lv_u)
    ListView lvU;
    @BindView(R.id.help_loading)
    RelativeLayout helpLoading;
    private UPAdapter upAdapter;
    private UPAdapter upAdapter2;
    private List<TitleBean> list = new ArrayList<>();
    private List<TitleBean> listUp = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    helpLoading.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    helpLoading.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(getActivity(), "导入成功");
                    break;
            }

        }
    };

    @Override
    protected void initEventAndData() {

        final StorageManager sm = (StorageManager) getActivity().getSystemService(Context.STORAGE_SERVICE);
        String[] volumePaths = new String[0];
        try {
            final Method method = sm.getClass().getMethod("getVolumePaths");
            if (null != method) {
                method.setAccessible(true);
                volumePaths = (String[]) method.invoke(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if ((volumePaths != null) && (volumePaths.length > 1)) {
            Log.e("TAG", "sdcardPath:" + volumePaths[1]);
            File file = new File(volumePaths[1]);
            listUp.add(new TitleBean(file.getName(), file.getPath()));
        }

        upAdapter2 = new UPAdapter(listUp, getActivity());
        lvUp.setAdapter(upAdapter2);

        upAdapter = new UPAdapter(list, getActivity());
        lvU.setAdapter(upAdapter);

        lvUp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listUp.size(); i++) {
                    listUp.get(position).setCheck(false);
                }
                listUp.get(position).setCheck(true);
                upAdapter2.notifyDataSetChanged();

                File file = new File(listUp.get(position).getCheckFileId());
                File[] files = file.listFiles();
                list.clear();
                upAdapter.notifyDataSetChanged();
                if (files!=null){
                    for (int i = 0; i < files.length; i++) {
                        list.add(new TitleBean(files[i].getName(), files[i].getPath()));
                    }
                }
                upAdapter.notifyDataSetChanged();
            }
        });


        lvU.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = new File(list.get(position).getCheckFileId());
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    list.clear();
                    upAdapter.notifyDataSetChanged();
                    for (int i = 0; i < files.length; i++) {
                        list.add(new TitleBean(files[i].getName(), files[i].getPath()));
                    }
                    upAdapter.notifyDataSetChanged();
                } else {
                    String end = file.getName().substring(file.getName().length() - 3, file.getName().length());
                    if (end.equals("zip")) {

                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                handler.sendEmptyMessage(1);
                                FileUtils.copyFile(file.getPath(), Environment.getExternalStorageDirectory() + "/数据包/" + file.getName());
                                handler.sendEmptyMessage(2);
                                getActivity().finish();
                            }
                        }.start();


//                        Intent intent=getIntent();
//                        intent.putExtra("paht",file.getPath());
//                        setResult(RESULT_OK,intent);
                    } else {
                        ToastUtils.getInstance().showTextToast(getActivity(), "此文件无效");
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_up;
    }
}

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
import com.example.acceptance2.adapter.main.ToAdapter;
import com.example.acceptance2.base.BaseFragment;
import com.example.acceptance2.bean.PackageBean;
import com.example.acceptance2.utils.FileUtils;
import com.example.acceptance2.utils.ToastUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;


public class UpFragment2 extends BaseFragment {


    @BindView(R.id.lv_checklist)
    ListView lvChecklist;
    @BindView(R.id.help_loading)
    RelativeLayout helpLoading;
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
                    ToastUtils.getInstance().showTextToast(getActivity(), "导出成功");
                    break;
                case 3:
                    helpLoading.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(getActivity(), "导出失败");
                    break;
            }

        }
    };

    private List<PackageBean> list = new ArrayList<>();

    @Override
    protected void initEventAndData() {


        File files = new File(Environment.getExternalStorageDirectory() + "/数据包");
        if (!files.exists()) {
            files.mkdirs();
        }
        File[] subFile = files.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            String filename = subFile[i].getName();
            File file = new File(filename);
            /* 取得扩展名 */
            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
            if (end.equals("zip")) {
                list.add(new PackageBean(filename, subFile[i] + ""));
            }

        }
        ToAdapter toAdapter = new ToAdapter(getActivity(), list);
        lvChecklist.setAdapter(toAdapter);

        lvChecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                    Log.e("TAG", "sdcardPath:" + volumePaths[0]);

                    String[] finalVolumePaths = volumePaths;
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            handler.sendEmptyMessage(1);
                            boolean b = FileUtils.copyFile(list.get(position).getPath(), finalVolumePaths[1] + "/" + list.get(position).getName());
                            if (b){
                                handler.sendEmptyMessage(2);
                            }else {
                                handler.sendEmptyMessage(3);
                            }
                            Log.e("TAG", "run: "+list.get(position).getPath()+"-------------"+finalVolumePaths[1]+"/"+list.get(position).getName());

                        }
                    }.start();


                }else {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请插入U盘");
                }

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_up2;
    }
}

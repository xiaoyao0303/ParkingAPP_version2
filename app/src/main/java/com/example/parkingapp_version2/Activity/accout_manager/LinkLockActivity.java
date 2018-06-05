package com.example.parkingapp_version2.Activity.accout_manager;

/**
 * Created by Dongqing Yao on 2018/4/10.
 * “我”界面的子界面——绑定车锁
 **/


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.parkingapp_version2.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class LinkLockActivity extends AppCompatActivity {
    /**
     * 扫描跳转Activity RequestCode
     **/
    //定义一个全局的静态常量
    public static final  int REQUEST_CODE = 001;
    private EditText idWrite;
    private EditText locationWrite;
    private Button btnScaning;
    private ImageView ivCamera;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private String result = "";
    /**
     * 扫描跳转按钮
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_activity_link_lock);

        idWrite = (EditText) findViewById(R.id.et_lock_id_write);
        locationWrite = (EditText) findViewById(R.id.et_lock_location_write);
        ivCamera = (ImageView)findViewById(R.id.iv_add_pic) ;


        /**
         * 添加车位照片
         */
        //跳转到添加照片选择页面 CameraPopupMenuActivity
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });


        /**
         * 处理扫描二维码
         */
        //初始化
        ZXingLibrary.initDisplayOpinion(this);
        //扫描时权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1 );
        }
        //找控件
        btnScaning=(Button)findViewById(R.id.btn_scanning);
        //点击事件
        btnScaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LinkLockActivity.this,ScanMyCameraActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }


    /**
     * 处理二维码扫描结果，即扫描回传值
     **/
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_CODE){
            Bundle bundle = data.getExtras();
            //处理扫描结果（在界面上显示）
            if (bundle == null){
                return;}
            if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                //拿到解析扫描到的信息，并转成字符串
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                //在idWrite中显示得到的id信息
                idWrite.getText().insert(0,result);
            }
            else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(LinkLockActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 添加照片显示popupWindow,以及其中的点击事件
     * @param v
     */

    private void showPopupWindow(View v) {
        View view = LayoutInflater.from(LinkLockActivity.this).inflate(R.layout.item_popup, null, false);
        Button bCamera = (Button) view.findViewById(R.id.btn_add_pic_camera);
        Button bLocal = (Button) view.findViewById(R.id.btn_add_pic_local);
        Button bDelete = (Button) view.findViewById(R.id.btn_delete);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;
        final PopupWindow popWindow = new PopupWindow(view, weight, height, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAtLocation(v, Gravity.BOTTOM,0, 0);


        //设置popupWindow里的按钮的事件
        bCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,1);
                popWindow.dismiss();
            }
        });
        bLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent();
                localIntent.setType("image/*");
                localIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(localIntent,2);
                popWindow.dismiss();
            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.setTouchable(false);
                popWindow.dismiss();
            }
        });

        //popupWindow消失屏幕变为不透明
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popWindow.showAtLocation(view, Gravity.BOTTOM,0,0);


    }
}

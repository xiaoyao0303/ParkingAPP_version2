package com.example.parkingapp_version2.Activity.accout_manager;
/**
 * Created by Dongqing Yao on 2018/4/2.
 * “我”界面的子界面——“我的评价”
 **/

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.parkingapp_version2.R;

public class MyEvaluateActivity extends AppCompatActivity {

    final private int LOCAL = 110;
    final private int WEB = 111;

    private Button bBack;
    private Button bSubmitEvaluate;
    private Button bAddPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_activity_my_evaluate);
        initView();
    }

    private void initView() {
        bBack = (Button) this.findViewById(R.id.back);
        bSubmitEvaluate = (Button) this.findViewById(R.id.bt_submit_evaluate);
        bAddPicture = (Button) this.findViewById(R.id.bt_add_picture);

        OnClick onClick = new OnClick();
        bBack.setOnClickListener(onClick);
        bSubmitEvaluate.setOnClickListener(onClick);
        bAddPicture.setOnClickListener(onClick);


    }

    public class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back:
//                    Intent i = new Intent(MyEvaluateActivity.this, MyMainFragment.class);
//                    startActivity(i);
                case R.id.bt_submit_evaluate:
                    Intent intent = new Intent(MyEvaluateActivity.this, EvaSubmit.class);
                    startActivity(intent);
                case R.id.bt_add_picture:
                    showPopupWindow(v);
            }
        }
    }


    private void showPopupWindow(View v) {
        View view = LayoutInflater.from(MyEvaluateActivity.this).inflate(R.layout.item_popup, null, false);
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

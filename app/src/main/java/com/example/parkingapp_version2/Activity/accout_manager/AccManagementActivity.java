package com.example.parkingapp_version2.Activity.accout_manager;

/**
 * Created by Dongqing Yao on 2018/4/8.
 * “我”界面的子界面——“账号管理”
 **/

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.parkingapp_version2.MainActivity;
import com.example.parkingapp_version2.R;

import java.util.Calendar;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class AccManagementActivity extends AppCompatActivity {

    private ImageView userImageView;
    private ImageView blurImageView;

    private Button mGender;
    private Button mDate;
    private Button mSave;
    private Button back;
    private EditText mSignature;
    private EditText mAlterSignature;
    private EditText mIdentity;
    private EditText mPaying;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private String result = "";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_activity_acc_management);



        mGender = (Button) findViewById(R.id.btn_gender);
        mDate = (Button) findViewById(R.id.btn_date);
        mSave = (Button) findViewById(R.id.btn_save);


        //跳转到性别选项的对话框
        mGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] sexs = new String[]{"男","女"};
                alert = null;
                builder = new AlertDialog.Builder(AccManagementActivity.this);
                alert = builder.setTitle("请选择性别：")
                        .setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mGender.setText(sexs[which]);
                            }
                        }).create();
                alert.show();
            }
        });

        //跳转到生日选择对话框
        Calendar calendar = Calendar.getInstance();
        mDate.setHint(calendar.get(Calendar.YEAR)+ "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(AccManagementActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        result += year + "-" +( monthOfYear+1 )+"-" + dayOfMonth;
                        mDate.setText(result);
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }







            /**
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(AccManagementActivity.this, null,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setCancelable(true);
                datePickerDialog.setCanceledOnTouchOutside(true);
                datePickerDialog.setTitle("请选择出生日期：");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确认",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker picker = datePickerDialog.getDatePicker();
                        int year = picker.getYear();
                        int monthOfYear = picker.getMonth();
                        int dayOfMonth = picker.getDayOfMonth();
                        mDate.setText(year+"-"+(++monthOfYear)+"-"+dayOfMonth);

                    }
                });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


            }
             **/
        });

        //个性签名编辑框监听器
        mSignature = findViewById(R.id.et_signature);
        mSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignature.requestFocus();
                mSignature.setFocusable(true);
                mSignature.setFocusableInTouchMode(true);
            }
        });
        //昵称编辑框监听器
        mAlterSignature = findViewById(R.id.et_alter_nickname);
        mAlterSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlterSignature.requestFocus();
                mAlterSignature.setFocusable(true);
                mAlterSignature.setFocusableInTouchMode(true);
            }
        });
//        //电话编辑框监听器
//        mTele = findViewById(R.id.et_tele);
//        mTele.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTele.requestFocus();
//                mTele.setFocusable(true);
//                mTele.setFocusableInTouchMode(true);
//            }
//        });
        //身份证编辑框监听器
        mIdentity = findViewById(R.id.et_identity);
        mIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIdentity.requestFocus();
                mIdentity.setFocusable(true);
                mIdentity.setFocusableInTouchMode(true);
            }
        });
        //支付宝编辑框监听器
        mPaying = findViewById(R.id.et_paying);
        mPaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaying.requestFocus();
                mPaying.setFocusable(true);
                mPaying.setFocusableInTouchMode(true);
            }
        });
        //保存按钮监听器
        mSave = findViewById(R.id.btn_save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccManagementActivity.this,"保存成功！",Toast.LENGTH_SHORT).show();
            }
        });
        //返回按钮监听器
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccManagementActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}

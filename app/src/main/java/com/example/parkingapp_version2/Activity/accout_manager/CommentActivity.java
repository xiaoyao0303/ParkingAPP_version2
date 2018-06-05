package com.example.parkingapp_version2.Activity.accout_manager;

/**
 * Created by Dongqing Yao on 2018/3/29.
 * “我”界面的子界面——“意见与建议”
 **/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parkingapp_version2.R;

public class CommentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_activity_comment);

        //返回按钮监视器
        final Button bBack = (Button) this.findViewById(R.id.back);
        bBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(CommentActivity.this,MyMainFragment.class);
//                        startActivity(i);
                    }
                }
        );

        //单选按钮监视器
        final RadioGroup rGroup1 = (RadioGroup) this.findViewById(R.id.radio_group1);
        final RadioGroup rGroup2 = (RadioGroup) this.findViewById(R.id.radio_group2);
        final RadioButton rNavigation = (RadioButton) this.findViewById(R.id.rd_navigation);
        final RadioButton rConsult = (RadioButton) this.findViewById(R.id.rd_consult);
        final RadioButton rLocation = (RadioButton) this.findViewById(R.id.rd_location);
        final RadioButton dOther = (RadioButton) this.findViewById(R.id.rd_other);

        rGroup1.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener(){
                    public void onCheckedChanged(RadioGroup radioGroup, int position) {
                        switch (position) {
                            case R.id.rd_navigation:
                                if(rNavigation.isChecked())
                                    rGroup2.clearCheck();
                                break;
                            case R.id.rd_consult:
                                if(rConsult.isChecked())
                                    rGroup2.clearCheck();
                                break;
                        }
                    }
                 }
        );
        rGroup2.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener(){
                    public void onCheckedChanged(RadioGroup radioGroup, int position) {
                        switch (position) {
                            case R.id.rd_location:
                                if (rLocation.isChecked())
                                    rGroup1.clearCheck();
                                break;
                            case R.id.rd_other:
                                if (dOther.isChecked())
                                    rGroup1.clearCheck();
                                break;
                        }
                    }
                }
        );
        //文本编辑器满150字提醒
        final EditText editText1 = (EditText) this.findViewById(R.id.comment_text);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>=150){
                    Toast.makeText(CommentActivity.this,"文字超过150",Toast.LENGTH_LONG).show();
                }

            }
        });
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.requestFocus();
                editText1.setFocusable(true);
                editText1.setFocusableInTouchMode(true);
            }
        });

        final EditText editText2 = (EditText) this.findViewById(R.id.et_mail);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText2.requestFocus();
                editText2.setFocusable(true);
                editText2.setFocusableInTouchMode(true);
            }
        });

        final EditText editText3 = (EditText) this.findViewById(R.id.et_contact);
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText3.requestFocus();
                editText3.setFocusable(true);
                editText3.setFocusableInTouchMode(true);
            }
        });

        //“提交”按钮监视器
        Button bSubmit = (Button) this.findViewById(R.id.bt_submit);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CommentActivity.this,"成功提交！",Toast.LENGTH_LONG).show();
            }
        });

    }
}

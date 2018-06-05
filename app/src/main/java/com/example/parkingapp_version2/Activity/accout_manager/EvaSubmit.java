package com.example.parkingapp_version2.Activity.accout_manager;

/**
 * Created by Dongqing Yao on 2018/4/2.
 * “我”界面的子界面——“我的评价”的子界面——“提交成功”
 **/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.parkingapp_version2.R;

public class EvaSubmit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_activity_eva_submit);

        final Button bBack = (Button) this.findViewById(R.id.back);
        bBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(EvaSubmit.this,MyMainFragment.class);
//                        startActivity(i);
                    }
                }
        );
    }
}

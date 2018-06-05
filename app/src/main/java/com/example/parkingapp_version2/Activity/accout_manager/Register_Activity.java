package com.example.parkingapp_version2.Activity.accout_manager;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingapp_version2.R;

public class Register_Activity extends AppCompatActivity{

    Button register;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_register_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }
//        register = (Button) findViewById(R.id.register_button);
//        //back = (TextView)((View)findViewById(R.id.register_head)).findViewById(R.id.register_back);
//        back = (TextView)findViewById(R.id.register_back);
//        register.setOnClickListener(this);
//        back.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId())
//        {
//            case R.id.register_button:
//                Toast.makeText(Register_Activity.this,"register",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.register_back:
//                this.finish();
//                break;
//        }
    }
}

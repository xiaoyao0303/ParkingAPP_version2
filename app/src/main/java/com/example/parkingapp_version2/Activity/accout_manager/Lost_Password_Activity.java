package com.example.parkingapp_version2.Activity.accout_manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingapp_version2.R;

public class Lost_Password_Activity extends AppCompatActivity {

    TextView back;
    Button correct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_lost_password_layout);
        back = (TextView)findViewById(R.id.cancel_lost_password);
        back.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        correct = (Button) findViewById(R.id.resetPassword);
        correct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(Lost_Password_Activity.this,"开始修改",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

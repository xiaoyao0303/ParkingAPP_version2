package com.example.parkingapp_version2.Activity.accout_manager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.parkingapp_version2.R;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class ScanMyCameraActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_my_camera);

        btn = (Button) findViewById(R.id.back_camera);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ScanMyCameraActivity.this,LinkLockActivity.class);
                startActivity(intent);
            }
        });


        /**
         * 执行扫描Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        /**
         * 为二维码扫描界面设置定制化界面
         */
        CodeUtils.setFragmentArgs(captureFragment, R.layout.activity_scan_my_layout);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }


    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ScanMyCameraActivity.this.setResult(RESULT_OK, resultIntent);
            ScanMyCameraActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanMyCameraActivity.this.setResult(RESULT_OK, resultIntent);
            ScanMyCameraActivity.this.finish();
        }
    };
}


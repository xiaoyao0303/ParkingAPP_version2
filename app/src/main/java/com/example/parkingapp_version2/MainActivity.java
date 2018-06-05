package com.example.parkingapp_version2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.parkingapp_version2.Activity.accout_manager.AccManagementActivity;
import com.example.parkingapp_version2.Activity.accout_manager.CommentActivity;
import com.example.parkingapp_version2.Activity.accout_manager.LinkLockActivity;
import com.example.parkingapp_version2.Activity.accout_manager.LoginActivity;


public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;//侧边菜单视图
    private ActionBarDrawerToggle mDrawerToggle;  //菜单开关
    private NavigationView mNavigationView;


    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private MenuItem mPreMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //导入Toolbar、 DrawerLayout、NavigationView 布局
        initView();
        //init the default checked fragment
        initDefaultFragment();

    }



    //init the default checked fragment
    private void initDefaultFragment() {
//        mCurrentFragment = ViewUtils.createFragment(MainFragment.class);
//
//        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        mPreMenuItem = mNavigationView.getMenu().getItem(0);
        mPreMenuItem.setChecked(true);


    }


    //导入Toolbar、 DrawerLayout、NavigationView 布局
    @SuppressLint("ResourceType")
    private void initView() {


        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("智慧停车");
        setSupportActionBar(mToolbar);  //设置Toolbar

        mNavigationView = findViewById(R.id.navigation_view);
      //  mNavigationView.inflateHeaderView(R.layout.navigation_headerlayout);
      //  mNagivationHeaderView = mNavigationView.getHeaderView(0);
        setNavigationViewItemClickListener();
      //  setNagivationHeaderItemClickListener();


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                mToolbar,0,1);   //将DrawerLayout和Toolbar关联
        mDrawerToggle.syncState();  //进行同步
        //   mDrawerLayout.addDrawerListener(mDrawerToggle);     //加上这句会有一个小的点击翻转动画


    }


    /**
     * drawerView的Item的点击事件
     */
    private void setNavigationViewItemClickListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = null;
               if(mPreMenuItem != null){
                   mPreMenuItem.setChecked(false);
               }
               switch (item.getItemId()){
                   case R.id.navigation_item_account:
                       intent = new Intent(MainActivity.this,AccManagementActivity.class);
                       break;
                   case R.id.navigation_item_link:
                       intent = new Intent(MainActivity.this, LinkLockActivity.class);
                       break;
                   case R.id.navigation_item_comment:
                       intent = new Intent(MainActivity.this, CommentActivity.class);
                       break;
                   default:
                       break;
               }
               item.setChecked(true);
               mDrawerLayout.closeDrawer(Gravity.LEFT);
               startActivity(intent);
               mPreMenuItem=item;
               return false;
            }
        });
    }

//    /**
//     * Navigation头部中登录、注册以及头像的点击事件
//     */
//    private void setNagivationHeaderItemClickListener() {
//        Button mLogin =(Button) mNagivationHeaderView.findViewById(R.id.login);
//        Button mRegister = (Button) mNagivationHeaderView.findViewById(R.id.register);
//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

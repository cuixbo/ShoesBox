package com.cuixbo.shoesbox.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author xiaobocui
 * @date 2019-12-16
 */
public class SettingActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
    }
}

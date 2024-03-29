package com.bqlab.themelab.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.bqlab.themelab.R;
import com.bqlab.themelab.custom.ApplicationDetector;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.setting_actionbar_title);
        getSupportActionBar().setHomeButtonEnabled(true);

        Switch settingPushItem1 = (Switch) findViewById(R.id.setting_push_item01);

        settingPushItem1.setChecked(getSharedPreferences("settings", MODE_PRIVATE).getBoolean("UPDATE_NEWS", true));
        settingPushItem1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("settings", MODE_PRIVATE).edit().putBoolean("UPDATE_NEWS", isChecked).apply();
            }
        });

        findViewById(R.id.setting_info_item01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(SettingActivity.this);
                a.setTitle(getResources().getString(R.string.setting_info_copyright));
                a.setMessage(getResources().getString(R.string.setting_info_copyright_content));
                a.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                a.show();
            }
        });

        findViewById(R.id.setting_info_item02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new ApplicationDetector(SettingActivity.this).hasApplication("com.facebook.katana")) {
                    String url = getResources().getString(R.string.app_fb_homepage);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } else {
                    String url = getResources().getString(R.string.app_homepage);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            }
        });
    }
}

package com.yydcdut.note.controller.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yydcdut.note.R;
import com.yydcdut.note.controller.BaseActivity;
import com.yydcdut.note.utils.LollipopUtils;

/**
 * Created by yuyidong on 15/9/2.
 */
public class AboutAppActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int setContentView() {
        return R.layout.activity_about;
    }

    @Override
    public void initUiAndListener() {
        initToolBarUI();
        initListener();
        try {
            initVersion();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initToolBarUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.about_setting));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        LollipopUtils.setElevation(toolbar, getResources().getDimension(R.dimen.ui_elevation));
    }

    private void initListener() {
        findViewById(R.id.layout_ripple_update).setOnClickListener(this);
        findViewById(R.id.layout_ripple_contact).setOnClickListener(this);
        findViewById(R.id.layout_ripple_share).setOnClickListener(this);
        findViewById(R.id.layout_ripple_github).setOnClickListener(this);
    }

    private void initVersion() throws PackageManager.NameNotFoundException {
        PackageManager manager = this.getPackageManager();
        PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
        String version = info.versionName;
        ((TextView) findViewById(R.id.txt_version)).setText(getResources().getString(R.string.version) + " " + version);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_update:
                Toast.makeText(this, "目前暂时不支持直接升级~", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.yydcdut.note");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.layout_contact:
                Toast.makeText(this, "目前暂时不支持对话级别联系我们~", Toast.LENGTH_SHORT).show();
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:378040621@qq.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.about_contact));
                data.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.about_contact));
                startActivity(data);
                break;
            case R.id.layout_share:
                Toast.makeText(this, "目前暂时不支持直接分享~", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getText(R.string.about_share_content));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.about_share_content)));
                break;
            case R.id.layout_github:
                Toast.makeText(this, "下个版本我们将开源~尽请期待!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

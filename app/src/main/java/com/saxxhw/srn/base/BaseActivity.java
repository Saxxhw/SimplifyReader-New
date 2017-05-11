package com.saxxhw.srn.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.saxxhw.srn.R;
import com.saxxhw.srn.utils.AtyManagerUtils;
import com.saxxhw.srn.widget.state.VaryViewHelperController;

import butterknife.ButterKnife;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：Activity基类(无MVP)
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;

    protected Toolbar mToolBar;

    private ProgressDialog mProgressDialog;

    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AtyManagerUtils.getInstance().addActivity(this);
        mContext = this;
        initToolBar();
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        bindListener();
        initEventAndData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AtyManagerUtils.getInstance().removeActivity(this);
    }

    /**
     * 初始化标题栏控件
     */
    @SuppressWarnings("ConstantConditions")
    private void initToolBar() {
        mToolBar = ButterKnife.findById(this, R.id.toolbar);
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            if (!hideBackButton()) {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    /**
     * startActivity
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 展示Toast
     * @param resId
     */
    protected void showToast(int resId){
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示Toast
     * @param str
     */
    protected void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示进度条
     */
    protected void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }

    /**
     * 隐藏进度条
     */
    protected void hideProgressDialog() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading();
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEmptyClick();
                }
            });
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError();
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected abstract int getLayout();

    protected abstract View getLoadingTargetView();

    protected abstract void getBundleExtras(Bundle extras);

    protected abstract void bindListener();

    protected abstract void initEventAndData();

    protected abstract boolean hideBackButton();

    protected void onEmptyClick() {

    }
}

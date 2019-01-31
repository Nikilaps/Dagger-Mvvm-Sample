package com.mvvm.mvvmdagger.ui.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.mvvm.mvvmdagger.BR
import com.mvvm.mvvmdagger.R
import com.mvvm.mvvmdagger.databinding.ActivityMainBinding
import com.mvvm.mvvmdagger.ui.base.BaseActivity
import com.mvvm.mvvmdagger.ui.home.HomeActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityMainBinding, LoginViewModel>() {

    @Inject
    lateinit var mLoginViewModel: LoginViewModel

    private var mActivityLoginBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityLoginBinding = getViewDataBinding()

        mLoginViewModel.mResponseMsg.observe(this, Observer { msg ->
            if (!TextUtils.isEmpty(msg))
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        mLoginViewModel.mIsLogin.observe(this, Observer { isLogin ->
            if (isLogin!!) {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        })

        mLoginViewModel.mLoadingVisibility.observe(this, Observer { visible ->
            if (visible == View.VISIBLE) {
                showLoading()
            } else {
                hideLoading()
            }

        })
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): LoginViewModel {
        return mLoginViewModel
    }
}

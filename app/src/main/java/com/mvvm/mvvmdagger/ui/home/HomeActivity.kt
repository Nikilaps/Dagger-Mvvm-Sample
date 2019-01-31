package com.mvvm.mvvmdagger.ui.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mvvm.mvvmdagger.BR
import com.mvvm.mvvmdagger.R
import com.mvvm.mvvmdagger.data.model.db.User
import com.mvvm.mvvmdagger.databinding.ActivityHomeBinding
import com.mvvm.mvvmdagger.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    @Inject
    lateinit var mHomeViewModel: HomeViewModel

    private var userAdapter: UserAdapter? = null

    private var mHomeBinding: ActivityHomeBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return mHomeViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getViewDataBinding()
        userAdapter = UserAdapter(userClickCallBack)
        mHomeBinding!!.recUser.adapter = userAdapter
        mHomeViewModel.getUsers()

        mHomeViewModel.mLoadingVisibility.observe(this, Observer { visible ->
            if (visible == View.VISIBLE) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        mHomeViewModel.mUserLiveList.observe(this, Observer { userList ->
            mHomeViewModel.addUserItemsToList(userList!!)
            userAdapter!!.clearItems()
            userAdapter!!.addItems(userList)
        })
    }

    private val userClickCallBack = object : UserClickCallback {
        override fun onClick(user: User) {
            Toast.makeText(applicationContext, user.email, Toast.LENGTH_SHORT).show()
        }
    }

}

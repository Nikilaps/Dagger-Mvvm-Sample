package com.mvvm.mvvmdagger.ui.login

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.mvvm.mvvmdagger.data.db.AppDbHelper
import com.mvvm.mvvmdagger.data.model.db.User
import com.mvvm.mvvmdagger.data.remote.ApiInterface
import com.mvvm.mvvmdagger.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val mApiInterface: ApiInterface,
    private val mAppDbHelper: AppDbHelper
) : BaseViewModel() {

    private val mUser: User = User(0, "", "")

    val mResponseMsg: MutableLiveData<String> = MutableLiveData()
    val mLoadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val mIsLogin: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Function is used to get text from email edit text in xml.
     */
    fun afterEmailTextChanged(s: CharSequence) {
        mUser.email = s.toString()
    }

    /**
     * Function is used to get text from password edit text in xml.
     */
    fun afterPassTextChanged(s: CharSequence) {
        mUser.password = s.toString()
    }

    /**
     * Login button click function.
     */
    fun onLoginClicked() {
        if (mUser.isValidUser()) {
            login()
        } else {
            mResponseMsg.value = mErrorMsg
        }
    }

    /**
     * login Api call.
     */
    private fun login() {
        getCompositeDisposable().add(
            mApiInterface.login(mUser.email, mUser.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mLoadingVisibility.value = View.VISIBLE }
                .doOnTerminate { mLoadingVisibility.value = View.GONE }
                .subscribe({ response ->
                    if (response == null) return@subscribe
                    else {
                        mUser.id = 100
                        mAppDbHelper.insertUser(mUser)
                        mIsLogin.value = response.isSuccess
                        mResponseMsg.value = mSuccessMsg
                    }

                }, { throwable ->
                    mResponseMsg.value = throwable.toString()
                    mIsLogin.value = false
                })
        )
    }

    companion object {
        private const val mSuccessMsg: String = "Login successful"
        private const val mErrorMsg: String = "Email or Password not valid"
    }
}
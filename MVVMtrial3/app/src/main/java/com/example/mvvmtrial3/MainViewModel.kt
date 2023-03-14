package com.example.mvvmtrial3

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-12-16
 * @desc
 */
class MainViewModel : ViewModel() {
    var isPressed = MutableLiveData<Boolean>(false)

    fun onClickCamera(v: View) {
        isPressed.value = isPressed.value?.not()
    }
}
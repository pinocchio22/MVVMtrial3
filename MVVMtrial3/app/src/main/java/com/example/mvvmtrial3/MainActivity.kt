package com.example.mvvmtrial3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtrial3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터 바인딩 객체 생성
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 뷰모델 생성
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // isPressed 변수 관찰
        mainViewModel.isPressed.observe(this) {
            if (it) {
                binding.cameraName.text = "눌림"
            } else {
                binding.cameraName.text = "안눌림"
            }
        }

        with(binding) {
            // xml의 viewmodel과 View에서 생성한 viewmodel을 바인딩
            viewModel = mainViewModel
        }
    }
}
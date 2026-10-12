package com.ute.quanlycongviec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ten = intent.getStringExtra("KEY_TEN") ?: ""
        val moTa = intent.getStringExtra("KEY_MOTA") ?: ""

        binding.tvHienThiTen.text = "Tên công việc: $ten"
        binding.tvHienThiMoTa.text = "Mô tả: $moTa"
    }
}
package com.ute.quanlycongviec

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTaoCongViec.setOnClickListener {
            val ten = binding.edtTenCongViec.text.toString().trim()
            val moTa = binding.edtMoTaCongViec.text.toString().trim()

            if (ten.isEmpty()) {
                binding.edtTenCongViec.error = "Tên công việc không được để trống!"
                android.widget.Toast.makeText(this, "Vui lòng nhập tên công việc!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val congViecMoi = CongViec(tenCongViec = ten, moTa = moTa, trangThai = false)
            val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
                putExtra("KEY_CONG_VIEC", congViecMoi)
            }
            startActivity(intent)
        }
    }
}
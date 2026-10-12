package com.ute.quanlycongviec

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nut Tao cong viec
        binding.btnTaoCongViec.setOnClickListener {
            val ten = binding.edtTenCongViec.text.toString().trim()
            val moTa = binding.edtMoTaCongViec.text.toString().trim()

            // Validate khong cho phep ten cong viec de trong
            if (ten.isEmpty()) {
                binding.edtTenCongViec.error = "Tên công việc không được để trống!"
                Toast.makeText(this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Khoi tao doi tuong CongViec truyen sang man hinh chi tiet
            val congViecMoi = CongViec(tenCongViec = ten, moTa = moTa, trangThai = false)
            val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
                putExtra("KEY_CONG_VIEC", congViecMoi)
            }
            startActivity(intent)
        }

        // Nut Xem danh sach cong viec
        binding.btnXemDanhSach.setOnClickListener {
            Toast.makeText(this, "Tính năng xem danh sách đang phát triển!", Toast.LENGTH_SHORT).show()
        }
    }
}
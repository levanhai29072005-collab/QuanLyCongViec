package com.ute.quanlycongviec

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityChiTietCongViecBinding
import java.io.Serializable

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding
    private var congViec: CongViec? = null

    private inline fun <reified T : Serializable> Intent.laySerializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    private val boKhoiChaySua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val congViecCapNhat = ketQua.data?.laySerializable<CongViec>("KEY_CONG_VIEC_SUA")
            congViecCapNhat?.let {
                congViec = it
                capNhatGiaoDien()
                Toast.makeText(this, "Đã cập nhật công việc thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        congViec = intent.laySerializable<CongViec>("KEY_CONG_VIEC")
        capNhatGiaoDien()

        // Xu ly su kien nut Hoan thanh
        binding.btnHoanThanh.setOnClickListener {
            congViec?.let {
                it.trangThai = true
                capNhatGiaoDien()
                Toast.makeText(this, "Công việc đã được cập nhật thành hoàn thành!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("KEY_CONG_VIEC_GUI_SUA", congViec)
            }
            boKhoiChaySua.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        congViec?.let { cv ->
            binding.tvHienThiMa.text = "Mã công việc: ${cv.maCongViec}"
            binding.tvHienThiTen.text = "Tên: ${cv.tenCongViec}"
            binding.tvHienThiMoTa.text = "Mô tả: ${cv.moTa}"

            // Hien thi trang thai dua tren ham laDaHoanThanh()
            if (cv.laDaHoanThanh()) {
                binding.tvHienThiTrangThai.text = "Trạng thái: Đã hoàn thành"
                binding.tvHienThiTrangThai.setTextColor(Color.parseColor("#2E7D32"))
            } else {
                binding.tvHienThiTrangThai.text = "Trạng thái: Chưa hoàn thành"
                binding.tvHienThiTrangThai.setTextColor(Color.parseColor("#D32F2F"))
            }
        }
    }
}
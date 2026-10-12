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

        // Xu ly danh dau hoan thanh cong viec
        binding.cbDaHoanThanh.setOnCheckedChangeListener { _, isChecked ->
            congViec?.trangThai = isChecked
            capNhatTrangThaiUI(isChecked)
            val thongBao = if (isChecked) "Đã hoàn thành công việc!" else "Đã chuyển về chưa hoàn thành!"
            Toast.makeText(this, thongBao, Toast.LENGTH_SHORT).show()
        }

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("KEY_CONG_VIEC_GUI_SUA", congViec)
            }
            boKhoiChaySua.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        congViec?.let {
            binding.tvHienThiTen.text = "Tên: ${it.tenCongViec}"
            binding.tvHienThiMoTa.text = "Mô tả: ${it.moTa}"
            binding.cbDaHoanThanh.isChecked = it.trangThai
            capNhatTrangThaiUI(it.trangThai)
        }
    }

    private fun capNhatTrangThaiUI(daXong: Boolean) {
        if (daXong) {
            binding.tvHienThiTrangThai.text = "Trạng thái: Đã hoàn thành"
            binding.tvHienThiTrangThai.setTextColor(Color.parseColor("#2E7D32"))
        } else {
            binding.tvHienThiTrangThai.text = "Trạng thái: Chưa hoàn thành"
            binding.tvHienThiTrangThai.setTextColor(Color.parseColor("#D32F2F"))
        }
    }
}
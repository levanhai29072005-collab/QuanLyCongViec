package com.ute.quanlycongviec

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding
    private var tenCongViec: String = ""
    private var moTaCongViec: String = ""

    private val boKhoiChaySua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val tenMoi = ketQua.data?.getStringExtra("KEY_TEN_MOI") ?: ""
            val moTaMoi = ketQua.data?.getStringExtra("KEY_MOTA_MOI") ?: ""

            tenCongViec = tenMoi
            moTaCongViec = moTaMoi
            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tenCongViec = intent.getStringExtra("KEY_TEN") ?: ""
        moTaCongViec = intent.getStringExtra("KEY_MOTA") ?: ""
        capNhatGiaoDien()

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("KEY_SUA_TEN", tenCongViec)
                putExtra("KEY_SUA_MOTA", moTaCongViec)
            }
            boKhoiChaySua.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        binding.tvHienThiTen.text = "Tên công việc: $tenCongViec"
        binding.tvHienThiMoTa.text = "Mô tả: $moTaCongViec"
    }
}
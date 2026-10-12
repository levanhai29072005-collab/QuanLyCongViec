package com.ute.quanlycongviec

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        congViec = intent.laySerializable<CongViec>("KEY_CONG_VIEC")
        capNhatGiaoDien()

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
            binding.tvHienThiMoTa.text = it.layThongTinCongViec()
        }
    }
}
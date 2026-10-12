package com.ute.quanlycongviec

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityChinhSuaCongViecBinding
import java.io.Serializable

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChinhSuaCongViecBinding
    private var congViecHienTai: CongViec? = null

    private inline fun <reified T : Serializable> Intent.laySerializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChinhSuaCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        congViecHienTai = intent.laySerializable<CongViec>("KEY_CONG_VIEC_GUI_SUA")
        congViecHienTai?.let {
            binding.edtSuaTen.setText(it.tenCongViec)
            binding.edtSuaMoTa.setText(it.moTa)
        }

        binding.btnLuu.setOnClickListener {
            val tenMoi = binding.edtSuaTen.text.toString().trim()
            val moTaMoi = binding.edtSuaMoTa.text.toString().trim()

            if (tenMoi.isEmpty()) {
                binding.edtSuaTen.error = "Tên công việc không được để trống!"
                android.widget.Toast.makeText(this, "Vui lòng nhập tên công việc!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val congViecMoi = congViecHienTai?.copy(tenCongViec = tenMoi, moTa = moTaMoi)
                ?: CongViec(tenMoi, moTaMoi, false)

            val ketQuaIntent = Intent().apply {
                putExtra("KEY_CONG_VIEC_SUA", congViecMoi)
            }
            setResult(Activity.RESULT_OK, ketQuaIntent)
            finish()
        }
    }
}
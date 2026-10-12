package com.ute.quanlycongviec

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.quanlycongviec.databinding.ActivityChinhSuaCongViecBinding

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChinhSuaCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChinhSuaCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenHienTai = intent.getStringExtra("KEY_SUA_TEN") ?: ""
        val moTaHienTai = intent.getStringExtra("KEY_SUA_MOTA") ?: ""

        binding.edtSuaTen.setText(tenHienTai)
        binding.edtSuaMoTa.setText(moTaHienTai)

        binding.btnLuu.setOnClickListener {
            val tenMoi = binding.edtSuaTen.text.toString().trim()
            val moTaMoi = binding.edtSuaMoTa.text.toString().trim()

            val ketQuaIntent = Intent().apply {
                putExtra("KEY_TEN_MOI", tenMoi)
                putExtra("KEY_MOTA_MOI", moTaMoi)
            }
            setResult(Activity.RESULT_OK, ketQuaIntent)
            finish()
        }
    }
}
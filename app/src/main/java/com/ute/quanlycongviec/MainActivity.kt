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

            val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
                putExtra("KEY_TEN", ten)
                putExtra("KEY_MOTA", moTa)
            }
            startActivity(intent)
        }
    }
}
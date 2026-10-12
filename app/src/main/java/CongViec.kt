package com.ute.quanlycongviec
import java.io.Serializable
data class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false
) : Serializable {
    fun layThongTinCongViec(): String {
        val chuoiTrangThai = if (trangThai) "Đã hoàn thành" else "Chưa hoàn thành"
        return "Công việc: $tenCongViec | Trạng thái: $chuoiTrangThai\nMô tả: $moTa"
    }
}
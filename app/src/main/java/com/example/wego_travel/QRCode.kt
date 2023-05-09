package com.example.wego_travel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.*

class QRCode : AppCompatActivity() {
    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        val id = intent.getStringExtra(ID)
        val qrCodeImageView: ImageView = findViewById(R.id.qr_code)
        Log.v("id tiket : ", id.toString())
        val qrCodeBitmap = generateQRCode(id.toString(), 1000, 1000)

        qrCodeImageView.setImageBitmap(qrCodeBitmap)
    }

    fun generateQRCode(content: String, width: Int, height: Int): Bitmap? {
        try {

            val hints = Hashtable<EncodeHintType, Any>()
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"

            val bitMatrix: BitMatrix = MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints)

            val qrCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    qrCodeBitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            return qrCodeBitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return null
    }
}
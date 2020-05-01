package com.example.stockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_qr_code.*

class QrCode : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_qr_code)
    firstActivityGenerateButton.setOnClickListener {
      startActivity(GenerateQrCodeActivity.getGenerateQrCodeActivity(this))
    }
    firstActivityScanButton.setOnClickListener {
      startActivity(ScanQrCodeActivity.getScanQrCodeActivity(this))
    }
  }
}

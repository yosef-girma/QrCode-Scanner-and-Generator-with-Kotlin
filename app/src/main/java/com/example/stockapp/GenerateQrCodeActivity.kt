package com.example.stockapp;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.stockapp.R
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.example.stockapp.helper.EncryptionHelper
import com.example.stockapp.helper.QRCodeHelper
import com.example.stockapp.models.UserObject
import kotlinx.android.synthetic.main.activity_generate_qr_code2.*

class GenerateQrCodeActivity : AppCompatActivity() {

  companion object {

    fun getGenerateQrCodeActivity(callingClassContext: Context) = Intent(callingClassContext, GenerateQrCodeActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_generate_qr_code2)
    generateQrCodeButton.setOnClickListener {
      if (checkEditText()) {
        hideKeyboard()

        val user = UserObject(fullName = "Yosef Girma", age = 22)
        val serializeString = Gson().toJson(user)
        val encryptedString = EncryptionHelper.getInstance().encryptionString(serializeString).encryptMsg()
        setImageBitmap(encryptedString)
      }
    }
  }

  private fun setImageBitmap(encryptedString: String?) {
    val bitmap = QRCodeHelper.newInstance(this).setContent(encryptedString).setErrorCorrectionLevel(ErrorCorrectionLevel.Q).setMargin(2).qrcOde
    qrCodeImageView.setImageBitmap(bitmap)
  }

  /**
   * Hides the soft input keyboard if it is shown to the screen.
   */

  private fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  private fun checkEditText(): Boolean {
    if (TextUtils.isEmpty("k")) {
      Toast.makeText(this, "fullName field cannot be empty!", Toast.LENGTH_SHORT).show()
      return false
    } else if (TextUtils.isEmpty("kl")) {
      Toast.makeText(this, "age field cannot be empty!", Toast.LENGTH_SHORT).show()
      return false
    }
    return true
  }
}

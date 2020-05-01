package com.example.stockapp;
import android.content.Context
import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockapp.R
import com.example.stockapp.helper.EncryptionHelper
import com.google.gson.Gson
import com.example.stockapp.models.UserObject
import kotlinx.android.synthetic.main.activity_scanned2.*
import java.lang.RuntimeException

class ScannedActivity : AppCompatActivity() {

  companion object {
    private const val SCANNED_STRING: String = "scanned_string"
    fun getScannedActivity(callingClassContext: Context, encryptedString: String): Intent {
      return Intent(callingClassContext, ScannedActivity::class.java)
        .putExtra(SCANNED_STRING, encryptedString)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_scanned2)
    if (intent.getSerializableExtra(SCANNED_STRING) == null)
      throw RuntimeException("No encrypted String found in intent")
    val decryptedString = EncryptionHelper.getInstance().getDecryptionString(intent.getStringExtra(SCANNED_STRING))
    val userObject = Gson().fromJson(decryptedString, UserObject::class.java)
    scannedFullNameTextView.text = userObject.fullName
    scannedAgeTextView.text = userObject.age.toString()
  }
}

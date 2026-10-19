package com.example.lenslauncher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraIcon: ImageView = findViewById(R.id.cameraIcon)

        cameraIcon.setOnClickListener {
            openGoogleLens()
        }
    }

    private fun openGoogleLens() {
        val lensPackageName = "com.google.ar.lens"
        val intent = packageManager.getLaunchIntentForPackage(lensPackageName)

        if (intent != null) {
            startActivity(intent)
        } else {
            // যদি গুগল লেন্স সরাসরি না পাওয়া যায়, তবে প্লে-স্টোরে নিয়ে যাবে
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$lensPackageName")))
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$lensPackageName")))
            }
            Toast.makeText(this, "গুগল লেন্স পাওয়া যায়নি। প্লে-স্টোর খুলছে...", Toast.LENGTH_LONG).show()
        }
    }
}

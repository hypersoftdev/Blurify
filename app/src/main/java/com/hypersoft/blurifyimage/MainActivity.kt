package com.hypersoft.blurifyimage

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hypersoft.blurify.BlurryBackgroundView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val blurryBackgroundView = findViewById<BlurryBackgroundView>(R.id.blurryBackgroundView)

        // Ensure the background is updated after the view is laid out
        blurryBackgroundView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    blurryBackgroundView.updateBackgroundFromView()
                    blurryBackgroundView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    blurryBackgroundView.setBlurRadius(30f)
                }
            }
        )
    }



}
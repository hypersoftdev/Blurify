package com.hypersoft.blurify

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import java.lang.Exception



class BlurryBackgroundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_BLUR_RADIUS = 25f
    }

    private var backgroundImage: Bitmap? = null
    private var radius = 0f // Corner radius for rounded corners
    private var blurRadius = DEFAULT_BLUR_RADIUS // Blur radius (default is 25)

    private var strokeWidthGradientLine = 5f
    private val halfStrokeWidth = strokeWidthGradientLine / 2

    @ColorRes
    private var startColor: Int = R.color.secondary_text // Gradient start color
    private var endColor = Color.TRANSPARENT

    private var paint = Paint()
    private val overlayPaint = Paint()

    @ColorRes
    private var overlayColor: Int = R.color.gray_white // Overlay color

    private val overlayRect by lazy { RectF(0f, 0f, width.toFloat(), height.toFloat()) }

    private val gradient by lazy {
        LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(),
            intArrayOf(ContextCompat.getColor(context, startColor), endColor),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
    }

    private val roundedRect by lazy {
        RectF(
            halfStrokeWidth, halfStrokeWidth,
            width.toFloat() - halfStrokeWidth, height.toFloat() - halfStrokeWidth
        )
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.BlurryBackgroundView, defStyleAttr, 0).let {
            radius = it.getFloat(R.styleable.BlurryBackgroundView_cornerRadiusView, radius)
            blurRadius = it.getFloat(R.styleable.BlurryBackgroundView_blurRadius, blurRadius)
            overlayColor = it.getResourceId(R.styleable.BlurryBackgroundView_backgroundColorOverlay, overlayColor)
            startColor = it.getResourceId(R.styleable.BlurryBackgroundView_startColorGradient, startColor)
            strokeWidthGradientLine = it.getFloat(R.styleable.BlurryBackgroundView_strokeWidthGradient, strokeWidthGradientLine)
            it.recycle()
        }
        backgroundImage = (background as? BitmapDrawable)?.bitmap
    }

    fun setBlurRadius(radius: Float) {
        blurRadius = radius
        updateBackgroundFromView() // Reapply blur when radius changes
    }

    fun updateBackgroundFromView() {
        val location = IntArray(2)
        getLocationOnScreen(location)

        val viewX = location[0]
        val viewY = location[1]
        val width = width
        val height = height

        val rootView = rootView
        val screenshot = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(screenshot)
        rootView.draw(canvas)

        val croppedBitmap = Bitmap.createBitmap(screenshot, viewX, viewY, width, height)

        val bitmap = applyBlur(croppedBitmap)
        Canvas(bitmap).apply {
            drawColor(ContextCompat.getColor(context, overlayColor))
        }

        backgroundImage = createRoundedBitmap(bitmap)

        backgroundImage?.let {
            background = BitmapDrawable(resources, it)
        }
    }

    private fun applyBlur(sourceBitmap: Bitmap): Bitmap {
        return try {
            val rsBlur = RSBlur()
            rsBlur.blur(context, sourceBitmap, blurRadius.toInt()) // Use dynamic blur radius
        } catch (e: Exception) {
            val fastBlur = FastBlur()
            fastBlur.blur(sourceBitmap, blurRadius.toInt(), true) // Use dynamic blur radius
        }
    }

    private fun createRoundedBitmap(sourceBitmap: Bitmap?): Bitmap? {
        sourceBitmap ?: return null

        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), radius, radius, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)

        return output
    }
}

package ru.skillbranch.devintensive.ui.drawable

import android.graphics.*
import android.graphics.drawable.Drawable

class AvatarDrawable(private val text : String, colorBk :Int) : Drawable() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintText: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var width = 0
    private var height = 0
    init {
        mPaint.color = colorBk
        mPaintText.color = Color.WHITE
        mPaintText.textSize = 50F;
        mPaintText.style = Paint.Style.STROKE;
    }

    override fun draw(canvas: Canvas) {
        canvas?.drawCircle(width.toFloat()/2,height.toFloat()/2,(height.toFloat() - 2)/2f,mPaint)
        val bounds = Rect()
        mPaintText.getTextBounds(text, 0, text.length, bounds)
        val heightText = bounds.height()
        val widthText = bounds.width()
        canvas?.drawText(text,(width.toFloat() - widthText) / 2,(height.toFloat() + heightText)/2, mPaintText)
    }

    override fun setAlpha(p0: Int) {
        mPaint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(p0: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }
    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        width = bounds.width()
        height = bounds.height()
    }
}
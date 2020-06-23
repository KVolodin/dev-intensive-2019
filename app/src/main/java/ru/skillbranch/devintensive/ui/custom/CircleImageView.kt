package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import ru.skillbranch.devintensive.R


class CircleImageView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
) : androidx.appcompat.widget.AppCompatImageView (context, attrs, defStyleAttr){

    companion object {

        private const val DEFAULT_BORDER_WIDTH = 2f
        private val DEFAULT_BORDER_COLOR = Color.parseColor("#FFFFFF")
        private val paint = Paint()

    }

    private var borderWidth = DEFAULT_BORDER_WIDTH
    private var borderColor = DEFAULT_BORDER_COLOR
    init {
        if(attrs != null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = a.getDimension(
                R.styleable.CircleImageView_cv_borderWidth,
                DEFAULT_BORDER_WIDTH)
            borderColor = a.getColor(
                R.styleable.CircleImageView_cv_borderColor,
                DEFAULT_BORDER_COLOR)
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        paint.strokeWidth = borderWidth
        paint.color = borderColor
        paint.isSubpixelText = true
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        canvas?.drawCircle(width.toFloat()/2,height.toFloat()/2,(height.toFloat() - borderWidth)/2f, paint)
        super.onDraw(canvas)
    }

    fun getBorderWidth():Int = borderWidth.toInt()

    fun setBorderWidth(@Dimension dp:Int) {
        borderWidth = dp.toFloat()
    }

    fun getBorderColor():Int = borderColor

    fun setBorderColor(@ColorRes colorId: Int) {
        borderColor = colorId
    }
}
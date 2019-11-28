package com.quickbirdstudios.surveykit.backend.views.question_parts

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.quickbirdstudios.surveykit.R
import com.quickbirdstudios.surveykit.SurveyTheme
import com.quickbirdstudios.surveykit.backend.helpers.extensions.px
import com.quickbirdstudios.surveykit.backend.views.main_parts.StyleablePart

internal class AnimatedCheckmark @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes),
    StyleablePart {
    override fun style(surveyTheme: SurveyTheme) {}

    init {
        val layoutInflater = LayoutInflater.from(context)
        val root = layoutInflater.inflate(R.layout.lottie_checkmark_wrapper, this, false)

        val lottieView = root.findViewById<LottieAnimationView>(R.id.animation_view)
        val size = context.px(160f).toInt()
        addView(lottieView, LayoutParams(size, size))

        this.gravity = Gravity.CENTER
    }
}

package com.quickbirdstudios.surveykit.backend.views.questions

import android.content.Context
import androidx.annotation.StringRes
import com.quickbirdstudios.surveykit.AnswerFormat
import com.quickbirdstudios.surveykit.StepIdentifier
import com.quickbirdstudios.surveykit.TextChoice
import com.quickbirdstudios.surveykit.backend.views.question_parts.MultipleChoicePart
import com.quickbirdstudios.surveykit.backend.views.step.QuestionView
import com.quickbirdstudios.surveykit.result.QuestionResult
import com.quickbirdstudios.surveykit.result.question_results.MultipleChoiceQuestionResult

internal class MultipleChoiceQuestionView(
    context: Context,
    id: StepIdentifier,
    isOptional: Boolean,
    @StringRes title: Int?,
    @StringRes text: Int?,
    @StringRes nextButtonText: Int,
    private val answerFormat: AnswerFormat.MultipleChoiceAnswerFormat,
    private val preselected: List<TextChoice>?
) : QuestionView(context, id, isOptional, title, text, nextButtonText) {


    //region Members

    private lateinit var choicesContainer: MultipleChoicePart

    //endregion


    //region Overrides

    override fun createResults(): QuestionResult =
        MultipleChoiceQuestionResult(
            id = id,
            startDate = startDate,
            answer = choicesContainer.selected,
            stringIdentifier = choicesContainer.selected
                .map { it.value }
                .joinToString(",") { context.getString(it) }
        )

    override fun isValidInput(): Boolean = isOptional || choicesContainer.isOneSelected()

    override fun setupViews() {
        super.setupViews()

        choicesContainer = content.add(
            MultipleChoicePart(
                context
            )
        )
        choicesContainer.options = answerFormat.textChoices
        choicesContainer.onCheckedChangeListener = { _, _ -> footer.canContinue = isValidInput() }
        choicesContainer.selected = preselected ?: emptyList()
    }

    //endregion


}

package dora.widget

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.lang.IllegalArgumentException

class SpannableTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    var autoHighlightNumber: Boolean = false
    var highlightNumberColor: Int = Color.BLUE

    private fun buildSpannableText(text: String, vararg spans: Span) : CharSequence {
        return buildSpannableTextList(text, spans.asList())
    }

    private fun buildSpannableTextList(text: String, spans: List<Span>) : CharSequence {
        val ss = SpannableString(text)
        for (span in spans) {
            if (span.start < 0) {
                throw IllegalArgumentException("buildSpannableTextList span.start >= 0")
            }
            // 越界保护
            if (span.end > text.length - 1) {
                span.end = text.length
            }
            ss.setSpan(span.spanType, span.start, span.end + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        return ss
    }

    /**
     * 所有要设置spannable的建议使用它，而不是textview的setText。
     */
    fun setSpannableText(text: String, vararg spans: Span) {
        setText(buildSpannableText(text, *spans))
    }

    fun setSpannableTextList(text: String, spans: List<Span>) {
        setText(buildSpannableTextList(text, spans))
    }

    init {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SpannableTextView)
        autoHighlightNumber = a.getBoolean(R.styleable.SpannableTextView_dora_autoHighlightNumber, autoHighlightNumber)
        highlightNumberColor = a.getColor(R.styleable.SpannableTextView_dora_highlightNumberColor, highlightNumberColor)
        a.recycle()
        if (autoHighlightNumber && !TextUtils.isEmpty(text)) {
            val spans: List<Span> = parseNumberSpan(text)
            if (spans.isNotEmpty()) {
                setSpannableTextList(text.toString(), spans)
            }
        }
    }

    private fun parseNumberSpan(text: CharSequence): List<Span> {
        val spans = ArrayList<Span>()
        var startIndex = -1
        for ((index, char) in text.withIndex()) {
            if (char.isDigit()) {
                if (startIndex == -1) {
                    // 记录spannable text的开始位置
                    startIndex = index
                }
            } else {
                if (startIndex != -1) {
                    spans.add(Span(ForegroundColorSpan(highlightNumberColor), startIndex, index))
                    startIndex = -1
                }
            }
        }
        return spans
    }
}
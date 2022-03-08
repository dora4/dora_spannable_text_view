package dora.widget

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ForegroundColorSpan

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spannableTextView = findViewById<SpannableTextView>(R.id.spannableTextView)
        val content = "默认是设置第1个字符"
        spannableTextView.setSpannableText(content, Span(ForegroundColorSpan(Color.RED)))
    }
}
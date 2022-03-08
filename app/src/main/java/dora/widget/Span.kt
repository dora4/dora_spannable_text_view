package dora.widget

import android.text.style.CharacterStyle

data class Span(
    var spanType : CharacterStyle,
    // 包括start的字符
    var start: Int = 0,
    // 包括end的字符
    var end: Int = 0)
# DoraSpannableTextView

描述：一个便于设置高亮文本的文本控件

复杂度：★☆☆☆☆

分组：【Dora大控件组】

关系：暂无

技术要点：Spannable、自定义属性

### 照片

![avatar](https://github.com/dora4/dora_spannable_text_view/blob/main/art/dora_spannable_text_view.jpg)

### 软件包

https://github.com/dora4/dora_spannable_text_view/blob/main/art/dora_spannable_text_view.apk

### 用法

```kotlin
val spannableTextView = findViewById<SpannableTextView>(R.id.spannableTextView)
        val content = "默认是设置第1个字符"
        spannableTextView.setSpannableText(content, Span(ForegroundColorSpan(Color.RED)))
```

| 自定义属性 | 描述 |
| ---------- | ---- |
| dora_autoHighlightNumber     | 默认是否开启将所有数字高亮 |
| dora_highlightNumberColor     | 高亮数字的颜色 |

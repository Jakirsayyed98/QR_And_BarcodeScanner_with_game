package com.jhcreativedeveloper.BarcodeScanner.Games.Utilites

import android.content.Context
import android.widget.Toast
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.LoaderListener

import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Context.toast(mess: String?) {
    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
}

fun setErrorHandler(loadLis: LoaderListener?): CoroutineExceptionHandler {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        loadLis?.dismissLoader()
        if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            loadLis?.showMess("Internet error")
        } else {
            loadLis?.showMess(throwable.message)
        }
    }
    return exceptionHandler
}
/*

fun appLog(mess: String?) {
    Log.e("TAPFO", mess.toString())
}

fun getUniqueCode(): String {
    return (Math.random() * 10.0.pow(10.0)).toInt().toString()
}

fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun getCustomTab(context: Context?, title: String?): View? {
    return LayoutInflater.from(context).inflate(R.layout.layout_custom_tab, null)?.apply {
        findViewById<TextView>(R.id.titleTv).text = title
    }
}

fun Context.customToast(mess: String?, isError: Boolean) {
    try {
        Toast(this).apply {
            view =
                LayoutInflater.from(this@customToast).inflate(R.layout.toast_layout, null).apply {
                    findViewById<TextView>(R.id.toastTv).text = mess
                    backgroundTintList = ContextCompat.getColorStateList(this@customToast,
                        if (isError) R.color.red else R.color.green_light)
                }
            duration = Toast.LENGTH_SHORT
        }.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun withSuffixAmount(amount: String?): String? {
    try {
        amount?.let {
            val count = amount.toDouble()
            return String.format("₹%.2f", count)
            /* if (count < 1000) return String.format("₹%.2f", count)
             val exp = (ln(count) / ln(1000.0)).toInt()
             return String.format("₹%.1f %c",
                 count / 1000.0.pow(exp.toDouble()),
                 "kMGTPE"[exp - 1])*/
        }
        return "₹$amount"
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "₹$amount"
}

fun withSuffixAmount2(amount: String?): String? {
    try {
        amount?.let {
            val count = amount.toDouble()
            return String.format("%.2f", count)
            /* if (count < 1000) return String.format("₹%.2f", count)
             val exp = (ln(count) / ln(1000.0)).toInt()
             return String.format("₹%.1f %c",
                 count / 1000.0.pow(exp.toDouble()),
                 "kMGTPE"[exp - 1])*/
        }
        return "$amount"
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "$amount"
}

fun roundOff(amount: String?): String {
    amount?.let {
        try {
            val count = amount.toDouble()
            if (count < 1000) return String.format("₹%.2f", count)
            val exp = (ln(count) / ln(1000.0)).toInt()
            return String.format("₹%.1f %c",
                count / 1000.0.pow(exp.toDouble()),
                "kMGTPE"[exp - 1])
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return ""
}

fun getSpannableCashbackText(s: String, @ColorInt color: Int): SpannableString {
    var text = s
    try {
        text = URLDecoder.decode(text, "UTF-8")
    } catch (e: Exception) {
        e.printStackTrace()
    }
    val split = text.split(" ")
    if (split.size > 2)
        return SpannableString(text).apply {
            setSpan(StyleSpan(Typeface.BOLD),
                split[0].length + 1,
                text.length - split[2].length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            setSpan(ForegroundColorSpan(color), split[0].length + 1,
                text.length - split[2].length - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    return SpannableString(text)
}

fun getSpannableBold(
    s: String,
    startPos: Int,
    endPos: Int,
    color: Int?,
    fontSize: Float,
): SpannableStringBuilder {
    return SpannableStringBuilder(s).apply {
        setSpan(StyleSpan(Typeface.BOLD), startPos,
            if (endPos == -1) length else endPos,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        if (color != null)
            setSpan(ForegroundColorSpan(color),
                startPos,
                if (endPos == -1) length else endPos,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        if (fontSize != 0f)
            setSpan(RelativeSizeSpan(fontSize), startPos, if (endPos == -1) length else endPos, 0)
    }
}

fun setClickableSpannable(
    s: String,
    startPos: Int,
    endPos: Int,
    color: Int?,
    spanClickListener: SpanClickListener,
): SpannableStringBuilder {
    return SpannableStringBuilder(s).apply {
        setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                spanClickListener.onSpanClick()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                if (color != null) {
                    ds.color = color
                }
            }
        }, startPos,
            if (endPos == -1) length else endPos,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

fun parseDate(date: String?): String? {
    try {
        if (date.isNullOrEmpty().not())
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date)?.let {
                return SimpleDateFormat("yy-MM-dd HH:mm", Locale.ENGLISH).format(it)
            }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date
}

fun parseDate2(date: String?): String? {
    try {
        if (date.isNullOrEmpty().not())
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date)?.let {
                return SimpleDateFormat("dd MMM yyyy | hh:mm a", Locale.ENGLISH).format(it)
            }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date
}

fun getDate(date: String): Date? {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date)


}

*/
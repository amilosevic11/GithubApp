package hr.dice.amilosevic_.githubapp.helpers

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import hr.dice.amilosevic_.githubapp.R
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun formatInteger(number: Int): String {
    return "%,d".format(number).replace(".", ",")
}

fun formatThousands(number: Number): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = number.toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0")
            .format(numValue / 10.0.pow((base * 3).toDouble()))
            .replace(",", ".") + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
            .replace(",", ".")
    }
}

fun initPopupMenu(anchor: View, context: Context, listener: PopupMenu.OnMenuItemClickListener) {
    val popup = PopupMenu(context, anchor)
    val inflater = popup.menuInflater
    inflater.inflate(R.menu.actions, popup.menu)
    popup.setOnMenuItemClickListener(listener)
    popup.show()
}
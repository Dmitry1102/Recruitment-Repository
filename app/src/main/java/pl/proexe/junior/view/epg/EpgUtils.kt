package pl.proexe.junior.view.epg

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object EpgUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(timestamp: Long): LocalDate {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(timestamp))
        return LocalDate.parse(timestampAsDateString, format)
    }

    fun pressedButton(button: TextView) {
        button.isSelected = !button.isSelected
    }

}
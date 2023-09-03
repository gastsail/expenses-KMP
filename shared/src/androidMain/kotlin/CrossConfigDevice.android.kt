import androidx.appcompat.app.AppCompatDelegate
import data.CrossConfigDevice

class CrossConfigDevice : CrossConfigDevice {
    override fun isDarkModeEnabled(): Boolean {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }
}
import data.CrossConfigDevice
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIScreen
import platform.UIKit.UITraitCollection

class CrossConfigDevice : CrossConfigDevice {
    override fun isDarkModeEnabled(): Boolean {
        val osTheme: UITraitCollection = UIScreen.mainScreen.traitCollection
        return osTheme.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    }
}
import SwiftUI

@main
struct iOSApp: App {

    var isDarkMode: Bool {
        let osTheme = UITraitCollection.current.userInterfaceStyle
        return osTheme == .dark
    }

    let colorStatusBarDark = Color(UIColor(rgb: 0xFF1E1C1C))

	var body: some Scene {
		WindowGroup {
		    ZStack {
		        isDarkMode ? colorStatusBarDark.ignoresSafeArea(.all) : Color.white.ignoresSafeArea(.all)// status bar color
			    ContentView()
			}
			.preferredColorScheme(isDarkMode ? .dark : .light)
		}
	}
}

extension UIColor {
    convenience init(rgb: UInt32) {
        self.init(
            red: CGFloat((rgb & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgb & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgb & 0x0000FF) / 255.0,
            alpha: 1.0
        )
    }
}






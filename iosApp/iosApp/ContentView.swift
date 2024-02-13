import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


struct ColorStatusBarView: View {
    @Binding var isDarkMode: Bool

    var body: some View {
        let colorStatusBarDark = Color(UIColor(rgb: 0xFF1E1C1C))
        let statusBarColor = isDarkMode ? colorStatusBarDark : Color.white

        return statusBarColor
            .ignoresSafeArea(.all)
    }
}

struct ContentView: View {
    @Binding var isDarkMode: Bool
    
    var body: some View {
        ZStack {
            ColorStatusBarView(isDarkMode: $isDarkMode)
            ComposeView()
        }
        .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}




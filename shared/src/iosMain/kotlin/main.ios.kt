import androidx.compose.ui.window.ComposeUIViewController
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App(CrossConfigDevice()) }

fun initKoin() {
    startKoin {
        modules(appModule())
    }.koin
}
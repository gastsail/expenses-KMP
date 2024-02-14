import androidx.compose.ui.window.ComposeUIViewController
import com.expenseApp.db.AppDatabase
import data.local.DatabaseDriverFactory
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App(CrossConfigDevice()) }

fun initKoin() {
    startKoin {
        modules(appModule(AppDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}
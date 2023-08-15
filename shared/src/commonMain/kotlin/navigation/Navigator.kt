package navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import ui.AddExpensesScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    NavHost(navigator = navigator, initialRoute = "/home") {
        scene("/home") {
            ExpensesScreen()
        }
        scene("/addExpenses") {
            AddExpensesScreen()
        }
    }
}
package navigation

import BackgroundDarkMode
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.ExpenseRepoImpl
import data.SessionCache
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import presentation.ExpensesViewModel
import ui.AddExpensesScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    //val viewModel = koinInject<ExpensesViewModel>()  --> This works on Android, but cannot inject on iOS yet :(
    val viewModel = getViewModel(Unit, viewModelFactory { ExpensesViewModel(ExpenseRepoImpl()) })
    NavHost(
        Modifier.background(
            if (SessionCache.configDevice?.isDarkModeEnabled() == true) {
                BackgroundDarkMode
            } else {
                Color.White
            }
        ),
        navigator = navigator, initialRoute = "/home"
    ) {
        scene("/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }
        scene("/addExpenses/{id}?") {
            val idFromPath = it.path<Long>("id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithID(id) }

            AddExpensesScreen(expenseToEdit = isAddExpense, addExpenseAndNavigateBack = { expense ->
                if (isAddExpense == null) {
                    viewModel.addExpense(expense)
                } else {
                    viewModel.editExpense(expense)
                }
                navigator.popBackStack()
            })
        }
    }
}
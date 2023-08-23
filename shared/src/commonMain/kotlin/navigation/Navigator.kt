package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import data.ExpenseRepoImpl
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject
import presentation.ExpensesViewModel
import ui.AddExpensesScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    //val viewModel = koinInject<ExpensesViewModel>()  --> This works on Android, but cannot inject on iOS yet :(
    val viewModel = getViewModel(Unit, viewModelFactory { ExpensesViewModel(ExpenseRepoImpl()) })
    NavHost(navigator = navigator, initialRoute = "/home") {
        scene("/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState)
        }
        scene("/addExpenses") {
            AddExpensesScreen(addExpenseAndNavigateBack = { price, description, expenseCategory ->
                viewModel.addExpense(
                    Expense(
                        amount = price,
                        category = ExpenseCategory.valueOf(expenseCategory),
                        description = description
                    )
                )
                navigator.popBackStack()
            })
        }
    }
}
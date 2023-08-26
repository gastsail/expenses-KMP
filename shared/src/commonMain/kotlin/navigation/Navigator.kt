package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import data.ExpenseRepoImpl
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
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
            ExpensesScreen(uiState) { expense ->
                viewModel.expenseToEdit = expense
                navigator.navigate("/addExpenses")
            }
        }
        scene("/addExpenses") {
            val isAddExpense = viewModel.expenseToEdit == null
            AddExpensesScreen(expenseToEdit = viewModel.expenseToEdit,addExpenseAndNavigateBack = { expense ->
                if (isAddExpense){
                    viewModel.addExpense(expense)
                }else{
                    viewModel.editExpense(expense)
                }
                navigator.popBackStack()
            })
        }
    }
}
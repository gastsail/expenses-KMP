package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseRepoImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.core.parameter.parametersOf
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors = getColorsTheme()
    val viewModel = koinViewModel(ExpensesViewModel::class) { parametersOf() }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator, initialRoute = "/home"
    ) {
        scene("/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState = uiState, onDeleteExpense = {
                viewModel.deleteExpense(it.id)
            }, onExpenseClick = { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            })
        }
        scene("/addExpenses/{id}?") {
            val idFromPath = it.path<Long>("id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithID(id) }

            ExpensesDetailScreen(
                expenseToEdit = isAddExpense,
                addExpenseAndNavigateBack = { expense ->
                    if (isAddExpense == null) {
                        viewModel.addExpense(expense)
                    } else {
                        viewModel.editExpense(expense)
                    }
                    navigator.popBackStack()
                },
                categoryList = viewModel.getCategories()
            )
        }
    }
}
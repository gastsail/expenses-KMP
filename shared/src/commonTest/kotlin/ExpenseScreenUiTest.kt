import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.waitUntilExactlyOneExists
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.ExpensesUiState
import ui.ExpensesDetailScreen
import ui.ExpensesScreen
import utils.EXPENSE_DETAIL_TEST_TAG
import utils.EXPENSE_SCREEN_ERROR_TEST_TAG
import utils.EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG
import utils.EXPENSE_SCREEN_LOADING_TEST_TAG
import utils.EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG
import utils.EXPENSE_SCREEN_SUCCESS_TEST_TAG
import utils.EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG
import kotlin.test.Test

class ExpenseScreenUiTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenLoadingTest() = runComposeUiTest {
        val loadingUiState = ExpensesUiState.Loading
        setContent {
            ExpensesScreen(uiState = loadingUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_LOADING_TEST_TAG).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenErrorTest() = runComposeUiTest {
        val errorText = "Error de carga"
        val assertErrorText = "Error: $errorText"
        val errorUiState = ExpensesUiState.Error(message = errorText)
        setContent {
            ExpensesScreen(uiState = errorUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_ERROR_TEST_TAG).assertExists()
        onNodeWithTag(EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG).assertTextEquals(assertErrorText)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenSuccessTest() = runComposeUiTest {
        val successUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 10.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ), Expense(
                    id = 2,
                    amount = 5.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cafe"
                )
            ), total = 15.0
        )
        setContent {
            ExpensesScreen(uiState = successUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenSuccessTotalTest() = runComposeUiTest {
        val successUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 10.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ), Expense(
                    id = 2,
                    amount = 5.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cafe"
                )
            ), total = 15.0
        )
        setContent {
            ExpensesScreen(uiState = successUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG).assertTextEquals("$15.0")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenSuccessClickItemTest() = runComposeUiTest {
        val successUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 10.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ), Expense(
                    id = 2,
                    amount = 5.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cafe"
                )
            ), total = 15.0
        )
        setContent {
            PreComposeApp {
                val navigator = rememberNavigator()
                NavHost(
                    navigator = navigator,
                    initialRoute = "/home"
                ) {
                    scene(route = "/home") {
                        ExpensesScreen(uiState = successUiState, onExpenseClick = { expense ->
                            navigator.navigate("/addExpenses/${expense.id}")
                        }, onDeleteExpense = { _ -> })
                    }

                    scene(route = "/addExpenses/{id}?") { backStackEntry ->
                        ExpensesDetailScreen(
                            expenseToEdit = successUiState.expenses[0],
                            categoryList = emptyList()
                        ) { _ -> }
                    }
                }
            }
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG.plus("_1")).performClick()
        waitUntilExactlyOneExists(hasTestTag(EXPENSE_DETAIL_TEST_TAG))
        onNodeWithTag(EXPENSE_DETAIL_TEST_TAG).assertExists()
    }
}
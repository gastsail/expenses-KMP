package presentation

import data.fakeExpenseList
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpensesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllExpenses()
    }

    private fun getAllExpenses() {
        //TODO REPLACE WITH REPO CALL
        viewModelScope.launch {
            _uiState.update {
                it.copy(expenses = fakeExpenseList, total = fakeExpenseList.sumOf { it.amount })
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            //TODO REPLACE WITH REPO CALLS
            fakeExpenseList.add(expense)
            _uiState.update {
                it.copy(expenses = fakeExpenseList, total = fakeExpenseList.sumOf { it.amount })
            }
        }
    }
}
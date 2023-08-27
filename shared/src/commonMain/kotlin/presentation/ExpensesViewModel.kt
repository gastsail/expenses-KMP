package presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    private val allExpenses = repo.getAllExpenses()

    init {
        getAllExpenses()
    }

    private fun getAllExpenses() {
        viewModelScope.launch {
            _uiState.update {state ->
                state.copy(expenses = allExpenses, total = allExpenses.sumOf { it.amount })
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.addExpense(expense)
            _uiState.update {state ->
                state.copy(expenses = allExpenses, total = allExpenses.sumOf { it.amount })
            }
        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            repo.editExpense(expense)
            _uiState.update {state ->
                state.copy(expenses = allExpenses, total = allExpenses.sumOf { it.amount })
            }
        }
    }

    fun getExpenseWithID(id:Long):Expense?{
        return allExpenses.first { it.id == id }
    }
}
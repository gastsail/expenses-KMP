package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {
    suspend fun getAllExpenses(): List<Expense>
    suspend fun addExpense(expense: Expense)
    suspend fun editExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense): List<Expense>
    fun getCategories(): List<ExpenseCategory>
}
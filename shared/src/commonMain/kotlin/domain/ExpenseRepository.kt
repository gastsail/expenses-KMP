package domain

import model.Expense

interface ExpenseRepository {
    fun getAllExpenses(): List<Expense>
    fun addExpense(expense: Expense)
}
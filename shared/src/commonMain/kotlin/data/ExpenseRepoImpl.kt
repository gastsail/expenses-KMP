package data

import domain.ExpenseRepository
import model.Expense

class ExpenseRepoImpl: ExpenseRepository {

    private val expenses = fakeExpenseList.toMutableList()

    override fun getAllExpenses(): List<Expense> {
        return expenses.toList()
    }

    override fun addExpense(expense: Expense) {
        expenses.add(expense)
    }
}
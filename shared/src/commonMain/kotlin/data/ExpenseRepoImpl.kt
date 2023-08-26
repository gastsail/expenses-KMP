package data

import domain.ExpenseRepository
import model.Expense

class ExpenseRepoImpl: ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return ExpenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        ExpenseManager.addNewExpense(expense)
    }
}
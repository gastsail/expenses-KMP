package data

import domain.ExpenseRepository
import model.Expense

class ExpenseRepoImpl: ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        fakeExpenseList.add(expense)
    }
}
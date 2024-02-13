package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl(private val expenseManager: ExpenseManager) : ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("This is work for you ;)")
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }
}
package data

import com.expenseApp.db.AppDatabase
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl(
    private val expenseManager: ExpenseManager,
    private val appDatabase: AppDatabase
) : ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries

    override fun populateDb() {
        queries.transaction {
            expenseManager.fakeExpenseList.forEach {
                queries.insert(
                    amount = it.amount,
                    categoryName = it.category.name,
                    description = it.description
                )
            }
        }
    }

    override fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
    }

    override fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("This is work for you ;)")
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }
}
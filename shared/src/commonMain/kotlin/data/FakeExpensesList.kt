package data

import model.Expense
import model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.GROCERIES,
            description = "Weekly buy"
        ),
        Expense(
            id = currentId++,
            amount = 10.2,
            category = ExpenseCategory.SNACKS,
            description = "Homies"
        ),
        Expense(
            id = currentId++,
            amount = 21000.0,
            category = ExpenseCategory.CAR,
            description = "Audi A1 "
        ),
        Expense(
            id = currentId++,
            amount = 15.0,
            category = ExpenseCategory.COFFEE,
            description = "Beans and cream"
        ),
        Expense(
            id = currentId++,
            amount = 25.0,
            category = ExpenseCategory.PARTY,
            description = "Weekend party"
        ),
        Expense(
            id = currentId++,
            amount = 120.0,
            category = ExpenseCategory.HOUSE,
            description = "Expenses"
        ),
        Expense(
            id = currentId++,
            amount = 25.0,
            category = ExpenseCategory.OTHER,
            description = "CLEANING"
        )
    )

    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER
        )
    }
}

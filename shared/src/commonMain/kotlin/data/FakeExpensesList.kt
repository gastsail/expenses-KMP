package data

import model.Expense
import model.ExpenseCategory

val fakeExpenseList = mutableListOf(
    Expense(
        amount = 70.0,
        category = ExpenseCategory.GROCERIES,
        description = "Weekly buy"
    ),
    Expense(
        amount = 10.0,
        category = ExpenseCategory.SNACKS,
        description = "Hommies"
    ),
    Expense(
        amount = 21000.0,
        category = ExpenseCategory.CAR,
        description = "Audi A1 "
    ),
    Expense(
        amount = 15.0,
        category = ExpenseCategory.COFFEE,
        description = "Beans and cream"
    ),
    Expense(
        amount = 25.0,
        category = ExpenseCategory.PARTY,
        description = "Weekend party"
    ),
)
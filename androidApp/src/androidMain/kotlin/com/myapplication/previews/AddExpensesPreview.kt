package com.myapplication.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import model.Expense
import model.ExpenseCategory
import ui.ExpensesDetailScreen

@Preview(showBackground = true)
@Composable
fun ExpensesDetailsPreview() {
    ExpensesDetailScreen(addExpenseAndNavigateBack = {Expense(amount = 10.0, description = "test", category = ExpenseCategory.CAR)})
}
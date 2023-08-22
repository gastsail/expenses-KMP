package com.myapplication.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ui.AddExpensesScreen

@Preview(showBackground = true)
@Composable
fun ExpensesDetailsPreview() {
    AddExpensesScreen(addExpenseAndNavigateBack = {_,_,_ ->})
}
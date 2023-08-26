package com.myapplication.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.ExpenseManager
import presentation.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader


@Preview
@Composable
fun ExpensesTotalHeaderPreview() {
    ExpensesTotalHeader(total = 100.0)
}

@Preview(showBackground = true)
@Composable
fun AllExpensesPreview() {
    AllExpensesHeader()
}

@Preview
@Composable
fun ExpensesItemPreview() {
    ExpensesItem(ExpenseManager.fakeExpenseList.first(), {})
}

@Preview
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(uiState = ExpensesUiState(expenses = ExpenseManager.fakeExpenseList, total = 51234.0), {})
}


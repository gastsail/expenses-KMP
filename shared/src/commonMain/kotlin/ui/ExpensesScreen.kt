package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExpensesScreen() {
    ExpensesItem()
}

@Composable
fun ExpensesItem() {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text("LALA")
            Text("LOLO")
        }

        Column {
            Text("$123124")
            Text("23 aug 2023")
        }
    }
}
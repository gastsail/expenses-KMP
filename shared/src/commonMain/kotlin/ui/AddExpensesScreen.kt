package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddExpensesScreen() {
    var price by remember { mutableStateOf(0.0) }
    var description by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 16.dp)) {
        ExpenseAmount(onPriceChange = {
            price = it
        })
        Spacer(modifier = Modifier.height(30.dp))
        ExpenseTypeSelector()
        Spacer(modifier = Modifier.height(30.dp))
        ExpenseDescription(onDescriptionChange = {
            description = it
        })
    }
}

@Composable
private fun ExpenseAmount(onPriceChange: (Double) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Amount",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "$", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
            TextField(
                modifier = Modifier.weight(1f),
                onValueChange = { newText ->
                    val numericText = newText.filter { it.isDigit() || it == '.' }
                    if (numericText.isNotBlank()) {
                        onPriceChange(numericText.toDouble())
                    } else {
                        onPriceChange(0.0)
                    }
                    text = newText
                },
                value = text,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
            )
            Text(
                text = "USD",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray
            )
        }
        Divider(color = Color.Black, thickness = 2.dp)
    }
}

@Composable
private fun ExpenseTypeSelector() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Expenses made for",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(
                text = "Tea & Snacks",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        IconButton(modifier = Modifier.clip(RoundedCornerShape(35)).background(Color.Gray.copy(alpha = .2f)), onClick = {
            //TODO
        }) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

@Composable
private fun ExpenseDescription(onDescriptionChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Description",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                if (newText.length <= 200) {
                    text = newText
                    onDescriptionChange(newText)
                }
            },
            value = text,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        )
    }
}
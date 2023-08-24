package ui

import Purple
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.fakeExpenseList
import kotlinx.coroutines.launch
import model.ExpenseCategory

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddExpensesScreen(addExpenseAndNavigateBack: (price: Double, description: String, expenseCategory: String) -> Unit) {

    var price by remember { mutableStateOf(0.0) }
    var description by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }
    var categorySelected by remember { mutableStateOf("Select a category") }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if(sheetState.targetValue == ModalBottomSheetValue.Expanded){
            keyboardController?.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            CategoryBottomSheetContent(fakeExpenseList.map { it.category }) {
                expenseCategory = it.name
                categorySelected = it.name
                scope.launch {
                    sheetState.hide()
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 16.dp)) {
            ExpenseAmount(onPriceChange = {
                price = it
            }, keyboardController = keyboardController)
            Spacer(modifier = Modifier.height(30.dp))
            ExpenseTypeSelector(categorySelected = categorySelected, openBottomSheet = {
                scope.launch {
                    sheetState.show()
                }
            })
            Spacer(modifier = Modifier.height(30.dp))
            ExpenseDescription(onDescriptionChange = {
                description = it
            }, keyboardController = keyboardController)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(45)),
                onClick = {
                    addExpenseAndNavigateBack(price, description, expenseCategory)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple,
                    contentColor = Color.White
                ),
                enabled = price != 0.0 && description.isNotBlank() && expenseCategory.isNotBlank()
            ) {
                Text("Add Expense")
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ExpenseAmount(onPriceChange: (Double) -> Unit, keyboardController: SoftwareKeyboardController?) {
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
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
private fun ExpenseTypeSelector(
    categorySelected: String,
    openBottomSheet: () -> Unit
) {
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
                text = categorySelected,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        IconButton(
            modifier = Modifier.clip(RoundedCornerShape(35))
                .background(Color.Gray.copy(alpha = .2f)),
            onClick = {
                openBottomSheet.invoke()
            }) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

@Composable
private fun CategoryBottomSheetContent(
    categories: List<ExpenseCategory>,
    onCategorySelected: (ExpenseCategory) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(categories) { category ->
            CategoryItem(category, onCategorySelected)
        }
    }
}

@Composable
private fun CategoryItem(category: ExpenseCategory, onCategorySelected: (ExpenseCategory) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCategorySelected(category) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = category.icon,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = category.name)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ExpenseDescription(onDescriptionChange: (String) -> Unit, keyboardController: SoftwareKeyboardController?) {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
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
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        Divider(color = Color.Black, thickness = 2.dp)
    }
}
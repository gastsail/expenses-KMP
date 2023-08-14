import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App() {
    AppTheme {
        val viewModel = getViewModel(Unit, viewModelFactory { ExpensesViewModel() })
        val uiState by viewModel.uiState.collectAsState()
        Navigator(ExpensesScreen) { navigator ->
            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                TopAppBar(title = { Text("Expenses") }, actions = {
                    IconButton(onClick = {
                        navigator.push(AddExpensesScreen)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                })
            }) {
                //ONLY USE PARTIALLY
               navigator.lastItem.Content()
            }
        }
    }
}

object ExpensesScreen: Screen {
    @Composable
    override fun Content() {
        ExpensesItem()
    }
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

object AddExpensesScreen : Screen {

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Hello from add expenses screen"
            )
        }
    }
}

expect fun getPlatformName(): String
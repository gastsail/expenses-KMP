import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import presentation.ExpensesViewModel

@Composable
fun App() {
    AppTheme {
        val viewModel = getViewModel(Unit, viewModelFactory { ExpensesViewModel() })
        val uiState by viewModel.uiState.collectAsState()
        val navigator = rememberNavigator()
        val isOnAddExpenses =
            navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses")
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = { Text("Expenses") }, actions = {
                if (!isOnAddExpenses) {
                    IconButton(onClick = {
                        navigator.navigate("/addExpenses")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }

            }, navigationIcon = {
                if (isOnAddExpenses) {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            })
        }) {
            Navigation(navigator)
        }
    }
}
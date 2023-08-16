import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            TopAppBar(
                title = { Text(if (isOnAddExpenses) "Add Amount" else "Dashboard") },
                navigationIcon = {
                    if (isOnAddExpenses) {
                        IconButton(onClick = {
                            navigator.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                })
        }) {
            Box(modifier = Modifier.fillMaxSize()) {
                Navigation(navigator)
                if (!isOnAddExpenses) {
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp), onClick = {
                            navigator.navigate("/addExpenses")
                        }, shape = RoundedCornerShape(50), backgroundColor = Color.Black,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
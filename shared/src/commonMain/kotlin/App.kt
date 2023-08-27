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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation

@Composable
fun App() {
    AppTheme {
        val navigator = rememberNavigator()
        val titleTopBar = getTitleTopAppBar(navigator)
        val isEditOrAddExpenses = titleTopBar != TitleTopBarTypes.DASHBOARD.value
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = { Text(titleTopBar, fontSize = 25.sp) },
                navigationIcon = {
                    if (isEditOrAddExpenses) {
                        IconButton(
                            onClick = {
                            navigator.popBackStack()
                        }) {
                            Icon(
                                modifier = Modifier.padding(start = 16.dp),
                                imageVector = Icons.Default.ArrowBack,
                                tint = Color.Black,
                                contentDescription = null
                            )
                        }
                    } else {
                        Icon(
                            modifier = Modifier.padding(start = 16.dp),
                            imageVector = Icons.Default.Apps,
                            tint = Color.Black,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    if (!isEditOrAddExpenses) {
                        /* TODO: THIS WORKS FOR ANDROID BUT NOT FOR iOS
                        Image(
                            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(25)).padding(end = 16.dp),
                            painter = painterResource("profile.png"),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                            contentDescription = null
                        )
                         */
                    }
                },
                backgroundColor = Color.White)
        }) {
            Box(modifier = Modifier.fillMaxSize()) {
                Navigation(navigator)
                if (!isEditOrAddExpenses) {
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp), onClick = {
                            navigator.navigate("/addExpenses")
                        }, shape = RoundedCornerShape(50), backgroundColor = Color.Black,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun getTitleTopAppBar(navigator:Navigator):String{
    var titleTopBar = TitleTopBarTypes.DASHBOARD

    val isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses){
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpense = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpense?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }

    return  titleTopBar.value
}
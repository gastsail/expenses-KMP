import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.SessionCache

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

val Purple = Color(0xFF6A66FF)
val ColorExpenseItem = if (SessionCache.configDevice?.isDarkModeEnabled() == true) Color(0xFF090808) else Color(0xFFF1F1F1)
val BackgroundColor = if (SessionCache.configDevice?.isDarkModeEnabled() == true) Color(0xFF1E1C1C) else Color.White
val TextColor = if (SessionCache.configDevice?.isDarkModeEnabled() == true) Color.White else Color.Black
val AddIconColor = if (SessionCache.configDevice?.isDarkModeEnabled() == true) Purple else Color.Black

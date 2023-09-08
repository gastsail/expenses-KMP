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

@Composable
fun getColorsTheme(): DarkModeColors {
    val isDarkMode = SessionCache.isDarkMode()

    val Purple = Color(0xFF6A66FF)
    val ColorExpenseItem = if (isDarkMode) Color(0xFF090808) else Color(0xFFF1F1F1)
    val BackgroundColor = if (isDarkMode) Color(0xFF1E1C1C) else Color.White
    val TextColor = if (isDarkMode) Color.White else Color.Black
    val AddIconColor = if (isDarkMode) Purple else Color.Black
    val colorArrowRound = if (isDarkMode) Purple else Color.Gray.copy(alpha = .2f)

    return DarkModeColors(
        Purple = Purple,
        ColorExpenseItem = ColorExpenseItem,
        BackgroundColor = BackgroundColor,
        TextColor = TextColor,
        AddIconColor = AddIconColor,
        colorArrowRound = colorArrowRound
    )
}

data class DarkModeColors(
    val Purple: Color,
    val ColorExpenseItem: Color,
    val BackgroundColor: Color,
    val TextColor: Color,
    val AddIconColor: Color,
    val colorArrowRound: Color
)

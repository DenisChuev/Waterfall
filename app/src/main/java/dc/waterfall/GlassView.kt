package dc.waterfall

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun GlassView(
    modifier: Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    foregroundColor: Color,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 10,
) {
    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }


    val percentage =
        if (animatedIndicatorValue == 0f) 0
        else
            (animatedIndicatorValue / maxIndicatorValue) * 100


    val heightPercentage by animateFloatAsState(
        targetValue = percentage.toFloat(),
        animationSpec = tween(1000), label = ""
    )

    Column(
        modifier = modifier
            .drawBehind {
                backgroundIndicator(backgroundColor)
                foregroundIndicator(foregroundColor, heightPercentage)
            }
    )
    {

    }
}

private fun DrawScope.backgroundIndicator(backgroundColor: Color) {
    drawRoundRect(
        color = backgroundColor,
        cornerRadius = CornerRadius(x = 16.dp.toPx(), y = 16.dp.toPx())
    )
}

private fun DrawScope.foregroundIndicator(foregroundColor: Color, heightPercentage: Float) {
    drawRoundRect(
        color = foregroundColor,
        cornerRadius = CornerRadius(x = 16.dp.toPx(), y = 16.dp.toPx()),
        topLeft = Offset(
            x = 0f,
            y = size.height - (size.height / 100f * heightPercentage)
        )
    )
}


package dc.waterfall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dc.waterfall.ui.theme.WaterfallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterfallTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        DisplayGlass()
                    }
                }
            }
        }
    }
}


@Composable
fun DisplayGlass() {
    var glassCounter by rememberSaveable { mutableStateOf(0) }

    Row(modifier = Modifier.fillMaxSize()) {

        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp, 16.dp, 0.dp, 8.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, "#d4e3fc".toColor())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background("#f0f5fc".toColor()),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "$glassCounter",
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = { glassCounter++ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_glass),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentDescription = "Добавить",
                    tint = "#346fcf".toColor()
                )
            }
        }
    }
}

@Composable
fun ColumnRowTest(
    content: @Composable () -> Unit
) = Layout(content) { measurables, constraints ->
    // 1. The measuring phase.
    val placeables = measurables.map { measurable ->
        measurable.measure(constraints)
    }

    // 2. The sizing phase.
    layout(constraints.maxWidth, constraints.maxHeight) {
        // 3. The placement phase.
        var yPosition = 0
        var xPosition = 0

        var first = true

        placeables.forEach { placeable ->
            if (placeable.height + yPosition < constraints.maxHeight) {
                yPosition += if (first) {
                    first = false;
                    0
                } else placeable.height
            } else {
                yPosition += (placeable.height)
                xPosition = 0
            }
            placeable.place(xPosition, yPosition)
        }
    }
}

@Preview
@Composable
fun ReverseFlowRowPreview() {
    ColumnRowTest() {
        Text("First", fontSize = 20.sp, style = TextStyle(background = Color.Red))
        Text("Second", fontSize = 20.sp, style = TextStyle(background = Color.LightGray))
        Text("Third", fontSize = 20.sp, style = TextStyle(background = Color.Blue))
        Text("Fourth", fontSize = 20.sp, style = TextStyle(background = Color.Green))
        Text("Fifth", fontSize = 20.sp, style = TextStyle(background = Color.Gray))
        Text("Sixth", fontSize = 20.sp, style = TextStyle(background = Color.Yellow))
        Text("Seventh", fontSize = 20.sp, style = TextStyle(background = Color.Cyan))
        Text("Eight", fontSize = 20.sp, style = TextStyle(background = Color.Magenta))
        Text("Ninth", fontSize = 20.sp, style = TextStyle(background = Color.DarkGray))
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

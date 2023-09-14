package dc.waterfall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
                .padding(16.dp, 16.dp, 0.dp, 0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
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
                    contentDescription = "Добавить"
                )
            }
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

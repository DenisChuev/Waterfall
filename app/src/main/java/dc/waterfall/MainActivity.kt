package dc.waterfall

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dc.waterfall.ui.theme.WaterfallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterfallTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    DisplayTopBar()
                    DisplayGlass()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTopBar() {
    var displayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxHeight(1f),
        title = { Text("GFG | Menu Options", color = Color.White) },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Favorite, "")
            }

            IconButton(onClick = { displayMenu = !displayMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = displayMenu,
                onDismissRequest = { displayMenu = false }
            ) {
                DropdownMenuItem(text = { "Logout" }, onClick = { })

            }
        }
    )
}

@Composable
fun DisplayGlass() {
    var glassCounter by rememberSaveable { mutableStateOf(0) }

    Row {
        GlassView(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxHeight(1f)
                .padding(16.dp, 16.dp, 0.dp, 8.dp),
            backgroundColor = ("#f0f5fc".toColor()),
            foregroundColor = ("#145f99".toColor()),
            indicatorValue = glassCounter * 275,
            maxIndicatorValue = 3200
        )

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(onClick = { glassCounter++ }, modifier = Modifier.size(48.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_glass),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentDescription = "Добавить",
                    tint = "#346fcf".toColor()
                )
            }

//            IconButton(
//                modifier = Modifier
//                    .size(48.dp),
//                onClick = { glassCounter++ },
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_glass),
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(8.dp),
//                    contentDescription = "Добавить",
//                    tint = "#346fcf".toColor()
//                )
//            }
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

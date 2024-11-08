package my.app.quizsomativo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") { QuizzMenuUI(navController) }
                composable("gameplay/{playerName}") { backStackEntry ->
                    val playerName = backStackEntry.arguments?.getString("playerName") ?: ""
                    QuizzGameplayUI(playerName)
                }
                composable("ranking") { QuizzRankingUI() }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun TextFieldText(value: String, onValueChange: (String) -> Unit, label: String? = null) {
    TextField(value, onValueChange,label = {label?.let{  Text(it) }})
}

@Composable
fun ButtonPlay(playerName: String, onPlayClicked: () -> Unit) {


    Button(
        modifier = Modifier
            .height(50.dp),
        onClick = {
            onPlayClicked()
        }
    ) {
        TextComposable(text = "PLAY")
    }
}

@Composable
fun ButtonRanking(onResultChange: (String) -> Unit) {


    Button(
        modifier = Modifier
            .height(50.dp),
        onClick = {
        }
    ) {
        TextComposable(text = "RANKING")
    }
}

@Composable
fun TextComposable(text: String, modifier: Modifier = Modifier) {
    Text(text = "$text",modifier = modifier)

}

@Composable
fun QuizzMenuUI(navController: NavController) {
    QuizSomativoTheme {

        var playerName by remember { mutableStateOf("") }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
            ) {

            Column() {
                TextFieldText(value = playerName, onValueChange = {newText -> playerName = newText})

                ButtonPlay(playerName = playerName) {
                    navController.navigate("gameplay/$playerName")
                }
            }

            Column() {
                ButtonRanking {

                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizzMenuPreview() {

    val navController = rememberNavController()

    QuizSomativoTheme {
        QuizzMenuUI(navController = navController)
    }
}
package my.app.quizsomativo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizSomativoTheme {
                QuizzMenuUI()
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
fun ButtonPlay(onResultChange: (String) -> Unit) {


    Button(
        modifier = Modifier
            .height(50.dp),
        onClick = {
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
fun QuizzMenuUI() {
    QuizSomativoTheme {
        var textName by remember { mutableStateOf("") }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
            ) {

            Column() {
                TextFieldText(value = textName, onValueChange = {newText -> textName = newText})

                ButtonPlay() {

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
    QuizSomativoTheme {
        QuizzMenuUI()
    }
}
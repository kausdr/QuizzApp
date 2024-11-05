package my.app.quizsomativo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class Quizz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizSomativoTheme {
                    QuizzGameplayUI()
            }
        }
    }
}

@Composable
fun TextQuestion(text: String, modifier: Modifier = Modifier, style: androidx.compose.ui.text.TextStyle) {
    Text(text = "$text",modifier = modifier, style = style)

}

@Composable
fun listQuetion() {
    val questions = listOf(Pair("Olivia Rodrigo",R.drawable.or), Pair("Bruno Mars",R.drawable.bruno_mars), Pair("Alcione",R.drawable.alcione))
}


@Composable
fun TextAnswer(text: String, modifier: Modifier = Modifier, style: androidx.compose.ui.text.TextStyle) {
    Text(text = "$text",modifier = modifier, style = style)

}

@Composable
fun RadioAnswer(option: String, selectedOption: String?, onOptionSelected: (String) -> Unit) {
    RadioButton(
        selected = (selectedOption == option),
        onClick = {
            onOptionSelected(option)
        }
    )
}


@Composable
fun QuizzGameplayUI() {
    var textQuestion by remember { mutableStateOf("Quem foi michael jackson, mais conhecido como Lord?") }
    val options1 = listOf("Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4")
    val questions = listOf(Pair("Olivia Rodrigo",R.drawable.or), Pair("Bruno Mars",R.drawable.bruno_mars), Pair("Alcione",R.drawable.alcione))
    var selectedOption by remember { mutableStateOf<String?>(null) }

        QuizSomativoTheme {

            Column (modifier = Modifier
            ) {
                TextQuestion(
                    text = textQuestion, style = androidx.compose.ui.text.TextStyle(
                    )
                )

                questions.forEach { (question, img) ->

                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        TextQuestion(text = question, style = androidx.compose.ui.text.TextStyle())
                        Image(
                            painter = painterResource(id = img),
                            contentDescription = ""
                        )
                    }
                }

                options1.forEach { option ->

                    Row(modifier = Modifier
                        .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioAnswer(
                            option = option,
                            selectedOption = selectedOption,
                            onOptionSelected = { selectedOption = it }
                        )
                        TextAnswer(text = option, style = androidx.compose.ui.text.TextStyle())
                    }
                }
            }



        }
    }


@Preview(showBackground = true)
@Composable
fun QuizzGameplayPreview() {
    QuizSomativoTheme {
        QuizzGameplayUI()
    }
}
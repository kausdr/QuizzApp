package my.app.quizsomativo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class Quizz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val playerName = intent.getStringExtra("playerName") ?: "Jogador"

        setContent {
            QuizSomativoTheme {
                QuizzGameplayUI(playerName = playerName)
            }
        }
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)

data class Artist(
    val name: String,
    val img: Int,
    val questions: List<Question>
)

val artistData = listOf(
    Artist(
        name = "Olivia Rodrigo",
        img = R.drawable.or,
        questions = listOf(
            Question(
                text = "Qual o maior sucesso de Olivia Rodrigo?",
                options = listOf("Drivers License", "Good 4 U", "Deja Vu", "Brutal"),
                correctAnswer = "Drivers License"
            ),
            Question(
                text = "Qual o álbum mais recente de Olivia Rodrigo?",
                options = listOf("Sour", "Red", "Sweetener", "Future Nostalgia"),
                correctAnswer = "Sour"
            )
        )
    ),
    Artist(
        name = "Bruno Mars",
        img = R.drawable.bruno_mars,
        questions = listOf(
            Question(
                text = "Qual o álbum mais famoso de Bruno Mars?",
                options = listOf(
                    "Unorthodox Jukebox",
                    "24K Magic",
                    "Doo-Wops & Hooligans",
                    "An Evening with Silk Sonic"
                ),
                correctAnswer = "Doo-Wops & Hooligans"
            ),
            Question(
                text = "Com quem Bruno Mars colaborou em 'Silk Sonic'?",
                options = listOf("Anderson .Paak", "The Weeknd", "Olivia Rodrigo", "Beyoncé"),
                correctAnswer = "Anderson .Paak"
            )
        )
    ),
    Artist(
        name = "Alcione",
        img = R.drawable.alcione,
        questions = listOf(
            Question(
                text = "Qual o gênero musical da Alcione?",
                options = listOf("Samba", "Pop", "Rock", "Jazz"),
                correctAnswer = "Samba"
            ),
            Question(
                text = "Alcione é conhecida por tocar qual instrumento?",
                options = listOf("Trompete", "Violão", "Cavaquinho", "Pandeiro"),
                correctAnswer = "Trompete"
            )
        )
    )
)

@Composable
fun TextQuestion(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle
) {
    Text(text = "$text", modifier = modifier, style = style)
}

@Composable
fun TextAnswer(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle
) {
    Text(text = "$text", modifier = modifier, style = style)
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
fun QuizzGameplayUI(playerName: String) {
    val viewModel = QuizzViewModel.getInstance()
    var score = viewModel.getScore()

    // Usar remember para armazenar o artista e a pergunta de forma que não sejam recriados
    var artist = remember { artistData.random() }
    var question = remember { artist.questions.random() }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isCorrectAnswer by remember { mutableStateOf<Boolean?>(null) }

    var numberQuestions by remember { mutableStateOf(0) }
    var hasAnswered by remember { mutableStateOf(false) }

    QuizSomativoTheme {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Pergunta $numberQuestions", style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = artist.img),
                    contentDescription = ""
                )

                TextQuestion(
                    text = question.text,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                question.options.forEach { option ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = if (selectedOption == option) 2.dp else 0.dp,
                                color = when {
                                    selectedOption == option && isCorrectAnswer == true -> Color.Green
                                    selectedOption == option && isCorrectAnswer == false -> Color.Red
                                    else -> Color.Transparent
                                },
                                shape = RectangleShape
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioAnswer(
                            option = option,
                            selectedOption = selectedOption,
                            onOptionSelected = {
                                selectedOption = it
                                isCorrectAnswer = (it == question.correctAnswer)
                                if (isCorrectAnswer == true) {
                                    viewModel.increaseScore(10)
                                }

                                hasAnswered = true
                            }
                        )
                        TextAnswer(text = option, style = androidx.compose.ui.text.TextStyle())
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        "$playerName",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF915EEB).copy(alpha = 0.5f)
                        )
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        "$score",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0x910114EB),
                                    Color(0x91019224),
                                    Color(0x918F008B)
                                )
                            )
                        )
                    )
                }
            }

            if (hasAnswered) {
                artist = artistData.random()
                question = artist.questions.random()
                selectedOption = null
                isCorrectAnswer = null
                numberQuestions += 1
                hasAnswered = false

                if (numberQuestions == 3) {
                    viewModel.addPlayerToRanking(playerName)
                    val context = androidx.compose.ui.platform.LocalContext.current
                    val intent = Intent(context, Ranking::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizzGameplayPreview() {
    QuizSomativoTheme {
        QuizzGameplayUI(playerName = "João")
    }
}

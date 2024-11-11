package my.app.quizsomativo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            QuizzMenuUI()
        }
    }
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
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x918A4CFA),
            contentColor = Color.White
        ),
    ) {
        TextComposable(text = "PLAY")
    }
}

@Composable
fun ButtonRanking(onRankingClicked: () -> Unit) {


    Button(
        modifier = Modifier
            .height(50.dp),
        onClick = {
            onRankingClicked()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x91BDA000),
            contentColor = Color.Black
        ),
    ) {
        TextComposable(text = "Ranking \uD83C\uDFC6")
    }
}

@Composable
fun TextComposable(text: String, modifier: Modifier = Modifier) {
    Text(text = "$text",modifier = modifier)

}

@Composable
fun QuizzMenuUI() {
    QuizSomativoTheme {

        val viewModel = QuizzViewModel.getInstance()
        val context = LocalContext.current
        var playerName by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(WindowInsets.systemBars.asPaddingValues())
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ARTIST QUIZZ",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0x91EB5DE6),
                                Color(0x91915EEB),
                                Color(0x91AFEB60),
                                Color(0x91BF5EEB)
                            )
                        )
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Insira seu nome de jogador:",
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextFieldText(
                    value = playerName,
                    onValueChange = { newText -> playerName = newText },
                    label = "Nome do Jogador"
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ButtonPlay(playerName = playerName) {
                    viewModel.setScore(0)
                    val intent = Intent(context, Quizz::class.java).apply {
                        putExtra("playerName", playerName)
                    }
                    context.startActivity(intent)
                }

                ButtonRanking {
                    val intent = Intent(context, Ranking::class.java)
                    context.startActivity(intent)
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
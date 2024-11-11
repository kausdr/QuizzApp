package my.app.quizsomativo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class Ranking : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizSomativoTheme {
                val navController = rememberNavController()
                QuizzRankingUI(navController)
            }
        }
    }
}


@Composable
fun TextNames(text: String, modifier: Modifier = Modifier, style: androidx.compose.ui.text.TextStyle) {
    Text(text = "$text",modifier = modifier, style = style)

}

@Composable
fun TextPoints(text: Int, modifier: Modifier = Modifier, style: androidx.compose.ui.text.TextStyle) {
    Text(text = "$text",modifier = modifier, style = style)

}

@Composable
fun ButtonMenu(onMenuClicked: () -> Unit) {

    Button(
        modifier = Modifier
            .height(50.dp),
        onClick = {
            onMenuClicked()
        }
    ) {
        TextComposable(text = "MENU")
    }
}


@Composable
fun QuizzRankingUI(navController: androidx.navigation.NavController) {
    val viewModel = QuizzViewModel.getInstance()


    QuizSomativoTheme {

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
                    text = "Ranking",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
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


                viewModel.players.value.take(10).forEach { (name, score) ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextNames(text = name, style = androidx.compose.ui.text.TextStyle(
                                fontSize = 20.sp
                            ))
                            Spacer(modifier = Modifier.width(5.dp))

                            TextPoints(text = score, style = androidx.compose.ui.text.TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0x91AD8800)
                            ))
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                ButtonMenu() {
                    val context = navController.context
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizzRankingPreview() {
    QuizSomativoTheme {
        val navController = rememberNavController()
        QuizzRankingUI(navController)
    }
}
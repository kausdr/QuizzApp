package my.app.quizsomativo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.app.quizsomativo.ui.theme.QuizSomativoTheme

class Ranking : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizSomativoTheme {

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
fun TextRanking(text: String, modifier: Modifier = Modifier, style: androidx.compose.ui.text.TextStyle) {
    Text(text = "$text",modifier = modifier, style = style)

}


@Composable
fun QuizzRankingUI() {
    val names = remember { mutableStateListOf(Pair("Julio",10), Pair("Jerso",9), Pair("Andre",12)) }
    val rankingList = names.sortedByDescending { it.second }

    QuizSomativoTheme {

        Column (modifier = Modifier
        ) {
            TextRanking(
                text = "Ranking", style = androidx.compose.ui.text.TextStyle(
                )
            )

            rankingList.forEach { (name, points) ->

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        TextNames(text = name, style = androidx.compose.ui.text.TextStyle())
                        TextPoints(text = points, style = androidx.compose.ui.text.TextStyle())
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuizzRankingPreview() {
    QuizSomativoTheme {
        QuizzRankingUI()
    }
}
package my.app.quizsomativo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizzViewModel : ViewModel() {
    val score = mutableStateOf(0)
    val players = mutableStateOf(listOf<Player>())

    fun increaseScore(points: Int) {
        score.value += points
    }

    fun addPlayerToRanking(name: String) {
        players.value = players.value + Player(name, score.value)
    }



    companion object {
        @Volatile
        private var INSTANCE: QuizzViewModel? = null

        fun getInstance(): QuizzViewModel {
            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: QuizzViewModel().also { INSTANCE = it }
            }
        }
    }
}

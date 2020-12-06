package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(application: Application): AndroidViewModel(application){

    private val scope = CoroutineScope(Dispatchers.IO)
    private val gameRepository: GameRepository = GameRepository(application.applicationContext)
    val gameData: LiveData<List<Game>> = gameRepository.getAllGames()

    fun addGame(game: Game){
        scope.launch {
            gameRepository.addGame(game)
        }
    }

    fun deleteGame(game: Game) {
        scope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
        scope.launch {
            gameRepository.deleteAllGames()
        }
    }
}
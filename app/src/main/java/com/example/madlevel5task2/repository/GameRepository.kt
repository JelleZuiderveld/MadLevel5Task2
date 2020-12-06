package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.database.GameDatabase
import com.example.madlevel5task2.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    suspend fun addGame(game: Game) {
        return gameDao.addGame(game)
    }

    suspend fun deleteAllGames() {
       return gameDao.deleteAllGames()
    }

    suspend fun deleteGame(game: Game) {
       return gameDao.deleteGame(game)
    }

}
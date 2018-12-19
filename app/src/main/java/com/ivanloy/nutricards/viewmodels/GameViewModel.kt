package com.ivanloy.nutricards.viewmodels

import androidx.lifecycle.ViewModel
import com.ivanloy.nutricards.controllers.GameController
import com.ivanloy.nutricards.gamedata.NumPlayers

class GameViewModel : ViewModel(){

    var gameController : GameController? = null
        private set

    fun buildGameController(numPlayers: NumPlayers){
        gameController = GameController
                .Builder()
                .numPlayers(numPlayers)
                .build()
    }

}
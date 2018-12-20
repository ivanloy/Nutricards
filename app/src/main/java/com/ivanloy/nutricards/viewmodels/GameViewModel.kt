package com.ivanloy.nutricards.viewmodels

import androidx.lifecycle.ViewModel
import com.ivanloy.nutricards.controllers.GameController
import com.ivanloy.nutricards.controllers.GameControllerI
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameViewModel : ViewModel(), GameControllerI{

    private var gameController : GameController

    init {
        gameController = GameController
                .Builder()
                .numPlayers(NumPlayers.DEFAULT)
                .build()
    }

    fun buildGameController(numPlayers: NumPlayers = NumPlayers.DEFAULT){
        gameController = GameController
                .Builder()
                .numPlayers(numPlayers)
                .build()
    }

    fun getCurrentPlayer() : Int{
        return gameController.currentPlayer
    }

    fun getBoard() : Hand<FoodCard>{
        return gameController.board
    }

    override fun getBoardCard(index: Int): FoodCard {
        return gameController.getBoardCard(index)
    }

    override fun getCurrentPlayerHand(): Hand<FoodCard> {
        return gameController.getCurrentPlayerHand()
    }

    override fun nextPlayer() {
        gameController.nextPlayer()
    }

    override fun drawCardToCurrentPlayerHand() {
        gameController.drawCardToCurrentPlayerHand()
    }

    override fun fillBoard() {
        gameController.fillBoard()
    }

    override fun drawCardFromBoardToCurrentPlayerHand(card: FoodCard) {
        gameController.drawCardFromBoardToCurrentPlayerHand(card)
    }

}
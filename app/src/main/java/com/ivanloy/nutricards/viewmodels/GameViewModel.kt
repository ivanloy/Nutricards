package com.ivanloy.nutricards.viewmodels

import androidx.lifecycle.ViewModel
import com.ivanloy.nutricards.controllers.GameController
import com.ivanloy.nutricards.controllers.GameControllerI
import com.ivanloy.nutricards.ds.FoodCardsDeck
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameViewModel : ViewModel(), GameControllerI{

    private var gameController : GameController

    init {
        gameController = GameController()
    }

    fun getInitialDeckSize() : Int{
        return gameController.initialDeckSize
    }

    fun buildGameController(numPlayers: NumPlayers = NumPlayers.DEFAULT) {
        gameController = GameController(numPlayers)
    }

    fun getCurrentPlayer() : Int{
        return gameController.currentPlayer
    }

    fun getBoard() : Hand<FoodCard>{
        return gameController.board
    }

    override fun addCardToCurrentPlayerHand(type: FoodCardTypes) {
        return gameController.addCardToCurrentPlayerHand(type)
    }

    override fun addCardToCurrentPlayerHand(card: FoodCard) {
        return gameController.addCardToCurrentPlayerHand(card)
    }

    override fun getBoardDecks(): MutableList<FoodCardsDeck> {
        return gameController.getBoardDecks()
    }

    override fun getCardAmountOfType(type: FoodCardTypes): Int {
        return gameController.getCardAmountOfType(type)
    }

    override fun calculateCurrentPlayerScore(): Int {
        return gameController.calculateCurrentPlayerScore()
    }

    override fun getCurrentDeckSize(): Int {
        return gameController.getCurrentDeckSize()
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

    override fun drawCardToCurrentPlayerHand() : Boolean {
        return gameController.drawCardToCurrentPlayerHand()
    }

    override fun fillBoard() : Boolean {
        return gameController.fillBoard()
    }

    override fun drawCardFromBoardToCurrentPlayerHand(card: FoodCard) {
        gameController.drawCardFromBoardToCurrentPlayerHand(card)
    }

    override fun drawCardFromBoardToCurrentPlayerHand(index: Int) {
        gameController.drawCardFromBoardToCurrentPlayerHand(index)
    }

}
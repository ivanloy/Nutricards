package com.ivanloy.nutricards.viewmodels

import androidx.lifecycle.ViewModel
import com.ivanloy.nutricards.R
import com.ivanloy.nutricards.controllers.GameController
import com.ivanloy.nutricards.controllers.GameControllerI
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameViewModel : ViewModel(), GameControllerI{

    private var gameController : GameController

    fun getCardImageResource(card: FoodCard) : Int{
        return when(card.type){
            FoodCardTypes.CEREAL -> R.drawable.cereal
            FoodCardTypes.DAIRY -> R.drawable.dairy
            FoodCardTypes.FISH -> R.drawable.fish
            FoodCardTypes.FORK -> R.drawable.fork
            FoodCardTypes.FRUIT -> R.drawable.fruit
            FoodCardTypes.MEAT -> R.drawable.meat
            FoodCardTypes.PASTA -> R.drawable.pasta
            FoodCardTypes.SWEET -> R.drawable.sweet
            FoodCardTypes.VEGETABLE -> R.drawable.vegetable
            else -> R.drawable.sweet //TODO YUNOW
        }
    }

    fun getCardImageResourceFromBoard(index : Int) : Int{
        return getCardImageResource(getBoardCard(index)) //TODO Do in other index method
    }

    init {
        gameController = GameController()
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

    override fun drawCardFromBoardToCurrentPlayerHand(index: Int) {
        gameController.drawCardFromBoardToCurrentPlayerHand(index)
    }

}
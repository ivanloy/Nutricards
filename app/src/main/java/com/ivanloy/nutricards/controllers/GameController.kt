package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.FoodCardsDeck
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameController private constructor(
        var deck : FoodCardsDeck,
        var hands : MutableList< Hand< FoodCard >>,
        var numPlayers : NumPlayers,
        var board : Hand< FoodCard >) : GameControllerI
{

    var currentPlayer : Int = 0
        private set

    override fun getBoardCard(index: Int) : FoodCard {
        return board.peekCard(index)
    }

    override fun nextPlayer(){
        if(currentPlayer == numPlayers.nPlayers - 1) currentPlayer = 0 else currentPlayer++
    }

    override fun drawCardToCurrentPlayerHand(){
        hands[currentPlayer]
                .addCard(deck.drawCard())
    }

    override fun fillBoard(){
        board = Hand()
        repeat(numPlayers.nPlayers){
            board.addCard(deck.drawCard())
        }
    }

    override fun getCurrentPlayerHand(): Hand<FoodCard> {
        return hands[currentPlayer]
    }

    override fun drawCardFromBoardToCurrentPlayerHand(card : FoodCard){
        board.removeCard(card)
        hands[currentPlayer].addCard(card)
    }

    data class Builder(val numPlayers: NumPlayers = NumPlayers.TWO_PLAYERS) {

        private val deck : FoodCardsDeck = FoodCardsDeck()
        private val hands : MutableList< Hand < FoodCard >> = ArrayList()

        private fun buildDeck(numPlayers: NumPlayers) : FoodCardsDeck =
                deck.apply { buildInitialDeck(numPlayers)}
                        .apply { shuffle() }


        private fun buildHands(numPlayers: NumPlayers){
            repeat(numPlayers.nPlayers){
                hands.add(Hand())
            }
        }

        fun numPlayers(numPlayers: NumPlayers) =
                apply { buildDeck(numPlayers) }
                        .apply { buildHands(numPlayers) }

        fun build() = GameController(deck, hands, numPlayers, Hand())

    }

}
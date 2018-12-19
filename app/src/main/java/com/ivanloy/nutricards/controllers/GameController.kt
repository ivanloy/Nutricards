package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.FoodCardsDeck
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameController private constructor(
        var deck : FoodCardsDeck,
        var hands : MutableList< Hand < FoodCard >>,
        var numPlayers: NumPlayers)
{

    var currentPlayer : Int = 0
        private set

    fun nextPlayer(){
        if(currentPlayer == numPlayers.nPlayers - 1) currentPlayer = 0 else currentPlayer++
    }

    fun drawCardToCurrentPlayerHand(){
        hands[currentPlayer]
                .addCard(deck.drawCard())
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

        fun build() = GameController(deck, hands, numPlayers)

    }

}
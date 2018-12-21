package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gameelements.FoodCard

/**
 * Created by ofunes on 20/12/18.
 */
interface GameControllerI {

    fun nextPlayer()
    fun drawCardToCurrentPlayerHand()
    fun fillBoard()
    fun drawCardFromBoardToCurrentPlayerHand(card : FoodCard)
    fun getCurrentPlayerHand() : Hand<FoodCard>
    fun getBoardCard(index : Int) : FoodCard
    fun drawCardFromBoardToCurrentPlayerHand(index : Int)

}
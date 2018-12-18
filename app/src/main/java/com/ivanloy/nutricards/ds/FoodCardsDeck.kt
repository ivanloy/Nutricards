package com.ivanloy.nutricards.ds

import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.FoodCardsDeckComposition
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class FoodCardsDeck : Stequeue<FoodCard>() {

    fun putCardOnTop(type: FoodCardTypes) {
        this.putCardOnTop(type, 1)
    }

    fun putCardOnTop(type: FoodCardTypes, amount : Int) {
        repeat(amount){
            super.push(FoodCard(type))
        }
    }

    fun putCardOnBottom(type: FoodCardTypes) {
        this.putCardOnBottom(type, 1)
    }

    fun putCardOnBottom(type: FoodCardTypes, amount : Int) {
        repeat(amount){
            super.enqueue(FoodCard(type))
        }
    }

    fun drawCard(): FoodCard {
        return super.pop()
    }

    fun buildInitialDeck(numPlayers: NumPlayers){

        if(numPlayers == NumPlayers.TWO_PLAYERS){
            this.buildTwoPlayersDeck()
        }

    }

    fun buildTwoPlayersDeck(){

        putCardOnTop(
                FoodCardTypes.BLANK,
                FoodCardsDeckComposition.TWO_PLAYERS_BLANK
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.DAIRY,
                FoodCardsDeckComposition.TWO_PLAYERS_DAIRY
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.FORK,
                FoodCardsDeckComposition.TWO_PLAYERS_FORK
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.SWEET,
                FoodCardsDeckComposition.TWO_PLAYERS_SWEET
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.FRUIT,
                FoodCardsDeckComposition.TWO_PLAYERS_FRUIT
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.VEGETABLE,
                FoodCardsDeckComposition.TWO_PLAYERS_VEGETABLE
                        .quantity
        )

        putCardOnTop(
                FoodCardTypes.MEAT,
                FoodCardsDeckComposition.TWO_PLAYERS_MEAT_FISH
                        .quantity / 2
        )
        putCardOnTop(
                FoodCardTypes.FISH,
                FoodCardsDeckComposition.TWO_PLAYERS_MEAT_FISH
                        .quantity / 2
        )

        putCardOnTop(
                FoodCardTypes.CEREAL,
                FoodCardsDeckComposition.TWO_PLAYERS_PASTA_CEREAL
                        .quantity / 2
        )
        putCardOnTop(
                FoodCardTypes.PASTA,
                FoodCardsDeckComposition.TWO_PLAYERS_PASTA_CEREAL
                        .quantity / 2
        )

        shuffle();
    }

}

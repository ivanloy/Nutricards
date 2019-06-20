package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.FoodCardsDeckComposition
import com.ivanloy.nutricards.gameelements.FoodCard

/**
 * Created by ivanloy on 15/01/19.
 */
class PointsCalculator {

    companion object {

        fun calculateHandPoints(hand : Hand<FoodCard>) : Int {

            var cardMap : HashMap<FoodCardTypes, Int> = getCardsMap(hand)
            var total : Int = 0


            total += calculateSweetPoints(cardMap[FoodCardTypes.SWEET])
            total += calculateDairyPoints(cardMap[FoodCardTypes.DAIRY])
            total += calculateFruitPoints(cardMap[FoodCardTypes.FRUIT])
            total += calculateOnionPoints(cardMap[FoodCardTypes.VEGETABLE])
            total += calculateMeatAndFishPoints(cardMap[FoodCardTypes.MEAT], cardMap[FoodCardTypes.FISH])
            total += calculateCerealPoints(cardMap[FoodCardTypes.CEREAL])


            return total
            //TODO Vegetable and cereal
        }

        fun calculateSweetPoints(amount : Int?) : Int {
            val amountNotNull = amount ?: 0
            var ret : Int = 0
            if(amountNotNull == 1) ret = 10
            else if(amountNotNull > 1) ret = -5 * amountNotNull
            return ret
        }

        fun calculateCerealPoints(amount: Int?) : Int {
            if(amount == null) return 0
            if(amount == 0) return 0
            val total = FoodCardsDeckComposition.TWO_PLAYERS_PASTA_CEREAL.quantity
            val half = total / 2
            if(amount > half) return 7
            else return 3

        }

        fun calculateFruitPoints(amount : Int?) : Int{
            val amountNotNull = amount ?: 0
            var ret : Int = 0
            if(amountNotNull == 3) ret = 8
            else if(amountNotNull == 5) ret = -10
            else if(amountNotNull == 6) ret = 20
            return ret
        }

        fun calculateDairyPoints(amount : Int?) : Int{
            return amount ?: 0
        }

        fun calculateOnionPoints(amount : Int?) : Int{
            return if(amount != null) amount * 2 else 0
        }

        fun calculateMeatAndFishPoints(meatAmount : Int?, fishAmount : Int?) : Int{
            val meatAmountNotNull = meatAmount ?: 0
            val fishAmountNotNull = fishAmount ?: 0
            var ret : Int = 0
            if(meatAmountNotNull >= 3) ret -= 4
            if(fishAmountNotNull >= 3) ret -= 4
            else if(meatAmountNotNull < 3 && fishAmountNotNull < 3 && meatAmountNotNull > 0 && fishAmountNotNull > 0){ //TODO exclusive, legibility
                if(meatAmountNotNull == 2 && fishAmountNotNull == 2) ret += 12
                else ret += 6
            }
            return ret
        }

        fun getCardsMap(hand : Hand<FoodCard>) : HashMap<FoodCardTypes, Int>{

            var cardMap : HashMap<FoodCardTypes, Int> = HashMap()
            for(card in hand.cardList){
                var cardTypeAmount : Int? = cardMap[card.type]
                if(cardTypeAmount == null) cardMap.put(card.type, 1)
                else cardMap.put(card.type, cardTypeAmount + 1)
            }
            return cardMap

        }

    }



}
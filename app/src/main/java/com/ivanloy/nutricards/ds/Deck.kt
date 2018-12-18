package com.ivanloy.nutricards.ds

import com.ivanloy.nutricards.gameelements.FoodCard

class Deck : Stequeue<FoodCard>() {

    fun putCardOnTop(card: FoodCard) {
        super.push(card)
    }

    fun putCardOnBottom(card: FoodCard) {
        super.enqueue(card)
    }

    fun drawCard(): FoodCard {
        return super.pop()
    }

}

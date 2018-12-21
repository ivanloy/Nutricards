package com.ivanloy.nutricards.ds

import com.ivanloy.nutricards.gameelements.FoodCard

class Hand<T>: Cloneable {

    var cardList: MutableList<T> = ArrayList()
        private set

    constructor(hand : Hand<T>){
        this.cardList = hand.cardList.toMutableList();
    }

    constructor(vararg cards : T){
        cardList = cards.toMutableList()
    }

    fun peekCard(index : Int) : T{
        return cardList[index]
    }

    fun removeCard(vararg cards: T){
        cards.forEach {
            cardList.remove(it)
        }
    }

    fun removeCardWithPosition(index : Int) : T {
        val card : T = cardList[index]
        cardList.removeAt(index)
        return card
    }

    fun size(): Int {
        return cardList.size
    }

    fun addCard(vararg cards: T) {
        cardList.addAll(cards.toList())
    }

    fun addCard(hand: Hand<T>) {
        cardList.addAll(hand.cardList)
    }

}
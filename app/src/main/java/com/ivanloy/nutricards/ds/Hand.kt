package com.ivanloy.nutricards.ds

class Hand<T>: Cloneable {

    var handList: MutableList<T> = ArrayList()
        private set

    constructor(hand : Hand<T>){
        this.handList = hand.handList
    }

    constructor(vararg cards : T){
        handList = cards.toMutableList()
    }

    fun size(): Int {
        return handList.size
    }

    fun addCard(vararg cards: T) {
        handList.addAll(cards.toList())
    }

    fun addCard(hand: Hand<T>) {
        handList.addAll(hand.handList)
    }

}
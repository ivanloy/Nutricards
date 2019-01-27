package com.ivanloy.nutricards.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanloy.nutricards.R
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gameelements.FoodCard
import com.makeramen.roundedimageview.RoundedImageView

class CardStackAdapter(
        private var context: Context,
        private var cards: List<FoodCard> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.food_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card : String = cards[position].type.toString()

        Glide.with(context)
                .load(getCardImage(cards[position].type))
                .into(holder.image)

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, card, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun setSpots(spots: List<FoodCard>) {
        this.cards = spots
    }

    fun getSpots(): List<FoodCard> {
        return cards
    }

    fun setData(cards : List<FoodCard>) {
        this.cards = cards
        notifyDataSetChanged()
    }

    fun getData() : List<FoodCard> { //TODO swipeListerer I guess
        return this.cards
    }

    fun getCardImage(type : FoodCardTypes) : Int{
        var ret : Int

        when(type) {
            FoodCardTypes.SWEET -> ret = R.drawable.donut2
            FoodCardTypes.FRUIT -> ret = R.drawable.apple2
            FoodCardTypes.VEGETABLE -> ret = R.drawable.onion2
            FoodCardTypes.DAIRY -> ret = R.drawable.milk2
            FoodCardTypes.FISH -> ret = R.drawable.salmon2
            FoodCardTypes.FORK -> ret = R.drawable.fork2
            FoodCardTypes.MEAT -> ret = R.drawable.bife2
            FoodCardTypes.CEREAL -> ret = R.drawable.cereal2
            FoodCardTypes.PASTA -> ret = R.drawable.cereal2
            else -> ret = R.drawable.card_back
        }

        return ret
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: RoundedImageView = view.findViewById(R.id.card_image)
    }

}

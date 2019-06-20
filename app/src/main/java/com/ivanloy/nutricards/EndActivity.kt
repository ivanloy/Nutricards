package com.ivanloy.nutricards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivanloy.nutricards.gameelements.EndGameData
import kotlinx.android.synthetic.main.activity_end.*

class EndActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        val data = intent.getParcelableExtra<EndGameData>("data")

        tv_message.text = data.message

        tv_playerfruit.text = data.playerApples
        tv_playeronion.text = data.playerOnion
        tv_playerdonut.text = data.playerDonut
        tv_playermilk.text = data.playerMilk
        tv_playercereal.text = data.playerCereal
        tv_playermeatfish.text = data.playerMeatFish
        tv_playertotal.text = data.playerTotal

        tv_rivalfruit.text = data.iaApples
        tv_rivalonion.text = data.iaOnion
        tv_rivaldonut.text = data.iaDonut
        tv_rivalmilk.text = data.iaMilk
        tv_rivalcereal.text = data.iaCereal
        tv_rivalmeatfish.text = data.iaMeatFish
        tv_rivaltotal.text = data.iaTotal

        btn_back.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }

}

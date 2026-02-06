package com.example.a2d_gamedevelopment_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2d_gamedevelopment_project.gameView.GameView


class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }
}
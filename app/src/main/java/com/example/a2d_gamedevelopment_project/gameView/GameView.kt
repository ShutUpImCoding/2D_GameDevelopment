package com.example.a2d_gamedevelopment_project.gameView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {

    private var thread: Thread? = null
    private var running = false
    private val paint = Paint()

    fun resume() {
        running = true
        thread = Thread(this)
        thread!!.start()
    }

    fun pause() {
        running = false
        thread!!.join()
    }

    override fun run() {
        while (running) {
            if (!holder.surface.isValid) continue

            update()

            val canvas = holder.lockCanvas()
            drawGame(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun update() {
        // game logic later
    }

    private fun drawGame(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        paint.color = Color.WHITE
        canvas.drawCircle(300f, 300f, 50f, paint)
    }
}

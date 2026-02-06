package com.example.a2d_gamedevelopment_project.gameView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView
import kotlin.math.sqrt

class GameView(context: Context) : SurfaceView(context), Runnable {
//test
    private var ballX = 300f
    private var ballY = 300f
    private val ballRadius = 50f

    private val launchSpeed = 15f

    private var velocityX = 0f
    private var velocityY = 0f

    private var thread: Thread? = null
    private var running = false
    private val paint = Paint()

    fun resume() {
        running = true
        thread = Thread(this)
        thread !!.start()
    }

    fun pause() {
        running = false
        thread !!.join()
    }

    override fun run() {
        while (running) {
            if (! holder.surface.isValid) continue

            update()

            val canvas = holder.lockCanvas()
            drawGame(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {

            val dx = event.x - ballX
            val dy = event.y - ballY

            val length = sqrt(dx * dx + dy * dy)

            if (length != 0f) {
                velocityX = (dx / length) * launchSpeed
                velocityY = (dy / length) * launchSpeed
            }
        }
        return true
    }

    private fun update() {
        ballX += velocityX
        ballY += velocityY

        if (ballX <= ballRadius || ballX >= width - ballRadius) {
            velocityX *= - 1
        }

        if (ballY <= ballRadius || ballY >= height - ballRadius) {
            velocityY *= - 1
        }

    }

    private fun drawGame(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        paint.color = Color.MAGENTA
        canvas.drawCircle(ballX, ballY, ballRadius, paint)
    }
}

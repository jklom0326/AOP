package fastcampas.aop.part2.aop_part2_chapter07

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SoundVisualizerView(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var onRequestCurrentAmplitude: (() -> Int)? = null

    // 계단화 방지
    private val amplitudePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.purple_500)
        strokeWidth = LINE_WIDTH
        // 끝에 동그랗게 처리한다
        strokeCap = Paint.Cap.ROUND
    }

    private var drawingWidth: Int = 0
    private var drawingHeight: Int = 0
    private var drawingAmplitudes: List<Int> = emptyList()
    private var isReplaying: Boolean = false
    private var replayingPositon = 0

    private val visualizeRepeatAction: Runnable = object : Runnable {
        override fun run() {
            if (!isReplaying) {

                // Amplitude, Draw
                val currentAmplitude = onRequestCurrentAmplitude?.invoke() ?: 0
                drawingAmplitudes = listOf(currentAmplitude) + drawingAmplitudes
            } else {
                replayingPositon++
            }
            // 다시호출해서 그려주는 메소드
            invalidate()
            handler?.postDelayed(this, ACTION_INTERVAL)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawingWidth = w
        drawingHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        val centerY = drawingHeight / 2f
        var offsetX = drawingWidth.toFloat()

        drawingAmplitudes
            .let { amplitudes ->
                if (isReplaying) {
                    amplitudes.takeLast(replayingPositon)
                } else {
                    amplitudes
                }
            }
            .forEach { amplitude ->
                val lineLength = amplitude / MAX_AMPLITUDE * drawingHeight * 0.8f

                offsetX -= LINE_SPACE
                if (offsetX < 0) return@forEach



                canvas.drawLine(
                    offsetX,
                    centerY - lineLength / 2f,
                    offsetX,
                    centerY + lineLength / 2f,
                    amplitudePaint
                )
            }
    }

    fun startVisualizing(isReplaying: Boolean) {
        this.isReplaying = isReplaying
        handler?.post(visualizeRepeatAction)
    }

    fun clearVisualization(){
        drawingAmplitudes = emptyList()
        invalidate()
    }

    fun stopVisualizing() {
        replayingPositon = 0
        handler?.removeCallbacks(visualizeRepeatAction)
    }


    companion object {
        private const val LINE_WIDTH = 10f
        private const val LINE_SPACE = 15f
        private const val MAX_AMPLITUDE = Short.MAX_VALUE.toFloat()
        private const val ACTION_INTERVAL = 20L
    }
}
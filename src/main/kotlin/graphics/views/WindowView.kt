package graphics.views

import graphics.Scene
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

class WindowView(
        private val scene: Scene
) : View, JPanel() {
    private val img = BufferedImage(scene.cols, scene.rows, BufferedImage.TYPE_INT_RGB)
    private val frame = JFrame("Ray Marcher")

    init {
        frame.setSize(scene.cols, scene.rows)
        frame.isVisible = true
        frame.contentPane.add(this)
        background = Color.BLACK
        size = Dimension(scene.cols, scene.rows)
    }

    override fun paintComponent(g: Graphics?) {
        g ?: return
        val timeStart = System.currentTimeMillis()

        val clipBounds = g.clipBounds
        val leftX = clipBounds.x
        val leftY = clipBounds.y
        val rightX = leftX + clipBounds.width
        val rightY = leftY + clipBounds.height

        val pixels = scene.getPixels(leftX, leftY, rightX, rightY)
        img.raster.setPixels(leftX, leftY, clipBounds.width, clipBounds.height, pixels)
        g.drawImage(img, leftX, leftY, null)

        println("Time of frame: ${System.currentTimeMillis() - timeStart}")

    }

    override fun computeFrame() {
        paintImmediately(0, 0, width, height)
    }
}
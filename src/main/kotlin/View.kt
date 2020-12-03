import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class View(
        private val scene: Scene
) : JPanel() {
    private val img = BufferedImage(scene.cols, scene.rows, BufferedImage.TYPE_INT_RGB)

    init {
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
}
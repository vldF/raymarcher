import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class View(
        private val scene: Scene
) : JPanel() {
    private val img = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)

    init {
        background = Color.BLACK
        size = Dimension(300, 300)
    }

    override fun paintComponent(g: Graphics?) {
        val timeStart = System.currentTimeMillis()

        val pixels = scene.getPixels()
        img.raster.setPixels(0, 0, 300, 300, pixels)
        g?.drawImage(img, 0, 0, null)

        println("Time of frame: ${System.currentTimeMillis() - timeStart}")

    }
}
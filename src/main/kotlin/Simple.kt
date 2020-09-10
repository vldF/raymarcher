import math.Vector3d
import objects.Sphere
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

fun main() {
    val frame = JFrame()
    val pane = frame.contentPane
    frame.size = Dimension(300, 300)
    frame.isVisible = true

    val view = View()

    pane.add(view)

    while (true) {
        view.repaint()
    }
}

class View : JPanel() {
    private val w = 300
    private val h = 300

    private var sphereX = 45.0
    private var sphereY = 0.0
    private var sphereZ = 0.0
    private var sphereRadius = 8.0
    private val scene = Scene(h, w)
    private val sphere = Sphere(Vector3d(sphereX, sphereY, sphereZ), sphereRadius)
    private val img = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)

    private var stepModifier = 1
    init {
        background = Color.BLACK
        size = Dimension(300, 300)
        scene.addObject(sphere)
    }

    override fun paintComponent(g: Graphics?) {
        val timeStart = System.currentTimeMillis()
        sphereY += 0.2 * stepModifier

        if (sphereY > 10 ) stepModifier *= -1
        if (sphereY < -10) stepModifier *= -1

        val newVector = Vector3d(sphereX, sphereY, sphereZ)
        sphere.position = newVector

        val pixels = scene.getPixels()

        img.raster.setPixels(0, 0, 300, 300, pixels)
        g?.drawImage(img, 0, 0, null)

        println("Time of frame: ${System.currentTimeMillis() - timeStart}")

    }
}

import math.Vector3d
import objects.Sphere
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSlider

fun main() {
    val frame = JFrame()
    val pane = frame.contentPane
    frame.size = Dimension(300, 300)
    frame.isVisible = true

    val view = View()
    val sliderX = JSlider(JSlider.HORIZONTAL, -5, 5, 0)
    val sliderY = JSlider(JSlider.HORIZONTAL, -5, 5, 0)
    val sliderZ = JSlider(JSlider.HORIZONTAL, -5, 5, 0)

    pane.add(view)
    /*
    val sliders = JPanel(GridLayout(0, 1))
    sliders.add(sliderX)
    sliders.add(sliderY)
    sliders.add(sliderZ)
    sliders.size = Dimension(300, 50)

    pane.add(sliders)
    pane.repaint()
    */
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
    private val img = BufferedImage(300, 300, BufferedImage.TYPE_BYTE_GRAY)

    init {
        background = Color.BLACK
        size = Dimension(300, 300)
        scene.addObject(sphere)

    }

    override fun paintComponent(g: Graphics?) {
        val timeStart = System.currentTimeMillis()
        sphereY += 0.1

        val newVector = Vector3d(sphereX, sphereY, sphereZ)
        sphere.position = newVector


        val pixels = scene.getPixels()

        val timeFilling = System.currentTimeMillis()
        img.raster.setPixels(0, 0, 300, 300, pixels)
        g?.drawImage(img, 0, 0, null)

        println("Time of filling: ${System.currentTimeMillis() - timeFilling}")
        println("Time of frame: ${System.currentTimeMillis() - timeStart}")

    }
}

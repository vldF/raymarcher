import math.Vector3d
import objects.Sphere
import sun.awt.image.ByteInterleavedRaster
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSlider

fun main() {
    val frame = JFrame()
    val pane = frame.contentPane
    pane.layout = GridLayout(2, 1)
    frame.size = Dimension(300, 350)
    frame.isVisible = true

    val canv = Canv()
    val sliderX = JSlider(JSlider.HORIZONTAL, -5, 5, 0)
    val sliderY = JSlider(JSlider.HORIZONTAL, -5, 5, 0)
    val sliderZ = JSlider(JSlider.HORIZONTAL, -5, 5, 0)

    pane.add(canv)
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
        canv.repaint()
    }
}

class Canv : Canvas() {
    val w = 300
    val h = 300

    var sphereX = 0.0
    var sphereY = 0.0
    var sphereZ = 15.0
    var sphereRadius = 10.0
    val scene = Scene(h, w)
    val sphere = Sphere(Vector3d(sphereX, sphereY, sphereZ), sphereRadius)

    init {
        background = Color.BLACK
        size = Dimension(300, 300)
        scene.addObject(sphere)

    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        sphereY += 0.1
        if (sphereY >= 9) {
            sphereY = -5.0
        }
        val newVector = Vector3d(sphereX, sphereY, sphereZ)
        sphere.position = newVector
        println(sphereY)

        val pixels = scene.getPixels()

        val img = BufferedImage(300, 300, BufferedImage.TYPE_BYTE_GRAY)
        (img.raster as ByteInterleavedRaster).putByteData(0, 0, 300, 300, pixels)
        g?.drawImage(img, 0, 0, null)

    }
}

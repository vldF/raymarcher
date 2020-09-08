import math.Vector3d
import objects.Sphere
import java.awt.image.BufferedImage

fun main() {
    val w = 300
    val h = 300

    var sphereX = 30.0
    var sphereY = 20.0
    var sphereZ = 0.0
    var sphereRadius = 35.0

    while (true) {
        val scene = Scene(h, w)
        val sphere = Sphere(Vector3d(sphereX, sphereY, sphereZ), sphereRadius)
        scene.addObject(sphere)
        val pixels = scene.getPixels()
        val image = BufferedImage(h, w, BufferedImage.TYPE_BYTE_GRAY)
        for ((i, p) in pixels.withIndex()) {
            val v = if (p.toInt() == 0) 0 else Int.MAX_VALUE
            image.setRGB(i % w, i / w, v)
        }

        println()
    }
}
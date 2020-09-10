package objects

import graphics.ColorValue
import math.Vector3d
import java.awt.Color
import java.lang.Double.max
import kotlin.math.atan
import kotlin.math.tan

class Sphere(
        override var position: Vector3d,
        val radius: Double
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        return vec.dist(position) - radius
    }

    override fun color(coords: Vector3d): ColorValue {
        val r = radius
        val localCoords = coords - position
        val g = 0.0
        val b = atan(localCoords.z / r) + Math.PI / 2

        return ColorValue(Vector3d(r, g, b * 10).normalize * 127.0)
    }

    companion object {
        private val color = ColorValue(Byte.MAX_VALUE, 0, 0)
    }
}
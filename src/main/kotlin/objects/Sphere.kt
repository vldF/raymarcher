package objects

import graphics.ColorValue
import math.Vector3d
import java.lang.Double.max

class Sphere(
        override var position: Vector3d,
        val radius: Double
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        return vec.dist(position) - radius
    }

    override fun getMaxDist(vec: Vector3d): Double {
        return vec.dist(position) + radius
    }

    override fun color(coords: Vector3d): ColorValue = color

    companion object {
        private val color = ColorValue(Byte.MAX_VALUE.toInt(), 0, 0)
    }
}
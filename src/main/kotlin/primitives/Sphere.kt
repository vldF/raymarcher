package primitives

import graphics.ColorValue
import math.Vector3d
import kotlin.math.atan

class Sphere(
        override var position: Vector3d,
        var radius: Double,
        val color: ColorValue? = null
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        return vec.dist(position) - radius
    }

    override fun color(coords: Vector3d): ColorValue {
        if (color != null) return color

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
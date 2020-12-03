package primitives

import graphics.ColorValue
import math.Vector3d
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

class CapedCylinder(
        override var position: Vector3d,
        var height: Double,
        var radius: Double
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localPos = position - vec
        return max(
                sqrt(localPos.x * localPos.x + localPos.y*localPos.y) - radius,
                abs(localPos.z) - height / 2
        )
    }

    override fun color(coords: Vector3d): ColorValue = ColorValue.white
}
package graphics

import math.Vector3d

data class ColorValue(
        val r: Int,
        val g: Int,
        val b: Int
) {
    companion object {
        val black = ColorValue(0, 0, 0)
    }

    constructor(vector: Vector3d) : this(vector.x.toInt(), vector.y.toInt(), vector.z.toInt())

    val vector
        get() = Vector3d(r.toDouble(), g.toDouble(), b.toDouble())
}
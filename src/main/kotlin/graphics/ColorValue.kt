package graphics

import math.Vector3d

data class ColorValue(
        val r: Byte,
        val g: Byte,
        val b: Byte
) {
    companion object {
        val black = ColorValue(0, 0, 0)
        val white = ColorValue(127, 127, 127)
        val red = ColorValue(127, 0, 0)
    }

    constructor(vector: Vector3d) : this(
            vector.x.toInt().toByte(),
            vector.y.toInt().toByte(),
            vector.z.toInt().toByte()
    )

    val rInt = r.toInt()
    val gInt = g.toInt()
    val bInt = b.toInt()

    val vector
        get() = Vector3d(r.toDouble(), g.toDouble(), b.toDouble())
}
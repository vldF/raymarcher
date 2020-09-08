package math

import kotlin.math.pow
import kotlin.math.sqrt

class Vector3d(x: Double, y: Double, z: Double) {
    companion object{
        val empty = Vector3d(.0, .0, .0)
    }
    var components = arrayOf(x, y, z)

    operator fun minus(other: Vector3d): Vector3d {
        return Vector3d(x - other.x, y - other.y, z - other.z)
    }

    operator fun plus(other: Vector3d): Vector3d {
        return Vector3d(other.x + x, other.y + y, other.z + z)
    }

    operator fun div(other: Double): Vector3d {
        return Vector3d(x / other, y / other, z / other)
    }

    operator fun times(other: Double): Vector3d {
        return Vector3d(x * other, y * other, z * other)
    }

    fun dot(other: Vector3d): Vector3d {
        return Vector3d(x * other.x, y * other.y, z * other.z)
    }

    fun dist(other: Vector3d): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2) + (z - other.z).pow(2))
    }

    val abs
        get() = sqrt(x * x + y * y + z * z)

    val normalize: Vector3d
        get() {
            val absCurr = abs
            return Vector3d(x / absCurr, y / absCurr, z / absCurr)
        }

    val x: Double
        get() = components[0]
    val y: Double
        get() = components[1]
    val z: Double
        get() = components[2]

    private fun set(x: Double, y: Double, z: Double) {
        components[0] = x
        components[1] = y
        components[2] = z
    }

    override fun toString(): String {
        return "{$x, $y, $z}"
    }
}
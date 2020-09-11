package math

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Vector3d(x: Double, y: Double, z: Double) {
    constructor(vector: Vector3d) : this(vector.x, vector.y, vector.z)

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

    fun add(other: Vector3d) {
        components[0] += other.x
        components[1] += other.y
        components[2] += other.z
    }

    fun subtract(other: Vector3d) {
        components[0] -= other.x
        components[1] -= other.y
        components[2] -= other.z
    }

    fun mul(other: Vector3d) {
        components[0] *= other.x
        components[1] *= other.y
        components[2] *= other.z
    }

    fun mul(other: Double) {
        components[0] *= other
        components[1] *= other
        components[2] *= other
    }

    fun dot(other: Vector3d): Double {
        return x * other.x + y * other.y + z * other.z
    }

    fun cross(other: Vector3d): Vector3d {
        val newX = y * other.z - z * other.y
        val newY = z * other.x - x * other.z
        val newZ = x * other.y - y * other.x

        return Vector3d(newX, newY, newZ)
    }

    fun dist(other: Vector3d): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2) + (z - other.z).pow(2))
    }

    fun normalize() {
        val absCurr = abs
        if (absCurr == 0.0) return

        components[0] /= absCurr
        components[1] /= absCurr
        components[2] /= absCurr
    }

    fun rotate(angleX: Double, angleY: Double, angleZ: Double): Vector3d {
        var result = this
        if (angleX != 0.0) {
            result = result.rotateX(angleX)
        }
        if (angleY != 0.0) {
            result = result.rotateY(angleY)
        }
        if (angleZ != 0.0) {
            result = result.rotateZ(angleZ)
        }

        return result
    }

    fun rotateX(angle: Double): Vector3d {
        val newX = x
        val newY = y * cos(angle) - z * sin(angle)
        val newZ = y * sin(angle) + z * cos(angle)

        return Vector3d(newX, newY, newZ)
    }

    fun rotateY(angle: Double): Vector3d {
        val newX = x * cos(angle) + z * sin(angle)
        val newY = y
        val newZ = -x * sin(angle) + z * cos(angle)

        return Vector3d(newX, newY, newZ)
    }

    fun rotateZ(angle: Double): Vector3d {
        val newX = x * cos(angle) - y * sin(angle)
        val newY = x * sin(angle) + y * cos(angle)
        val newZ = z

        return Vector3d(newX, newY, newZ)
    }

    private val abs
        get() = sqrt(x * x + y * y + z * z)

    val normalize: Vector3d
        get() {
            val absCurr = abs
            if (absCurr == 0.0) return Vector3d(0.0, 0.0, 0.0)
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

fun mix(v1: Vector3d, v2: Vector3d, value: Double): Vector3d {
    return v1 * (1 - value) + v2 * value
}
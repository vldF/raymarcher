package math

import kotlin.math.*

class Vector3d(var x: Double, var y: Double, var z: Double) {
    constructor(vector: Vector3d) : this(vector.x, vector.y, vector.z)

    companion object{
        val zero = Vector3d(.0, .0, .0)
        private val sineTable = mutableMapOf<Double, Double>()
        private val cosineTable = mutableMapOf<Double, Double>()

        private fun fastCos(angle: Double): Double = cosineTable.getOrPut(angle % 2 * PI) { cos(angle) }
        private fun fastSin(angle: Double): Double = sineTable.getOrPut(angle % 2 * PI) { sin(angle) }
    }

    operator fun minus(other: Vector3d): Vector3d {
        return Vector3d(x - other.x, y - other.y, z - other.z)
    }

    operator fun plus(other: Vector3d): Vector3d {
        return Vector3d(other.x + x, other.y + y, other.z + z)
    }

    operator fun plus(num: Double): Vector3d {
        return Vector3d(x + num, y + num, z + num)
    }

    operator fun div(other: Double): Vector3d {
        return Vector3d(x / other, y / other, z / other)
    }

    operator fun times(other: Double): Vector3d {
        return Vector3d(x * other, y * other, z * other)
    }

    fun add(other: Vector3d) {
        x += other.x
        y += other.y
        z += other.z
    }

    fun subtract(other: Vector3d) {
        x -= other.x
        y -= other.y
        z -= other.z
    }

    fun mul(other: Vector3d) {
        x *= other.x
        y *= other.y
        z *= other.z
    }

    fun mul(other: Double) {
        x *= other
        y *= other
        z *= other
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

        x /= absCurr
        y /= absCurr
        z /= absCurr
    }

    fun rotated(angleX: Double, angleY: Double, angleZ: Double): Vector3d {
        val result = Vector3d(this)
        if (angleX != 0.0) {
            result.rotateX(angleX)
        }
        if (angleY != 0.0) {
            result.rotateY(angleY)
        }
        if (angleZ != 0.0) {
            result.rotateZ(angleZ)
        }

        return result
    }

    fun rotate(angleX: Double, angleY: Double, angleZ: Double): Vector3d {
        if (angleX != 0.0) {
            this.rotateX(angleX)
        }
        if (angleY != 0.0) {
            this.rotateY(angleY)
        }
        if (angleZ != 0.0) {
            this.rotateZ(angleZ)
        }

        return this
    }

    private fun rotateX(angle: Double) {
        val newY = y * fastCos(angle) - z * fastSin(angle)
        val newZ = y * fastSin(angle) + z * fastCos(angle)

        y = newY
        z = newZ
    }

    private fun rotateY(angle: Double) {
        val newX = x * fastCos(angle) + z * fastSin(angle)
        val newZ = -x * fastSin(angle) + z * fastCos(angle)

        x = newX
        z = newZ
    }

    private fun rotateZ(angle: Double) {
        val newX = x * fastCos(angle) - y * fastSin(angle)
        val newY = x * fastSin(angle) + y * fastCos(angle)

        x = newX
        y = newY
    }

    fun max(double: Double): Vector3d {
        return Vector3d(
                max(x, double),
                max(y, double),
                max(z, double)
        )
    }

    fun min(double: Double): Vector3d {
        return Vector3d(
                min(x, double),
                min(y, double),
                min(z, double)
        )
    }

    val absComponents: Vector3d
        get() {
            return Vector3d(
                    x.absoluteValue,
                    y.absoluteValue,
                    z.absoluteValue
            )
        }

    val abs
        get() = sqrt(x * x + y * y + z * z)

    val normalize: Vector3d
        get() {
            val absCurr = abs
            if (absCurr == 0.0) return Vector3d(0.0, 0.0, 0.0)
            return Vector3d(x / absCurr, y / absCurr, z / absCurr)
        }

    val xy
        get() = Vector3d(x, y, 0.0)
    val xz
        get() = Vector3d(x, z, 0.0)
    val yz
        get() = Vector3d(y, z, 0.0)

    override fun toString(): String {
        return "{$x, $y, $z}"
    }
}

fun mix(v1: Vector3d, v2: Vector3d, value: Double): Vector3d {
    return v1 * (1 - value) + v2 * value
}
package objects

import math.Vector3d
import kotlin.math.tan

class VirtualPixelGridIterator(
        private val width: Int,
        private val height: Int,
        private val coords: Vector3d,
        private val cameraDirection: Vector3d,
        private val fov: Pair<Double, Double>
) : Iterator<Vector3d> {
    private val applicata = Vector3d(0.0, 0.0, 1.0)
    private val cache = Array<Vector3d?>(width * height) { null }
    private val fovHTan = tan(fov.first / 2) * 2
    private val fovWTan = tan(fov.second / 2) * 2

    private var currentPixelIndex = 0

    override fun hasNext(): Boolean {
        return width * height > currentPixelIndex
    }

    override fun next(): Vector3d {
        if (cache[currentPixelIndex] != null) return cache[currentPixelIndex]!!

        val row = currentPixelIndex / width
        val col = currentPixelIndex % width

        val localR = cameraDirection.cross(applicata).normalize
        val localU = cameraDirection.cross(localR).normalize
        val result = cameraDirection + (localR * (col * fovWTan / width - .5)) + (localU * (row * fovHTan / height - .5))

        cache[currentPixelIndex] = result
        currentPixelIndex++

        return result
    }

}
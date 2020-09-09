package objects

import math.Vector3d

class VirtualPixelGridIterator(
        private val width: Int,
        private val height: Int,
        private val coords: Vector3d,
        private val cameraDirection: Vector3d
) : Iterator<Vector3d> {
    private var currentPixelIndex = 0

    override fun hasNext(): Boolean {
        return width * height > currentPixelIndex
    }

    override fun next(): Vector3d {
        val row = currentPixelIndex / width
        val col = currentPixelIndex % width

        val zeroYZero = Vector3d(0.0, 1.0, 0.0)

        val localR = zeroYZero.cross(cameraDirection).normalize
        val localU = cameraDirection.cross(localR).normalize

        val result = cameraDirection + (localR * (col * 1.0 / width)) + (localU * (row * 1.0 / height))

        currentPixelIndex++

        return result
    }

}
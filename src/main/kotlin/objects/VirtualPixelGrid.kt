package objects

import math.Vector3d

class VirtualPixelGridIterator(
        private val width: Int,
        private val height: Int,
        private val cameraPosition: Vector3d,
        private val cameraDirection: Vector3d
) : Iterator<Vector3d> {
    private var currentPixelIndex = 0

    override fun hasNext(): Boolean {
        return width * height > currentPixelIndex
    }

    override fun next(): Vector3d {
        val row = currentPixelIndex / width
        val col = currentPixelIndex % width
        val x = 1.0 / width * col - 0.5 + cameraPosition.x
        val y = 1.0 / height * row - 0.5 + cameraPosition.y
        val z = cameraDirection.z + cameraPosition.z
        currentPixelIndex++

        return Vector3d(x, y, z)
    }

}
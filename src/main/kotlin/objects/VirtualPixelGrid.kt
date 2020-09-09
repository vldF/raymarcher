package objects

import math.Vector3d
import kotlin.math.tan

class VirtualPixelGrid(
        private val width: Int,
        private val height: Int,
        private val coords: Vector3d,
        private val cameraDirection: Vector3d,
        private val fov: Pair<Double, Double>
) {
    private val applicata = Vector3d(0.0, 0.0, 1.0)
    private val cache = Array<Array<Vector3d?>>(width) { Array(height) { null } }
    private val fovHTan = tan(fov.first / 2) * 2
    private val fovWTan = tan(fov.second / 2) * 2

    fun getPixel(x: Int, y: Int): Vector3d {
        if (cache[x][y] != null) return cache[x][y]!!

        val localR = cameraDirection.cross(applicata).normalize
        val localU = cameraDirection.cross(localR).normalize
        val result = cameraDirection + (localR * (x * fovWTan / width - .5)) + (localU * (y * fovHTan / height - .5))

        cache[x][y] = result

        return result
    }

}
package graphics

import math.Vector3d
import kotlin.math.tan

class VirtualPixelGrid(
        private val width: Int,
        private val height: Int,
        private val coords: Vector3d,
        private val cameraDirection: Vector3d,
        fov: Double
) {
    private val applicata = Vector3d(0.0, 0.0, 1.0)
    private val cache = Array<Array<Vector3d?>>(width) { Array(height) { null } }
    private val fovHTan = tan(fov / 2) * 2
    private val fovWTan = tan(fov / 2) * 2
    private val localR = (cameraDirection + coords).cross(applicata).normalize
    private val localU = (cameraDirection + coords).cross(localR).normalize

    fun getPixel(x: Int, y: Int): Vector3d {
        if (cache[x][y] != null) return cache[x][y]!!
        val result = cameraDirection + coords + (localR * (x * fovWTan / width - fovWTan / 2)) + (localU * (y * fovHTan / height - fovHTan / 2))

        cache[x][y] = result
        return result
    }

}
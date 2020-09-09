package objects

import math.Vector3d

data class Camera (
    val coords: Vector3d,
    val direction: Vector3d,
    var fov: Double = kotlin.math.PI / 180 * 50 // 50 degrees
) {
    private val oldCoords = coords
    private val oldDirection = coords
    private var pixelIterator = VirtualPixelGridIterator(300, 300, coords, direction.normalize, fov to fov)

    val virtualPixels: VirtualPixelGridIterator
        get() {
            if (coords == oldCoords && direction == oldDirection)
                return pixelIterator
            pixelIterator = VirtualPixelGridIterator(300, 300, coords, direction.normalize, fov to fov)
            return  pixelIterator
        }
}
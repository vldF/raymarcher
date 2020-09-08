package objects

import math.Vector3d

data class Camera (
    var coords: Vector3d,
    var direction: Vector3d,
    var fov: Double = kotlin.math.PI / 2
) {
    val virtualPixels
        get() = VirtualPixelGridIterator(300, 300, direction)
}
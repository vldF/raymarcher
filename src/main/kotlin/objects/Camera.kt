package objects

import math.Vector3d

data class Camera (
    val coords: Vector3d,
    val direction: Vector3d,
    var fov: Double = kotlin.math.PI / 180 * 50 // 50 degrees
) {
    var pixelGrid = VirtualPixelGrid(300, 300, coords, direction, fov to fov)
}
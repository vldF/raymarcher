package objects

import math.Vector3d

data class Camera (
    private var coords: Vector3d,
    private var dir: Vector3d,
    var gridWidth: Int,
    var gridHeight: Int,
    var fov: Double = kotlin.math.PI / 180 * 100, // 180 degrees
) {
    private var isGridNeedsUpdate = false
    private var grid = VirtualPixelGrid(300, 300, coords, dir, fov)

    val pixelGrid: VirtualPixelGrid
        get() {
            if (isGridNeedsUpdate) {
                isGridNeedsUpdate = false
                grid = VirtualPixelGrid(300, 300, coords, dir, fov)
            }
            return grid
    }

    var position
        get() = coords
        set(value) {
            coords = value
            isGridNeedsUpdate = true
        }

    var direction
        get() = dir
        set(value) {
            dir = value
            isGridNeedsUpdate = true
        }
}
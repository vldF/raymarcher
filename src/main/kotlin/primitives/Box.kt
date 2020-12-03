package primitives

import graphics.ColorValue
import math.Vector3d

class Box(
        override var position: Vector3d,
        val sizes: Vector3d,
        val color: ColorValue = ColorValue.white
 ) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localCoords = position - vec
        val absLocalCoords = localCoords.absComponents
        return maxOf(
                absLocalCoords.x - sizes.x / 2,
                absLocalCoords.y - sizes.y / 2,
                absLocalCoords.z - sizes.z / 2
        )
    }

    override fun color(coords: Vector3d): ColorValue = color
}
package objects

import graphics.ColorValue
import math.Vector3d

object NullObject : Object3D() {
    override var position: Vector3d = Vector3d.empty

    override fun getDist(vec: Vector3d): Double = Double.POSITIVE_INFINITY

    override fun color(coords: Vector3d): ColorValue = ColorValue.black

}
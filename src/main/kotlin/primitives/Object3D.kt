package primitives

import graphics.ColorValue
import graphics.material.Material
import math.Vector3d

abstract class Object3D {
    abstract var position: Vector3d
    abstract fun getDist(vec: Vector3d): Double
    abstract fun color(coords: Vector3d): ColorValue
    open val isLightVisible = true
    var material: Material? = null
}
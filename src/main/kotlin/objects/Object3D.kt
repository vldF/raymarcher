package objects

import math.Vector3d

abstract class Object3D {
    abstract var position: Vector3d
    abstract fun getDist(vec: Vector3d): Double
}
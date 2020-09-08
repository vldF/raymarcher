package objects

import math.Vector3d

class Sphere(
        override var position: Vector3d,
        val radius: Double
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        return vec.dist(position) - radius
    }

}
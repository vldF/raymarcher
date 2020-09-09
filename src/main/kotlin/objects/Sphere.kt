package objects

import math.Vector3d
import java.lang.Double.max

class Sphere(
        override var position: Vector3d,
        val radius: Double
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        return vec.dist(position) - radius
    }

    override fun getMaxDist(vec: Vector3d): Double {
        return vec.dist(position) + radius
    }

}
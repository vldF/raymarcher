package primitives.operations

import graphics.ColorValue
import math.Vector3d
import primitives.NullObject
import primitives.Object3D

class Difference : Operation() {
    override fun getDist(vec: Vector3d): Double {
        return getDistColor(vec).first
    }

    override fun color(coords: Vector3d): ColorValue {
        return getDistColor(coords).second.color(coords)
    }

    private fun getDistColor(vec: Vector3d): Pair<Double, Object3D> {
        val first = children.first()
        var dist = -first.getDist(vec)
        var nearPrimitive = first
        for (obj in children) {
            if (obj == first) continue
            val newDist = obj.getDist(vec)
            if (newDist > dist) {
                dist = newDist
                nearPrimitive = obj
            }
        }

        return dist to nearPrimitive
    }
}
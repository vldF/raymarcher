package objects

import graphics.ColorValue
import math.Vector3d

class Torus(
    override var position: Vector3d,
    var sizes: Vector3d,
    var rotation: Vector3d,
    private val colorValue: ColorValue = ColorValue.white
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localCords = (vec-position).rotate(rotation.x, rotation.y, rotation.z)
        val q = Vector3d(localCords.xz.abs - sizes.x, localCords.y, 0.0)
        return q.abs - sizes.y
    }

    override fun color(coords: Vector3d): ColorValue = colorValue
}

class XTorus(
    override var position: Vector3d,
    var sizes: Vector3d,
    private val colorValue: ColorValue = ColorValue.white
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localCords = vec-position
        val q = Vector3d(localCords.xz.abs - sizes.x, localCords.y, 0.0)
        return q.abs - sizes.y
    }

    override fun color(coords: Vector3d): ColorValue = colorValue
}

class YTorus(
    override var position: Vector3d,
    var sizes: Vector3d,
    private val colorValue: ColorValue = ColorValue.white
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localCords = vec-position
        val q = Vector3d(localCords.z, 0.0, localCords.xy.abs - sizes.x)
        return q.abs - sizes.y
    }

    override fun color(coords: Vector3d): ColorValue = colorValue
}

class ZTorus(
    override var position: Vector3d,
    var sizes: Vector3d,
    private val colorValue: ColorValue = ColorValue.white
) : Object3D() {
    override fun getDist(vec: Vector3d): Double {
        val localCords = vec-position
        val q = Vector3d(localCords.yz.abs - sizes.x, 0.0, localCords.x)
        return q.abs - sizes.y
    }

    override fun color(coords: Vector3d): ColorValue = colorValue
}
package primitives

import graphics.ColorValue
import math.Vector3d

class LightDebug(light: Vector3d) : Object3D() {
    override var position: Vector3d = light

    override fun getDist(vec: Vector3d): Double = position.dist(vec) - 1

    override fun color(coords: Vector3d): ColorValue = ColorValue.red

    override val isLightVisible = false
}
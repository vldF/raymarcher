package objects

import graphics.ColorValue
import math.Vector3d
import kotlin.math.floor

class Floor(height: Double) : Object3D() {
    private val cellSize = 3
    override var position: Vector3d = Vector3d(0.0, 0.0, height)

    override fun getDist(vec: Vector3d): Double = vec.z - position.z

    override fun color(coords: Vector3d): ColorValue {
        val abscissaPeriodIndex = floor(coords.x / (2 * cellSize))
        val abscissa = coords.x <= 2 * cellSize * abscissaPeriodIndex + cellSize

        val ordinatePeriodIndex = floor(coords.y / (2 * cellSize))
        val ordinate = coords.y <= 2 * cellSize * ordinatePeriodIndex + cellSize

        return if (abscissa == ordinate) {
            ColorValue(80, 80 ,80)
        } else {
            ColorValue(58, 58, 58)
        }
    }

}
package math

import graphics.ColorValue
import objects.Camera
import objects.Object3D

class RayMarcher(
        private val camera: Camera,
        private val objects: List<Object3D>
) {
    private val light = Vector3d(0.0, .0, -1.0).normalize
    private val grid = camera.pixelGrid

    fun getPixels(): IntArray {
        val res = IntArray(camera.gridHeight * camera.gridWidth * 3)
        var pixelIndex = 0
        for (y in 0 until camera.gridHeight) {
            for (x in 0 until camera.gridWidth) {
                val pixel = grid.getPixel(x, y)
                val color = getColor(pixel)
                res[pixelIndex++] = color.rInt
                res[pixelIndex++] = color.gInt
                res[pixelIndex++] = color.bInt
            }
        }

        return res
    }

    private fun getColor(pixel: Vector3d): ColorValue {
        val max = 12
        var steps = 0
        val currentCoords = Vector3d(pixel)
        var dist = Double.POSITIVE_INFINITY
        var distFromStart = 0.0

        val dir = (pixel - camera.coords)
        dir.normalize()

        while (dist >= 0.01 && steps < max) {
            val newDist = objects.getDistanceToClosed(currentCoords)
            if (newDist > dist) return ColorValue.black  // it isn't work for scene with 1+ object

            dist = newDist
            distFromStart += dist

            //currentCoords += dir * dist, optimization
            currentCoords.add(dir * dist)

            steps++
        }

        val light = light.dot(calculateLightVector(currentCoords).normalize)
        val closedColor = objects.getClosed(currentCoords).color(currentCoords).vector
        val result = mix(closedColor, Vector3d( 0.0, 0.0, 0.0), light)
        return ColorValue(result)
    }

    private fun List<Object3D>.getDistanceToClosed(coords: Vector3d): Double {
        return this.minOfOrNull { it.getDist(coords) } ?: 0.0
    }

    private fun List<Object3D>.getClosed(coords: Vector3d): Object3D {
        return this.minByOrNull { it.getDist(coords) }!!
    }

    private fun calculateLightVector(ray: Vector3d): Vector3d {
        return Vector3d(
                objects.getDistanceToClosed(ray + eps1) - objects.getDistanceToClosed(ray - eps1),
                objects.getDistanceToClosed(ray + eps2) - objects.getDistanceToClosed(ray - eps2),
                objects.getDistanceToClosed(ray + eps3) - objects.getDistanceToClosed(ray - eps3)
        ).normalize
    }

    companion object {
        private const val eps = 0.001
        private val eps1 = Vector3d(eps, 0.0, 0.0)
        private val eps2 = Vector3d(0.0, eps, 0.0)
        private val eps3 = Vector3d(0.0, 0.0, eps)
    }
}
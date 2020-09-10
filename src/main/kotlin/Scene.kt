import graphics.ColorValue
import math.Vector3d
import math.mix
import objects.Camera
import objects.Object3D

class Scene(private val rows: Int, private val cols: Int) {
    private val camera = Camera(Vector3d.empty, Vector3d(1.0, 0.0, 0.0))

    private val objects: MutableList<Object3D> = mutableListOf()
    private val grid = camera.pixelGrid

    private val light = Vector3d(0.0, .0, -1.0).normalize
    private val eps = 0.001
    private val eps1 = Vector3d(eps, 0.0, 0.0)
    private val eps2 = Vector3d(0.0, eps, 0.0)
    private val eps3 = Vector3d(0.0, 0.0, eps)

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): IntArray {
        val res = IntArray(rows * cols * 3)
        var pixelIndex = 0
        for (y in 0 until rows) {
            for (x in 0 until cols) {
                val pixel = grid.getPixel(x, y)
                val color = getColor(pixel)
                res[pixelIndex++] = color.r
                res[pixelIndex++] = color.g
                res[pixelIndex++] = color.b
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

    private fun MutableList<Object3D>.getDistanceToClosed(coords: Vector3d): Double {
        return this.minOfOrNull { it.getDist(coords) } ?: 0.0
    }

    private fun MutableList<Object3D>.getClosed(coords: Vector3d): Object3D {
        return this.minByOrNull { it.getDist(coords) }!!
    }

    private fun calculateLightVector(ray: Vector3d): Vector3d {
        return Vector3d(
                objects.getDistanceToClosed(ray + eps1) - objects.getDistanceToClosed(ray - eps1),
                objects.getDistanceToClosed(ray + eps2) - objects.getDistanceToClosed(ray - eps2),
                objects.getDistanceToClosed(ray + eps3) - objects.getDistanceToClosed(ray - eps3)
        ).normalize
    }

}
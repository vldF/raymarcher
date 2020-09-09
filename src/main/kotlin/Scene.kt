import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(private val rows: Int, private val cols: Int) {
    private val camera = Camera(Vector3d.empty, Vector3d(1.0, 0.0, 0.0))
    private val objects: MutableList<Object3D> = mutableListOf()
    private val maxDist = 50

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): IntArray {
        val res = IntArray(rows * cols)
        for ((i, pixel) in camera.virtualPixels.withIndex()) {
            res[i] = getColor(pixel)
        }

        return res
    }

    fun getColor(pixel: Vector3d): Int {
        val max = 12
        var steps = 0
        var currentCoords = pixel
        var dist = Double.POSITIVE_INFINITY
        var distFromStart = 0.0

        val dir = (pixel - camera.coords).normalize

        while (dist >= 0.01 && steps < max) {
            dist = objects.getDistanceToClosed(currentCoords)
            if (dist > maxDist) return 0
            distFromStart += dist
            currentCoords += pixel + dir * dist
            steps++
        }

        return (distFromStart * 1000).toInt()
    }

    private fun MutableList<Object3D>.getDistanceToClosed(coords: Vector3d): Double {
        return this.minOfOrNull { it.getDist(coords) } ?: 0.0
    }

    private fun MutableList<Object3D>.getMaxDistance(coords: Vector3d): Double {
        return this.maxOfOrNull { it.getDist(coords) } ?: 0.0
    }

}
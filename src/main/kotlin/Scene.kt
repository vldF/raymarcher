import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(private val rows: Int, private val cols: Int) {
    private val camera = Camera(Vector3d.empty, Vector3d(1.0, 0.0, 0.0))

    private val objects: MutableList<Object3D> = mutableListOf()
    private val maxDist = 50
    private val grid = camera.pixelGrid

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): IntArray {
        val res = IntArray(rows * cols)
        var pixelIndex = 0
        for (y in 0 until rows) {
            for (x in 0 until cols) {
                val pixel = grid.getPixel(x, y)
                res[pixelIndex++] = getColor(pixel)
            }
        }

        return res
    }

    private fun getColor(pixel: Vector3d): Int {
        val max = 12
        var steps = 0
        val currentCoords = Vector3d(pixel)
        var dist = Double.POSITIVE_INFINITY
        var distFromStart = 0.0

        val dir = (pixel - camera.coords)
        dir.normalize()

        while (dist >= 0.001 && steps < max) {
            val newDist = objects.getDistanceToClosed(currentCoords)
            if (newDist > dist) return 0

            dist = newDist
            distFromStart += dist

            //currentCoords += pixel + dir * dist
            currentCoords.add(pixel)
            currentCoords.add(dir * dist)

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
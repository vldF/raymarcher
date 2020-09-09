import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(private val rows: Int, private val cols: Int) {
    private val camera = Camera(Vector3d.empty, Vector3d(0.0, 0.0, 1.0))
    private val objects: MutableList<Object3D> = mutableListOf()

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): ByteArray {
        val res = ByteArray(rows * cols)
        for ((i, pixel) in camera.virtualPixels.withIndex()) {
            res[i] = getColor(pixel)
        }

        return res
    }

    fun getColor(pixel: Vector3d): Byte {
        val max = 12
        var dist = Double.POSITIVE_INFINITY
        var steps = 0
        var currentCoords = pixel

        val dir = (pixel - camera.coords).normalize

        while (dist >= 0.0001 && steps < max) {
            dist = objects.getDistanceToClosed(currentCoords)
            currentCoords = camera.coords + dir * dist
            steps++
        }

        if (steps >= max) return 0
        return 64
    }

    private fun MutableList<Object3D>.getDistanceToClosed(coords: Vector3d): Double {
        return this.minByOrNull { it.getDist(coords) }?.getDist(coords) ?: -1.0
    }

}
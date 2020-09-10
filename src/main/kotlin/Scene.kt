import math.RayMarcher
import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(rows: Int, cols: Int) {
    private val camera = Camera(Vector3d.empty, Vector3d(1.0, 0.0, 0.0), rows, cols)

    private val objects: MutableList<Object3D> = mutableListOf()
    private val rayMarcher = RayMarcher(camera, objects)

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): IntArray = rayMarcher.getPixels()
}
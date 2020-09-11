import math.RayMarcher
import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(val camera: Camera) {
    private val objects: MutableList<Object3D> = mutableListOf()
    private val rayMarcher = RayMarcher(camera, objects)

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(): IntArray = rayMarcher.getPixels()
}
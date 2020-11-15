import math.RayMarcher
import math.Vector3d
import objects.Camera
import objects.Object3D

class Scene(val camera: Camera) {
    val objects: MutableList<Object3D> = mutableListOf()
    private val rayMarcher = RayMarcher(camera, objects)
    val cols
        get() = camera.gridWidth
    val rows
        get() = camera.gridHeight

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun getPixels(
        leftX: Int,
        leftY: Int,
        rightX: Int,
        rightY: Int
    ): IntArray = rayMarcher.getPixels(leftX, leftY, rightX, rightY)
}
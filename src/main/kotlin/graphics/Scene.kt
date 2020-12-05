package graphics

import math.RayMarcher
import math.Vector3d
import primitives.Camera
import primitives.LightDebug
import primitives.Object3D

class Scene(
        val camera: Camera,
        private val lightDebug: Boolean = false
) {
    val objects: MutableList<Object3D> = mutableListOf()
    val lights = mutableListOf<Vector3d>()

    private val rayMarcher = RayMarcher(camera, objects, lights)

    val cols
        get() = camera.gridWidth
    val rows
        get() = camera.gridHeight

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun addLight(v: Vector3d) {
        lights.add(v)

        if (lightDebug) {
            objects.add(LightDebug(v))
        }
    }

    fun getPixels(
        leftX: Int,
        leftY: Int,
        rightX: Int,
        rightY: Int
    ): IntArray = rayMarcher.getPixels(leftX, leftY, rightX, rightY)
}
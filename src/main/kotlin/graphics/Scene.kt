package graphics

import graphics.light.Light
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
    val lights = mutableListOf<Light>()

    private val rayMarcher = RayMarcher(camera, objects, lights)

    val cols
        get() = camera.gridWidth
    val rows
        get() = camera.gridHeight

    fun addObject(o: Object3D) {
        objects.add(o)
    }

    fun addLight(light: Light) {
        lights.add(light)

        if (lightDebug) {
            objects.add(LightDebug(light.position))
        }
    }

    fun getPixels(
        leftX: Int,
        leftY: Int,
        rightX: Int,
        rightY: Int
    ): IntArray = rayMarcher.getPixels(leftX, leftY, rightX, rightY)
}
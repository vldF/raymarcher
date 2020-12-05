package demos

import graphics.Render
import graphics.Scene
import math.Vector3d
import primitives.Camera
import primitives.Floor
import primitives.Sphere

fun main() {
    val sceneCamera = Camera(
            Vector3d(0.0, 2.0, 5.0),
            Vector3d(0.01, 0.0, -0.7),
            300,
            300
    )
    val scene = Scene(sceneCamera)
    scene.addObject(Floor(0.0))
    scene.addObject(Sphere(Vector3d(0.0, 0.0, 5.0), 3.0))
    scene.addLight(Vector3d(0.0, -1.0, 10.0))
    val render = Render(scene)
    render.loop()
}
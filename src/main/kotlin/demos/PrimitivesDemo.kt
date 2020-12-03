package demos

import Render
import Scene
import math.Vector3d
import objects.Camera
import objects.Floor
import objects.Sphere

fun main() {
    val sceneCamera = Camera(
            Vector3d(-15.0, 0.0, 15.0),
            Vector3d(2.0, 0.0, -1.0),
            300,
            300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(Floor(0.0))
    scene.addObject(Sphere(Vector3d(5.0, 0.0, 4.0), 8.0))
    scene.addObject(Sphere(Vector3d(0.0, 0.0, 0.0), 10.0))

    val render = Render(scene)
    render.runBeforeEvertFrame = {

    }
    render.loop()
}
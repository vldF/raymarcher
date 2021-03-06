package demos

import graphics.Render
import graphics.Scene
import math.Vector3d
import primitives.Camera
import primitives.CapedCylinder
import primitives.Sphere

fun main() {
    val sceneCamera = Camera(
            Vector3d(-15.0, 0.0, 4.0),
            Vector3d(4.0, 0.0, -1.0),
            300,
            300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(CapedCylinder(
            Vector3d(0.0, 0.0, 0.0),
            0.1,
            5.0
    ))
    scene.addObject(Sphere(
            Vector3d(0.0, 0.0, 0.0),
            3.0
    ))

    val render = Render(scene)
    render.runBeforeEvertFrame = {

    }
    render.loop()
}
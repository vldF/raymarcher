package demos

import graphics.Render
import graphics.Scene
import math.Vector3d
import primitives.Camera
import primitives.CapedCylinder

fun main() {
    val sceneCamera = Camera(
            Vector3d(-15.0, 0.0, 4.0),
            Vector3d(4.0, 0.0, -2.0),
            300,
            300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(CapedCylinder(
            Vector3d(0.0, 0.0, -2.0),
            5.0,
            2.5
    ))

    val render = Render(scene)
    render.loop()
}
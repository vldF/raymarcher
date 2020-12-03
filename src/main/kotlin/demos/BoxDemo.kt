package demos

import Render
import Scene
import math.Vector3d
import primitives.Box
import primitives.Camera
import primitives.CapedCylinder

fun main() {
    val sceneCamera = Camera(
            Vector3d(4.0, 2.0, 4.0),
            Vector3d(-1.0, 0.0, -1.0),
            300,
            300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(Box(
            Vector3d.zero,
            Vector3d(3.0, 4.0, 5.0)
    ))

    val render = Render(scene)
    render.loop()
}
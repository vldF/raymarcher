package demos

import Render
import Scene
import math.Vector3d
import primitives.Box
import primitives.Camera
import primitives.CapedCylinder
import primitives.Sphere
import primitives.operations.Difference
import kotlin.math.pow

fun main() {
    val sceneCamera = Camera(
            Vector3d(0.0, -8.0, 0.0),
            Vector3d(0.0, 1.0, 0.0),
            900,
            900
    )
    val scene = Scene(sceneCamera)
    val difference = Difference()

    scene.addObject(difference)
    difference.add(
            Box(Vector3d.zero, Vector3d(11.0, 11.0, 22.0))
    )
    difference.add(
            Sphere(Vector3d.zero, 8.0)
    )

    for (i in 0..7) {
        difference.add(
                Sphere(Vector3d((-1.0).pow(i / 1) * 3.2, (-1.0).pow(i / 2) * -3.2, (-1.0).pow(i / 4) * 8.6), 3.0)
        )
    }

    for (i in 0..2) {
        difference.add(
                Sphere(Vector3d(0.0, (-1.0).pow(i / 1) * -5.2, (-1.0).pow(i / 2) * 9.8), 1.3)
        )
    }

    val render = Render(scene)
    render.loop()
}
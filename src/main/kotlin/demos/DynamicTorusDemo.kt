package demos

import Render
import Scene
import math.Vector3d
import primitives.*
import kotlin.math.PI

fun main() {
    val sceneCamera = Camera(
        Vector3d(-15.0, 6.0, 15.0),
        Vector3d(2.0, -0.5, -1.0),
        300,
        300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(Floor(0.0))
    scene.addObject(Torus(
        Vector3d(5.0, -9.0, 5.0),
        Vector3d(5.0, 2.0, 0.0),
        Vector3d(PI/2, PI/2, PI/2).normalize
    ))

    val render = Render(scene)
    render.runBeforeEvertFrame = {
        val torus = it.scene.objects[1] as Torus
        val angle = it.frame * (2 * PI) / 360
        torus.rotation.rotate(angle, 0.0, 0.0)
    }
    render.loop()
}
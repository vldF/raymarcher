package demos

import Render
import Scene
import math.Vector3d
import primitives.*

fun main() {
    val sceneCamera = Camera(
        Vector3d(-15.0, 6.0, 15.0),
        Vector3d(2.0, -0.5, -1.0),
        300,
        300
    )
    val scene = Scene(sceneCamera)

    scene.addObject(Floor(0.0))
    scene.addObject(XTorus(
        Vector3d(5.0, -9.0, 5.0),
        Vector3d(5.0, 2.0, 0.0)
    ))
    scene.addObject(YTorus(
        Vector3d(5.0, 0.0, 5.0),
        Vector3d(5.0, 2.0, 0.0)
    )
    )
    scene.addObject(ZTorus(
        Vector3d(5.0, 5.0, 5.0),
        Vector3d(5.0, 2.0, 0.0)
    ))

    val render = Render(scene)
    render.runBeforeEvertFrame = {

    }
    render.loop()
}
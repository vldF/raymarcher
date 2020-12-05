package demos

import graphics.Render
import graphics.Scene
import math.Vector3d
import primitives.Camera
import primitives.Floor

fun main() {
    val sceneCamera = Camera(
            Vector3d(0.0, 0.0, 10.0),
            Vector3d(0.01, 0.0, -1.0),
            300,
            300
    )
    val scene = Scene(sceneCamera)
    scene.addObject(Floor(0.0))
    val render = Render(scene)
    render.runBeforeEvertFrame = {
        val camera = it.scene.camera
        camera.direction = camera.direction.rotate(0.0, 0.0, 0.00000001)
    }
    render.loop()
}
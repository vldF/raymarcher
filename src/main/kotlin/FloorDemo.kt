import math.Vector3d
import objects.Floor
import kotlin.math.sin

fun main() {
    val scene = Scene(300, 300)
    scene.addObject(Floor(0.0))
    val render = Render(scene)
    val camera = scene.camera
    render.runBeforeEvertFrame = {

        camera.direction = camera.direction + Vector3d(1.0, 0.01, 0.0)
    }
    render.loop()
}
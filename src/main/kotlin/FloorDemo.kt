import objects.Floor

fun main() {
    val scene = Scene(300, 300)
    scene.addObject(Floor(0.0))
    val render = Render(scene)
    render.runBeforeEvertFrame = {
        val camera = it.scene.camera
        camera.direction = camera.direction.rotate(0.0, 0.0, 0.00000001)
    }
    render.loop()
}
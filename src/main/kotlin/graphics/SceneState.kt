package graphics

class SceneState(val scene: Scene) {
    private val timeStart = System.currentTimeMillis()
    var frame = 0
    val timeMillis
        get() = System.currentTimeMillis() - timeStart
}
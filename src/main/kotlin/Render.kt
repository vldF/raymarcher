import javax.swing.JFrame

class Render(
        scene: Scene
) : JFrame("Ray Marcher") {
    private val view = View(scene)
    private val state = SceneState(scene)
    var runBeforeEvertFrame: (SceneState) -> Unit = {}

    init {
        setSize(scene.cols, scene.rows)
        isVisible = true
        contentPane.add(view)
    }

    fun loop() {
        while (true) {
            state.run(runBeforeEvertFrame)
            view.paintImmediately(0, 0, view.width, view.height)
            updateState()
        }
    }

    private fun updateState() {
        state.frame++
    }
}
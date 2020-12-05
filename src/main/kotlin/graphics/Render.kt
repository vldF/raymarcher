package graphics

import graphics.views.View
import graphics.views.WindowView

class Render(
        scene: Scene,
        private val view: View = WindowView(scene)
) {
    private val state = SceneState(scene)
    var runBeforeEvertFrame: (SceneState) -> Unit = {}

    fun loop() {
        while (true) {
            state.run(runBeforeEvertFrame)
            view.computeFrame()
            updateState()
        }
    }

    private fun updateState() {
        state.frame++
    }
}
package graphics.views

import graphics.Scene
import java.awt.image.BufferedImage
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO

class ImageSeqView(
        private val scene: Scene
) : View {
    private val img = BufferedImage(scene.cols, scene.rows, BufferedImage.TYPE_INT_RGB)
    private var frameNumber = 0
    private val dateFormatter = DateTimeFormatter.ISO_DATE_TIME
    private val dirName = dateFormatter.format(LocalDateTime.now())

    init {
        File(dirName).mkdir()
    }

    override fun computeFrame() {
        val timeStart = System.currentTimeMillis()
        val pixels = scene.getPixels(0, 0, scene.cols, scene.cols)
        img.raster.setPixels(0, 0, scene.cols, scene.cols, pixels)
        val file = File("$dirName/frame_$frameNumber.png")
        ImageIO.write(img, "png", file)
        frameNumber++

        println("Time of frame[$frameNumber]: ${System.currentTimeMillis() - timeStart}")
    }
}
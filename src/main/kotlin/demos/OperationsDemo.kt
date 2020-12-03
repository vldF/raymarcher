package demos

import Render
import Scene
import graphics.ColorValue
import math.Vector3d
import primitives.Camera
import primitives.CapedCylinder
import primitives.Sphere
import primitives.YTorus
import primitives.operations.Difference
import primitives.operations.Intersection
import primitives.operations.Union
import kotlin.math.abs
import kotlin.math.sin

fun main() {
    val sceneCamera = Camera(
            Vector3d(0.0, 5.0, 0.0),
            Vector3d(0.01, -1.0, 0.0),
            300,
            300
    )

    val scene = Scene(sceneCamera)
    val union = Union()
    val intersection = Intersection()
    val difference = Difference()

    scene.addObject(union)
    scene.addObject(intersection)
    scene.addObject(difference)

    union.add(Sphere(Vector3d(-5.0, 2.0, 0.0), 2.0))
    union.add(Sphere(Vector3d(-4.5, 2.0, 2.0), 1.0, color = ColorValue.red))

    intersection.add(Sphere(Vector3d(0.0, 4.0, 0.0), 2.0))
    intersection.add(YTorus(Vector3d(0.0, 4.0, 2.0), Vector3d(2.0, 0.3, 0.0), colorValue = ColorValue.red))

    difference.add(Sphere(Vector3d(5.0, 0.0, 0.0), 1.0, color = ColorValue.white))
    difference.add(CapedCylinder(Vector3d(5.0, 0.0, 0.0), 7.0, 1.5))


    val render = Render(scene)
    render.runBeforeEvertFrame = {
        val sphere = ((scene.objects[2] as Difference).children[0] as Sphere)
        sphere.radius = abs(sin(it.frame * 1.0 / 30) * (3.5 - 1.5) + 1.5)

        val torus = ((scene.objects[1] as Intersection).children[1] as YTorus)
        torus.position.x += sin(it.frame * 1.0 / 10)
    }
    render.loop()
}
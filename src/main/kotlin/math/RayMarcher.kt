package math

import graphics.ColorValue
import graphics.light.Light
import graphics.material.Material
import primitives.Camera
import primitives.NullObject
import primitives.Object3D
import java.lang.Integer.max
import kotlin.math.pow

class RayMarcher(
        private val camera: Camera,
        private val objects: List<Object3D>,
        private val lights: List<Light> = listOf(),
) {
    private val globalLight = Vector3d(0.0, 0.0, 100.0)
    private val maxSteps = 64
    private val maxShadowSteps = 32

    fun getPixels(
        leftX: Int,
        leftY: Int,
        rightX: Int,
        rightY: Int
    ): IntArray {
        val grid = camera.pixelGrid
        val res = IntArray((rightX - leftX) * (rightX - leftX) * 3)
        var pixelIndex = 0
        for (y in leftY until rightY) {
            for (x in leftX until rightX) {
                val pixel = grid.getPixel(x, y)
                val color = getColor(pixel)
                res[pixelIndex++] = color.rInt
                res[pixelIndex++] = color.gInt
                res[pixelIndex++] = color.bInt
            }
        }

        return res
    }

    private fun getColor(pixel: Vector3d): ColorValue {
        var steps = 0
        val currentCoords = Vector3d(pixel) + camera.position
        var dist = Double.POSITIVE_INFINITY
        var distFromOrigin = 0.0

        val dir = pixel - camera.position
        dir.normalize()

        while (dist > 0.0001 && steps < maxSteps) {
            dist = getDistanceToNearest(currentCoords)
            distFromOrigin += dist

            //currentCoords += dir * dist, optimization
            currentCoords.add(dir * dist)

            steps++
        }

        if (dist > 0.0001) return ColorValue.black

        val nearestObject = objects.getClosed(currentCoords)

        val light = nearestObject.material?.calculatePhongReflection(currentCoords) ?: 0.0
        val shadow = getShadow(currentCoords, nearestObject)
        val nearestColor = nearestObject.color(currentCoords).vector

        //val result = mix(nearestColor, Vector3d(127.0, 127.0, 127.0), (-shadow+light))
        return ColorValue(
            127.coerceAtMost(max(0, (nearestColor.x * shadow + light).toInt())).toByte(),
            127.coerceAtMost(max(0, (nearestColor.y * shadow + light).toInt())).toByte(),
            127.coerceAtMost(max(0, (nearestColor.z * shadow + light).toInt())).toByte()
        )
    }

    private fun getShadow(where: Vector3d, from: Object3D): Double {
        if (lights.isEmpty()) return 1.0
        var lightValue = 0.0
        for (lightObj in lights) {
            val lightPosition = lightObj.position
            var steps = 0
            val currentCoords = Vector3d(where)
            var dist: Double

            val dir = lightPosition - where
            dir.normalize()
            currentCoords.add(dir * 0.1)

            do {
                dist = getDistanceAndNearest(currentCoords, isLightCalculation = true).second
                currentCoords.add(dir * dist)
                steps++
            } while (dist > 0.00001 && steps < maxShadowSteps)
            if (steps == maxShadowSteps) {
                lightValue += 1
            }
        }

        return lightValue / lights.size
    }

    private fun Material.calculatePhongReflection(point: Vector3d): Double {
        val normal = calculateSDFGradient(point)
        val cameraDirection = (camera.position - point).normalize

        return ambient + lights.sumByDouble {
            val lightDirection = (it.position - point).normalize
            val lightDirMulN = lightDirection.dot(normal)
            val reflection = normal * (2 * lightDirMulN) - lightDirection

            (diffuse * (lightDirMulN) * it.diffusivity + ((reflection.dot(cameraDirection)) * specular).pow(shininess)*it.intensity)
        } / lights.size
    }

    private fun getDistanceToNearest(coords: Vector3d, except: Object3D? = null): Double {
        return getDistanceAndNearest(coords, except).second
    }

    private fun getDistanceAndNearest(
            coords: Vector3d,
            except: Object3D? = null,
            isLightCalculation: Boolean = false
    ): Pair<Object3D?, Double> {
        var nearest: Object3D? = except
        var dist = Double.POSITIVE_INFINITY

        for (obj in objects) {
            if (obj == except) continue
            if ((isLightCalculation && !obj.isLightVisible)) continue

            val newDist = obj.getDist(coords)
            if (newDist < dist) {
                dist = newDist
                nearest = obj
            }
        }

        return nearest to dist
    }

    private fun List<Object3D>.getClosed(coords: Vector3d): Object3D {
        return this.minByOrNull { it.getDist(coords) } ?: NullObject
    }

    private fun calculateSDFGradient(point: Vector3d): Vector3d {
        val dist = getDistanceToNearest(point)
        return Vector3d(
                dist - getDistanceToNearest(point - eps1),
                dist - getDistanceToNearest(point - eps2),
                dist - getDistanceToNearest(point - eps3)
        ).normalize
    }

    companion object {
        private const val eps = 0.00001
        private val eps1 = Vector3d(eps, 0.0, 0.0)
        private val eps2 = Vector3d(0.0, eps, 0.0)
        private val eps3 = Vector3d(0.0, 0.0, eps)
    }
}
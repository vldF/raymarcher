package graphics.light

import math.Vector3d

data class Light (
        val position: Vector3d,
        val intensity: Double = 1.0,
        val diffusivity: Double = 1.0
)
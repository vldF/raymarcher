package graphics.material

interface Material {
    val specular: Double
    val diffuse: Double
    val ambient: Double
    val shininess: Double

    val ambientLightning: Double
}
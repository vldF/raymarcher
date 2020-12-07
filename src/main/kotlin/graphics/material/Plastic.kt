package graphics.material

class Plastic : Material {
    override val specular: Double = 3.0
    override val diffuse: Double = 3.0
    override val ambient: Double = 1.0
    override val shininess: Double = 10.0
    override val ambientLightning: Double = 1.0
}
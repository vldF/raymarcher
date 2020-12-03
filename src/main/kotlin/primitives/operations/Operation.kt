package primitives.operations

import math.Vector3d
import primitives.Object3D

abstract class Operation : Object3D() {
    internal val children = mutableListOf<Object3D>()

    override var position = Vector3d.zero

    fun add(primitive: Object3D) = children.add(primitive)
}
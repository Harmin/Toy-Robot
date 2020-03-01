package com.myxplor.toyrobot.model

open class CommandParams

data class PlaceCommandParams(val params: String?) : CommandParams() {
    var positionX: Int? = null
    var positionY: Int? = null
    var direction: Direction? = null

    init {
        params?.let {
            it.trim().split(",").let { paramArray ->
                if (paramArray.size == 3) {
                    positionX = paramArray[0].toIntOrNull()
                    positionY = paramArray[1].toIntOrNull()
                    direction = Direction.values().find { direction -> direction.name == paramArray[2] }
                }
            }
        }
    }

    fun isValid() : Boolean{
        return !(positionX == null || positionY == null || direction == null)
    }

    override fun toString(): String {
        return "$positionX, $positionY, ${direction?.name}"
    }
}
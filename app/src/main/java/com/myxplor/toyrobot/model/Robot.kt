package com.myxplor.toyrobot.model

import kotlin.math.abs

class Robot(private var posX: Int, private var posY: Int, private var direction: Direction){

    fun move(noOfUnits: Int){
        val next = getNextPoint(noOfUnits)
        if(isWithinAvailableSpace(next.X, next.Y)){
            posX = next.X
            posY = next.Y
        }
    }

    fun rotate(rotateTowards: Int){
        if(rotateTowards == rotateClockwise){
            turn(1)
        } else {
            turn(3)
        }
    }

    private fun isWithinAvailableSpace(x: Int, y: Int): Boolean {
        return (abs(x) <= maxX && abs(y) <= maxY)
    }

    // get the next X,Y coordinates of the robot assuming it can move forward
    private fun getNextPoint(noOfUnits: Int): Position {
        return when (direction) {
            Direction.NORTH -> Position(posX, posY + noOfUnits)
            Direction.EAST -> Position(posX + noOfUnits, posY)
            Direction.SOUTH -> Position(posX, posY - noOfUnits)
            Direction.WEST -> Position(posX - noOfUnits, posY)
        }
    }

    // Always turning robot to 90 degree clockwise
    private fun turn(noOf90DegreeSteps: Int) {
        var value = (direction.value + noOf90DegreeSteps) % 4
        direction = Direction.values().find { it.value == value } ?: direction
    }

    override fun toString(): String {
        return "Output: $posX,$posY,${direction.name}"
    }


    data class Position(val X:Int, val Y:Int)

    companion object {
        const val rotateClockwise = 0
        const val rotateAntiClockwise = 1

        const val originX = 0
        const val originY = 0
        const val dimension = 5
        const val unitsToMove = 1

        const val maxX = originX + dimension
        const val maxY = originY + dimension
    }
}
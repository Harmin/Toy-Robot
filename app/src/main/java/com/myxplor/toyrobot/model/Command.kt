package com.myxplor.toyrobot.model

data class Command(val commandName: RobotCommand,
                   val params: String?) {

    val commandParams:CommandParams? = if(commandName == RobotCommand.PLACE) PlaceCommandParams(params) else null

    override fun toString(): String {
        return "${commandName} ${commandParams?.toString() ?: ""}"
    }
}


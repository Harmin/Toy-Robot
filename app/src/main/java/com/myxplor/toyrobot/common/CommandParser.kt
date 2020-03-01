package com.myxplor.toyrobot.common

import com.myxplor.toyrobot.model.Command
import com.myxplor.toyrobot.model.RobotCommand
import java.lang.Exception

class CommandParser {

    fun execute(commandString: String?) = commandString?.let {
        val cmd = it.trim().toUpperCase()
        val commandName: RobotCommand?
        val commandParams: String?

        val firstSpaceIndex = cmd.indexOf(" ")
        if(firstSpaceIndex == -1){
            commandName = RobotCommand.values().find { robotCommand -> robotCommand.name == cmd }
            commandParams = null
        } else {
            commandName = RobotCommand.values().find { robotCommand -> robotCommand.name == cmd.substring(0, firstSpaceIndex) }
            commandParams = cmd.substring(firstSpaceIndex+1)
        }
        commandName?.let { cmdName -> Command(cmdName, commandParams) }
    }
}
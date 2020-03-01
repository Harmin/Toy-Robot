package com.myxplor.toyrobot.common

import com.myxplor.toyrobot.model.Command
import com.myxplor.toyrobot.model.PlaceCommandParams

class ValidateCommandUseCase {

    fun validate(command: Command?) : Boolean {
        return command?.let {
            if(it.commandParams is PlaceCommandParams) it.commandParams.isValid() else it.commandParams == null
        } ?: false
    }
}
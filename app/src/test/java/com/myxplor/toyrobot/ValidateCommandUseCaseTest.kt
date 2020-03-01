package com.myxplor.toyrobot

import com.myxplor.toyrobot.common.CommandParser
import com.myxplor.toyrobot.common.ValidateCommandUseCase
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ValidateCommandUseCaseTest(private val commandString: String, private val expectedResult: Boolean) {

    @Test
    fun `test if the command entered is correct`() {
        val command = CommandParser().execute(commandString)
        val actual = ValidateCommandUseCase().validate(command)
        Assert.assertEquals(expectedResult, actual)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0} -> {1}")
        fun data(): Iterable<Array<out Any>> {
            return listOf(
                arrayOf("PLACE 0,0,NORTH", true),
                arrayOf("MOVE", true),
                arrayOf("REPORT", true),
                arrayOf("LEFT", true),
                arrayOf("RIGHT", true),
                arrayOf("place 1,2,east", true),
                arrayOf("move", true),
                arrayOf("left", true),
                arrayOf("report", true),
                arrayOf("right", true),
                arrayOf("move", true),
                arrayOf("report", true),

                arrayOf("PLACE,0,0,NORTH", false),
                arrayOf("TEST", false),
                arrayOf("place 0,0", false),
                arrayOf("PLACE 0,0,START", false),
                arrayOf("print", false)
            )
        }
    }

}
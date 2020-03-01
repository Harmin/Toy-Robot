package com.myxplor.toyrobot.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.myxplor.toyrobot.common.CommandParser
import com.myxplor.toyrobot.common.ValidateCommandUseCase
import com.myxplor.toyrobot.model.Command
import com.myxplor.toyrobot.model.PlaceCommandParams
import com.myxplor.toyrobot.model.Robot
import com.myxplor.toyrobot.model.RobotCommand

class MainViewModel : ViewModel() {
    val commandLog = MutableLiveData<ArrayList<String>>() // list of all commands entered
    val commandName = MutableLiveData("") // value input by user
    val robotPosition = MutableLiveData<String>(ROBOT_NOT_ON_TABLE) // value output to user
    val showCurrentPosition = MutableLiveData<Boolean>(false)

    private val robotTracker = MutableLiveData<Robot>()
    private val command = MutableLiveData<Command>()
    private val commandObserver = Observer<Command> {
        executeCommand(it)
        setCurrentPosition()
    }

    init {
        command.observeForever(commandObserver)
    }

    fun onExecuteClick() {
        showCurrentPosition.value = false
        val cmd: Command? = CommandParser().execute(commandName.value)
        if (ValidateCommandUseCase().validate(cmd)) {
            cmd?.let {
                command.value = it
                addCommandToLogList(it.toString())
            }
        } else {
            addCommandToLogList("invalid command")
        }
        commandName.value = ""
    }

    fun onClearLogClick(){
        commandLog.value = arrayListOf()
    }

    private fun addCommandToLogList(commandString: String) {
        val list =  arrayListOf<String>()
        commandLog.value?.let { it ->
            list.addAll(it)
        }
        list.add(commandString)
        commandLog.value = list
    }

    private fun executeCommand(command: Command) {
        when(command.commandName){
            RobotCommand.PLACE -> {
                placeRobotOnTable(command)
            }

            RobotCommand.MOVE -> {
                moveRobot(Robot.unitsToMove)
            }

            RobotCommand.LEFT -> {
                rotateRobot(Robot.rotateAntiClockwise)
            }

            RobotCommand.RIGHT -> {
                rotateRobot(Robot.rotateClockwise)
            }

            RobotCommand.REPORT -> {
                showCurrentPosition.value = true
            }
        }

    }

    private fun placeRobotOnTable(command: Command) {
        robotTracker.value = null
        val commandParams = command.commandParams
        if(commandParams is PlaceCommandParams){
            robotTracker.value =  Robot(commandParams.positionX!!, commandParams.positionY!!, commandParams.direction!!)
        }
    }

    private fun moveRobot(units: Int) {
        robotTracker.value?.move(units)
    }

    private fun rotateRobot(rotateTowards: Int) {
        robotTracker.value?.rotate(rotateTowards)
    }

    private fun setCurrentPosition() {
        robotPosition.value = robotTracker.value?.toString() ?: ROBOT_NOT_ON_TABLE
    }

    companion object{
        const val ROBOT_NOT_ON_TABLE = "Robot is not on table"
    }
}
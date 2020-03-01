package com.myxplor.toyrobot

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.myxplor.toyrobot.activities.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun `test place robot on table only after valid place command`() {

        Assert.assertTrue(viewModel.commandName.value.isNullOrEmpty())

        viewModel.commandName.value = "MOVE"
        viewModel.onExecuteClick()
        Assert.assertTrue(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)

        viewModel.commandName.value = "REPORT"
        viewModel.onExecuteClick()
        Assert.assertTrue(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)

        viewModel.commandName.value = "PLACE 0,0,NORTH"
        viewModel.onExecuteClick()
        Assert.assertFalse(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)
    }

    @Test
    fun `test first place command followed by move`() {

        viewModel.commandName.value = "PLACE 0,0,NORTH"
        viewModel.onExecuteClick()
        Assert.assertFalse(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)

        viewModel.commandName.value = "MOVE"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "REPORT"
        viewModel.onExecuteClick()
        Assert.assertTrue(viewModel.robotPosition.value == "Output: 0,1,NORTH")
    }

    @Test
    fun `test robot movement with left turn`() {

        viewModel.commandName.value = "PLACE 0,0,NORTH"
        viewModel.onExecuteClick()
        Assert.assertFalse(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)

        viewModel.commandName.value = "left"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "REPORT"
        viewModel.onExecuteClick()
        Assert.assertTrue(viewModel.robotPosition.value == "Output: 0,0,WEST")
    }

    @Test
    fun `test robot movement starting from east`() {

        viewModel.commandName.value = "PLACE 1,2,EAST"
        viewModel.onExecuteClick()
        Assert.assertFalse(viewModel.robotPosition.value == MainViewModel.ROBOT_NOT_ON_TABLE)

        viewModel.commandName.value = "MOVE"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "move"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "LEFT"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "MOVE"
        viewModel.onExecuteClick()

        viewModel.commandName.value = "REPORT"
        viewModel.onExecuteClick()
        Assert.assertTrue(viewModel.robotPosition.value == "Output: 3,3,NORTH")
    }
}
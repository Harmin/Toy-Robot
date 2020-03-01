package com.myxplor.toyrobot.di

import com.myxplor.toyrobot.common.CommandParser
import com.myxplor.toyrobot.common.ValidateCommandUseCase
import org.koin.dsl.module

val commonModule = module {
    factory { CommandParser() }
    factory { ValidateCommandUseCase() }
}
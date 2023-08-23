package di

import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.core.*

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule)
    }


val commonModule = module {
    single<ExpenseRepository> { ExpenseRepoImpl() }
}
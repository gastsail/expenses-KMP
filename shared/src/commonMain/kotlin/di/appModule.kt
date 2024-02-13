package di

import data.ExpenseManager
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun appModule() = module {
    single { ExpenseManager }.withOptions { createdAtStart() }
    single<ExpenseRepository> { ExpenseRepoImpl(get()) }
    factory { ExpensesViewModel(get()) }
}
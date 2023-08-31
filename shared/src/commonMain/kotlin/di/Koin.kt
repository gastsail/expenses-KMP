package di

import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule)
    }

//Called by iOS
fun initKoin() = initKoin{}

val commonModule = module {
    single<ExpenseRepository> { ExpenseRepoImpl() }
    factory { ExpensesViewModel(get()) }
}
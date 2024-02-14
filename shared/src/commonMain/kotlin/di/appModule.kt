package di

import com.expenseApp.db.AppDatabase
import data.ExpenseManager
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun appModule(appDatabase: AppDatabase) = module {
    single { ExpenseManager }.withOptions { createdAtStart() }
    single<ExpenseRepository> { ExpenseRepoImpl(get(), appDatabase) }
    factory { ExpensesViewModel(get()) }
}
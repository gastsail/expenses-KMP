package data

import com.expenseApp.db.AppDatabase
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import model.Expense
import model.ExpenseCategory
import model.NetworkExpense

//Change ip with your ip after runing server, please see
//https://github.com/gastsail/ktorExpensesApi/tree/master
private const val BASE_URL = "http://192.168.0.102:8080"

class ExpenseRepoImpl(
    private val appDatabase: AppDatabase,
    private val httpClient: HttpClient
) : ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries

    override suspend fun getAllExpenses(): List<Expense> {
        return if (queries.selectAll().executeAsList().isEmpty()) {
            val networkResponse = httpClient.get("$BASE_URL/expenses").body<List<NetworkExpense>>()
            val expenses = networkResponse.map { networkExpense ->
                Expense(
                    id = networkExpense.id,
                    amount = networkExpense.amount,
                    category = ExpenseCategory.valueOf(networkExpense.categoryName),
                    description = networkExpense.description
                )
            }
            expenses.forEach {
                queries.insert(it.amount, it.category.name, it.description)
            }
            expenses
        } else {
            queries.selectAll().executeAsList().map {
                Expense(
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }
        }
    }

    override suspend fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.post("$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }

    override suspend fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.put("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }

    override suspend fun deleteExpense(expense: Expense): List<Expense> {
        httpClient.delete("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
        queries.transaction {
            queries.delete(
                id = expense.id
            )
        }
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
    }
}
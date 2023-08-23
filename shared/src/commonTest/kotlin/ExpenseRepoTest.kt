import data.fakeExpenseList
import domain.ExpenseRepository
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.doesNothing
import io.mockative.every
import io.mockative.mock
import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertSame

class ExpenseRepoTest {

    @Mock
    val repo = mock(classOf<ExpenseRepository>())

    @Test
    fun testThatExpensesListExists() {
        every { repo.getAllExpenses() }.returns(fakeExpenseList)
        assertSame(repo.getAllExpenses(), fakeExpenseList)
    }

    @Test
    fun testThatExpenseIsAddedToList() {
        //TODO
    }
}
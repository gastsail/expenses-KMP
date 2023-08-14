import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdImage

data class ExpensesUiState(
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = null
) {
    val categories = images.map { it.category }.toSet()
    val selectedImages = images.filter { it.category == selectedCategory }
}
class ExpensesViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(images = httpClient.get("https://sebi.io/demo-image-api/pictures.json").body())
            }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }
}
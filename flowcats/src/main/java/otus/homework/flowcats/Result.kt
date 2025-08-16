package otus.homework.flowcats

sealed interface Result {
    data class Success(val uiModel: Fact) : Result
    data object Error : Result
    data object Loading : Result
}
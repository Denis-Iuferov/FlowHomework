package otus.homework.flowcats

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CatsRepository(
    private val catsService: CatsService,
    private val refreshIntervalMs: Long = 5000
) {

    fun listenForCatFacts() = flow {
        while (true) {
            try {
                val latestNews = catsService.getCatFact()
                emit(Result.Success(latestNews))
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
                // так как сервис не доступен, я хз почему приложение по задумке должно было крашится,
                // но у меня оно крашилось из-за 503 от сервера
                emit(Result.Error)
            }

            delay(refreshIntervalMs)
        }
    }
}
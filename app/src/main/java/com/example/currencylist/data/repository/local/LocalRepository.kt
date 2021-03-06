package com.example.currencylist.data.repository.local

import com.example.currencylist.data.IDispatcher
import com.example.currencylist.data.db.RateDao
import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalRepository(
    private val dao: RateDao,
    dispatcher: IDispatcher
) : ILocalRepository, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext = job + dispatcher.io

    override val flowRates: Flow<List<RateItem>>
        get() = dao.flowRates()

    override fun batchUpdate(newAmount: Int) {
        launch {
            dao.batchUpdate(newAmount)
        }
    }

    override fun update(item: RateItem) {
        launch { dao.update(item) }
    }

    override fun insertOrReplace(code: String, rate: Long) {
        launch {
            dao.insertOrReplace(code, rate)
        }
    }

    override fun cancel() {
        job.cancel()
    }

}
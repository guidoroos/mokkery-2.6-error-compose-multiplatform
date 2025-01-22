package com.viaplay.myapplicationskie


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SampleViewController (
    private val sampleRepository: SampleRepository = SampleRepositoryImpl() ,
    private val scope:CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private val _data : MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val data = _data.asStateFlow()



    fun getData(){
        scope.launch {
            val data = sampleRepository.getData()
            _data.value = data
        }
    }
}



interface SampleRepository {
    suspend fun getData(): List<String>
}

class SampleRepositoryImpl: SampleRepository {
    override suspend fun getData(): List<String> {
        return listOf("Hello", "World")
    }
}


package com.tuan.borutoheroes.domain.use_cases.read_onboarding

import com.tuan.borutoheroes.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}
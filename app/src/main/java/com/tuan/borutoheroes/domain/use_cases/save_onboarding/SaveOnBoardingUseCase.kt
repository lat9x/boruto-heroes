package com.tuan.borutoheroes.domain.use_cases.save_onboarding

import com.tuan.borutoheroes.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(isCompleted: Boolean) {
        repository.saveOnBoardingState(isCompleted = isCompleted)
    }
}
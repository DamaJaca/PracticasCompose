package com.djcdev.practicas.domain.usecase

import com.djcdev.practicascompose.domain.Repository
import com.djcdev.practicascompose.domain.model.DetailModel
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor (private val repository: Repository) {
    suspend operator fun invoke(): DetailModel? {
        return repository.getDetails()
    }
}
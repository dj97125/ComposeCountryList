package com.example.composecountrylist.domain

import com.example.composecountrylist.domain.NetworkRepository
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: NetworkRepository
) {
    operator fun invoke() = repository.countryList()

}
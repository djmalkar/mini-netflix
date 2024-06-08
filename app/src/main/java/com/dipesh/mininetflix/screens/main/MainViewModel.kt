package com.dipesh.mininetflix.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    /*private val observeFavoriteQuestionUseCase: ObserveFavoriteQuestionUseCase,
    private val toggleFavoriteQuestionUseCase: ToggleFavoriteQuestionUseCase,*/
): ViewModel() {

    fun isQuestionFavorite(questionId: String): Flow<Boolean> {
        //return observeFavoriteQuestionUseCase.isQuestionFavorite(questionId)
        return emptyFlow()
    }

    fun toggleFavoriteQuestion(questionId: String, questionTitle: String) {
        //toggleFavoriteQuestionUseCase.toggleFavoriteQuestion(questionId, questionTitle)
    }


}
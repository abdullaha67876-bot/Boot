package com.example.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ai.GeminiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AIViewModel : ViewModel() {
    private val geminiService = GeminiService()
    
    private val _suggestion = MutableStateFlow<String?>(null)
    val suggestion: StateFlow<String?> = _suggestion

    fun getSuggestion(message: String) {
        viewModelScope.launch {
            _suggestion.value = "AI is thinking..."
            val reply = geminiService.getReplySuggestion(message)
            _suggestion.value = reply
        }
    }
}

package com.fernando.fnotescreator.ui.screens.noteInfoScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NoteInfoViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private val _note: MutableLiveData<Note> = MutableLiveData()
    val note: LiveData<Note>
        get() = _note


    private val _isUpdated: MutableLiveData<Boolean> = MutableLiveData()
    val isUpdated: LiveData<Boolean>
        get() = _isUpdated


    fun updateNote(newContent: String) {
        viewModelScope.launch {
            _note.value?.let { note ->
                noteRepository.updateNoteContentById(newContent, note.id)
                _isUpdated.postValue(true)
            }
        }
    }


    fun getNote(noteID: String) {
        viewModelScope.launch {
            noteRepository.findNoteById(noteID)?.let { note ->
                _note.postValue(note)
            }
        }
    }
}
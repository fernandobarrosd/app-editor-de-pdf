package com.fernando.fnotescreator.ui.screens.addNoteScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.fnotescreator.mappers.toEntity
import com.fernando.fnotescreator.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private val _isShowNoteNameInputDialog : MutableLiveData<Boolean> = MutableLiveData()
    val isShowNoteNameInputDialog: LiveData<Boolean>
        get() = _isShowNoteNameInputDialog

    private val _isSaved: MutableLiveData<Boolean> = MutableLiveData()
    val isSaved : LiveData<Boolean>
        get() = _isSaved


    fun showNoteNameInputDialog() {
        _isShowNoteNameInputDialog.postValue(true)
    }

    fun hideNoteNameInputDialog() {
        _isShowNoteNameInputDialog.postValue(false)
    }

    fun saveNote(saveNoteDTO: SaveNoteDTO) {
        viewModelScope.launch {
            noteRepository.saveNote(saveNoteDTO.toEntity())
            _isShowNoteNameInputDialog.postValue(false)
            _isSaved.postValue(true)
        }

    }
}
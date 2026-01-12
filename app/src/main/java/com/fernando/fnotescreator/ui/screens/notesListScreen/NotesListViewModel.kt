package com.fernando.fnotescreator.ui.screens.notesListScreen

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
class NotesListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>>
        get() = _notes

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isSearched: MutableLiveData<Boolean> = MutableLiveData()
    val isSearched: LiveData<Boolean>
        get() = _isSearched

    private val _isShowDeleteAlert: MutableLiveData<Boolean> = MutableLiveData()
    val isShowDeleteAlert: LiveData<Boolean>
        get() = _isShowDeleteAlert

    private val _selectedNote: MutableLiveData<Note?> = MutableLiveData()
    val selectedNote: LiveData<Note?>
        get() = _selectedNote

    private var _initialNotes = emptyList<Note>()

    fun getAllNotes() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val allNotes = noteRepository.findAllNotes()
            _isLoading.postValue(false)
            _notes.postValue(allNotes)
            _initialNotes = allNotes
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            _selectedNote.value?.let { selectedNote ->
                noteRepository.deleteNoteById(selectedNote.id)

                _notes.value?.let { notes ->
                    val filteredNotes = notes.filter{ note -> note.id != selectedNote.id }
                    _notes.postValue(filteredNotes)
                }
                _isShowDeleteAlert.postValue(false)
            }
        }
    }

    fun filterNotesByName(noteName: String) {
        _isSearched.postValue(true)
        _notes.value?.let { notes ->
            val filteredNotesByName = _initialNotes
                .filter { pdfInfo -> pdfInfo.name.contains(noteName) }

            _notes.postValue(filteredNotesByName)
        }
    }
    fun showDeleteAlert(note: Note) {
        _isShowDeleteAlert.postValue(true)
        _selectedNote.postValue(note)
    }

    fun hideDeleteAlert() {
        _isShowDeleteAlert.postValue(false)
        _selectedNote.postValue(null)
    }
}
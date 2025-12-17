package com.fernando.editordepdf.ui.screens.pdfsListScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.repositories.PdfInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PdfsListViewModel @Inject constructor(private val pdfInfoRepository: PdfInfoRepository) : ViewModel() {
    private val _pdfs: MutableLiveData<List<PdfInfo>> = MutableLiveData()
    val pdfs: LiveData<List<PdfInfo>>
        get() = _pdfs

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isShowDeleteAlert: MutableLiveData<Boolean> = MutableLiveData()
    val isShowDeleteAlert: LiveData<Boolean>
        get() = _isShowDeleteAlert

    private val _selectedPdfInfo: MutableLiveData<PdfInfo?> = MutableLiveData()
    val selectedPdfInfo: LiveData<PdfInfo?>
        get() = _selectedPdfInfo

    private var _initialPdfsItems = emptyList<PdfInfo>()

    init {
        getAllPdfs()
    }

    fun getAllPdfs() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val allPdfs = pdfInfoRepository.findAll()
            _pdfs.postValue(allPdfs)
            _initialPdfsItems = allPdfs
            _isLoading.postValue(true)
        }
    }

    fun deletePdfInfo() {
        viewModelScope.launch {
            _selectedPdfInfo.value?.let { selectedPdfInfo ->
                pdfInfoRepository.deletePdfInfoById(selectedPdfInfo.id)
                _pdfs.value?.let { pdfs ->
                    val filteredPdfs = _initialPdfsItems.filter{ pdfInfo -> pdfInfo.id != selectedPdfInfo.id }
                    _pdfs.postValue(filteredPdfs)
                }
                _isShowDeleteAlert.postValue(false)
            }
        }
    }

    fun filterPdfsByName(pdfName: String) {
        _pdfs.value?.let { pdfs ->
            val filteredPdfsByName = _initialPdfsItems
                .filter { pdfInfo -> pdfInfo.name.contains(pdfName) }

            _pdfs.postValue(filteredPdfsByName)
        }
    }

    fun showDeleteAlert(pdfInfo: PdfInfo) {
        _isShowDeleteAlert.postValue(true)
        _selectedPdfInfo.postValue(pdfInfo)
    }

    fun hideDeleteAlert() {
        _isShowDeleteAlert.postValue(false)
        _selectedPdfInfo.postValue(null)
    }
}
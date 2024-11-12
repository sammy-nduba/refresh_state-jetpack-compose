package com.example.block_app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.block_app.employee.data.repository.EmployeeRepository

class ViewModelFactory (private val repository: EmployeeRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EmployeeViewModel::class.java) -> EmployeeViewModel(repository as EmployeeRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}

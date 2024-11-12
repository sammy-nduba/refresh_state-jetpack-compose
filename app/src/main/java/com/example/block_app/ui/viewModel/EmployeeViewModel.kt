package com.example.block_app.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.block_app.employee.data.apiService.model.Employee
import com.example.block_app.employee.data.apiService.model.EmployeeX
import com.example.block_app.employee.data.repository.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class EmployeeViewModel(private val employeeRepository: EmployeeRepository): ViewModel() {

    private val _employee = MutableStateFlow(Employee(emptyList()))
    val employee: StateFlow<Employee>
    get() = _employee


    fun getEmployee() {
        viewModelScope.launch {
            _employee.value = employeeRepository.getEmployee()
        }
    }


}
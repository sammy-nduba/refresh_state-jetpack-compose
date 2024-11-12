package com.example.block_app.employee.data.repository

import com.example.block_app.employee.data.apiService.EmployeeApi
import com.example.block_app.employee.data.apiService.model.Employee
import com.example.block_app.employee.data.apiService.model.EmployeeX

class EmployeeRepository(private val employeeApi: EmployeeApi) {


    suspend fun getEmployee(): Employee {
        return employeeApi.getEmployee()
    }

}
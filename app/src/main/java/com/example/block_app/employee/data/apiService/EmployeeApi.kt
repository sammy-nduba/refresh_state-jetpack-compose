package com.example.block_app.employee.data.apiService

import com.example.block_app.employee.data.apiService.model.Employee
import com.example.block_app.employee.data.apiService.model.EmployeeX
import retrofit2.http.GET

interface EmployeeApi {

    @GET("employees.json")
    suspend fun getEmployee() : Employee
}
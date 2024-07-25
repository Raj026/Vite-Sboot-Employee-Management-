import axios from 'axios'
import React from 'react'

const API_BASE_URL = 'http://localhost:8080/api/employees'

class EmployeeService {
    saveEmployee(employee) {
        return axios.post(API_BASE_URL, employee)
    }

    getEmployees() {
        return axios.get(API_BASE_URL)
    }
}

export default new EmployeeService
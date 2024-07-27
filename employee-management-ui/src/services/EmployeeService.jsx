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

    deleteEmployee(id) {
        return axios.delete(API_BASE_URL+"/"+id)
    }

    getEmployeeById(id) {
        return axios.get(API_BASE_URL+"/"+id)
    }

    updateEmployee(employee, id) {
        return axios.put(API_BASE_URL+"/"+id, employee)
    }
}

export default new EmployeeService
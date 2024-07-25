import axios from "axios";
import React, { useEffect, useState } from "react";
import EmployeeService from "../services/EmployeeService";

const AddEmployee = () => {
  const [employee, setEmployee] = useState({
    firstName: "",
    lastName: "",
    emailId: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee({ ...employee, [name]: value });
    // console.log(name + " Value: " + value )
  };

  const submitHandler = (event) => {
    // event.preventDefault();
    // const formData = new FormData();
    // formData.append("firstName", employee.firstName);
    // formData.append("lastName", employee.lastName);
    // formData.append("emailId", employee.emailId);

    // for (let [key, value] of formData.entries()) {
    //   console.log(`${key}, ${value}`);
    // }

    // axios
    //   .post("http://localhost:8080/api/employees", formData, {
    //     headers: {
    //       "Content-Type": "application/json",
    //     },
    //   })
    //   .then((response) => {
    //     alert("Employee Added");
    //   })
    //   .catch((error) => {
    //     alert("Error Occurred");
    //   });
  };

  const saveEmployee = (e) => {
    e.preventDefault()
    EmployeeService.saveEmployee(employee)
    .then((response) => {
        console.log(response)
        alert('Employee Added Successfully')
    })
    .catch((error) => {
        console.log(error)
        alert('Error Occurred')
    })
  }

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b ">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add Employee</h1>
        </div>
        <form onSubmit={saveEmployee}>
          <div className="items-center justify-center h-14 w-full my-4">
            <label
              className="block text-gray-600 "
              style={{ marginRight: "300px" }}
            >
              First Name
            </label>
            <input
              type="text"
              name="firstName"
              onChange={handleChange}
              value={employee.firstName}
              className="h-10 w-96 border mt-2 ml-2 px-2 py-2"
            ></input>
          </div>
          <div className="items-center justify-center h-14 w-full my-4">
            <label
              className="block text-gray-600 "
              style={{ marginRight: "300px" }}
            >
              Last Name
            </label>
            <input
              type="text"
              name="lastName"
              value={employee.lastName}
              onChange={handleChange}
              className="h-10 w-96 border mt-2 ml-2 px-2 py-2"
            ></input>
          </div>
          <div className="h-14 w-full my-4">
            <label
              className="block text-gray-600"
              style={{ marginRight: "320px" }}
            >
              Email ID
            </label>
            <input
              type="text"
              onChange={handleChange}
              name="emailId"
              value={employee.emailId}
              className="h-10 w-96 border mt-2 ml-2 px-2 py-2"
            ></input>
          </div>
          <div className="items-center justify-center h-14 w-full my-4">
            <button
              type="submit"
              className="rounded text-white bg-green-500 py-2 px-6 mt-4 hover:bg-green-800"
              style={{ marginRight: "170px" }}
              onClick={submitHandler}
            >
              SAVE
            </button>
            <button className="rounded text-white bg-red-500 py-2 px-6 hover:bg-red-950">
              CLEAR
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddEmployee;

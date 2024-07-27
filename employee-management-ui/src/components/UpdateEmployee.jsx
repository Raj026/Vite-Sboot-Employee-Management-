import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const UpdateEmployee = () => {
    const navigate = useNavigate()
  const {id} = useParams();
  const [employee, setEmployee] = useState({
    id: id,
    firstName: "",
    lastName: "",
    emailId: "",
  });

  const updateEmployee = (e) => {
    e.preventDefault();
    EmployeeService.updateEmployee(employee, id)
    .then((response) => {
        alert('updated successfully')
        navigate("/")
    })
    .catch((error) => {
        console.log(error)
    })
  };

  useEffect(() => {
    const fetchData = async () => {
        try {
            const response = await EmployeeService.getEmployeeById(id)
            setEmployee(response.data)
        } catch (error) {
            console.log(error)
        }
    }
    fetchData()
  },[])

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee({ ...employee, [name]: value });
    // console.log(name + " Value: " + value )
  };

//   const reset = (e) => {
//     e.preventDefault()
//     navigate("/")
//   }

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b ">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Update Employee</h1>
        </div>
        <form onSubmit={updateEmployee}>
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
              onClick={updateEmployee}
            >
              UPDATE
            </button>
            <button
              className="rounded text-white bg-red-500 py-2 px-6 hover:bg-red-950"
              onClick={() => navigate("/")}
            >
              CANCEL
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UpdateEmployee;

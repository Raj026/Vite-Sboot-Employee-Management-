import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import Employee from "./Employee";

const EmployeeList = () => {
  const navigate = useNavigate();
  const [count, setCount] = useState(1);
  const [loading, setLoading] = useState(true);
  const [employees, setEmployee] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      console.log(loading);
      try {
        const response = await EmployeeService.getEmployees();
        setEmployee(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  const deleteEmployee = async (e, id) => {
    e.preventDefault();
    try {
      await EmployeeService.deleteEmployee(id);
      setEmployee((prevEmployees) =>
        //here filter is applied and employees are set using setEmployee
        //such that the id which is deleted does not gets set and loaded in the UI side
        prevEmployees.filter((employee) => employee.id !== id)
      );
      alert('Employee Deleted')
    } catch (error) {
      console.log(error);
    }
    // EmployeeService.deleteEmployee(id)
    //   .then((response) => {
    //     if (employees) {
    //       //here filter is applied and employees are set using setEmployee
    //       //such that the id which is deleted does not gets set and loaded in the UI side
    //       setEmployee((prevElement) => {
    //         return prevElement.filter((employee) => {
    //           employee.id !== id;
    //         });
    //       });
    //     }
    //   })
    //   .catch((error) => {
    //     console.log("error");
    //   });
  };
  return (
    <div className="container mx-auto my-4">
      <div className="h-12 mt-12 flex w-full">
        <button
          onClick={() => navigate("/addEmployee")}
          className="rounded bg-slate-600 text-white py-2 px-8 font-semibold"
        >
          Add Employee
        </button>
      </div>
      <div className="flex shadow border-b mt-8">
        <table className="min-w-full">
          <thead className="bg-gray-500">
            <tr>
              <th className="text-left font-bold uppercase tracking-wider py-4 px-2">
                First Name
              </th>
              <th className="text-left font-bold uppercase tracking-wider py-3">
                Last Name
              </th>
              <th className="text-left font-bold uppercase tracking-wider py-3">
                Email
              </th>
              <th className="text-right font-bold uppercase tracking-wider py-3 px-2">
                Action
              </th>
            </tr>
          </thead>
          {!loading && employees && (
            <tbody>
              {employees.map((emp) => (
                <Employee
                  employee={emp}
                  key={emp.id}
                  deleteEmployee={deleteEmployee}
                >
                  {" "}
                </Employee>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default EmployeeList;

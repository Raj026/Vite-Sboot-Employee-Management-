import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const EmployeeList = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      console.log(loading)
      try {
        const response = await EmployeeService.getEmployees();
        setEmployee(response.data);
      } catch (error) {
        console.log(error)
      }
      setLoading(false);
    };
    fetchData();
  }, []);

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
          {!loading && (
            <tbody>
              {employee.map((emp) => (
                <tr key={emp.id}>
                  <td className="text-left tracking-wider py-4 px-2">{emp.firstName}</td>
                  <td className="text-left tracking-wider py-3">{emp.lastName}</td>
                  <td className="text-left tracking-wider py-3">{emp.emailId}</td>
                  <td className="text-right tracking-wider py-3 px-2"></td>
                </tr>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default EmployeeList;

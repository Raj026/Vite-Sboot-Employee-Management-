import React from "react";
import { useNavigate } from "react-router-dom";

const Employee = ({ employee, deleteEmployee }) => {
  const navigate = useNavigate()
  const editEmployee = (e, id) => {
    e.preventDefault()
    navigate(`/editEmployee/${id}`)

  }

  return (
    <tr key={employee.id}>
      <td className="text-left tracking-wider py-4 px-2">
        {employee.firstName}
      </td>
      <td className="text-left tracking-wider py-3">{employee.lastName}</td>
      <td className="text-left tracking-wider py-3">{employee.emailId}</td>
      <td className="text-right tracking-wider py-3 px-2">
        <a
          onClick={(e, id) => {
            editEmployee(e, employee.id);
          }}
          className="text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer"
        >
          Edit
        </a>
        <a
          onClick={(e, id) => deleteEmployee(e, employee.id)}
          className="text-indigo-600 hover:text-indigo-800 hover:cursor-pointer"
        >
          Delete
        </a>
      </td>
    </tr>
  );
};

export default Employee;

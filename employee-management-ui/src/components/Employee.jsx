import React from 'react'

const Employee = ({ employee }) => {
  return (
    <tr key={employee.id}>
      <td className="text-left tracking-wider py-4 px-2">
        {employee.firstName}
      </td>
      <td className="text-left tracking-wider py-3">{employee.lastName}</td>
      <td className="text-left tracking-wider py-3">{employee.emailId}</td>
      <td className="text-right tracking-wider py-3 px-2"></td>
    </tr>
  );
};

export default Employee
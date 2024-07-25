import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Navbar from "./components/Navbar";
import AddEmployee from "./components/AddEmployee";

function App() {
  return (
    <>
      <Navbar />
      <AddEmployee />
    </>
  );
}

export default App;

import TextField from "@mui/material/TextField";
import React, { useEffect, useState } from 'react';
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper"
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import axios from 'axios';
import { api } from '../api';



export const Profile = () => {
  const [textValue, setTextValue] = useState("");
  const [rows, setRows] = useState([]);

  const [firstName, setFName] = useState("");
  const [lastName, setLName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhone] = useState("");
  const [speciality, setSpec] = useState("");
  const [price, setPrice] = useState("");
  const [grade, setGrade] = useState("");
  const [experience, setExp] = useState("");
  const [classification, setClass] = useState("");
  const [hospitals, setHospitals] = useState([]);

  const onTextChange = (e) => setTextValue(e.target.value);
  const handleSubmit = () => console.log(textValue);
  const handleReset = () => setTextValue("");

  const accessToken = localStorage.getItem("token");

  const posy = async () => {
    const result = await axios(
      api.dashboard.getDoctorData(), {
        params: { email: localStorage.getItem("email") },
        headers: { "Authorization": accessToken }
      }
    );
    // console.log(result)
    setRows(result.data);
    setFName(result.data.firstName)
    setEmail(result.data.email)
    setLName(result.data.lastName)
    setPhone(result.data.phoneNumber)
    setSpec(result.data.speciality)
    setPrice(result.data.price)
    setGrade(result.data.grade)
    setExp(result.data.experience)
    setClass(result.data.classification)
    setHospitals(result.data.hospitals)
  };

  useEffect(() => {
    posy();
  }, [])

  const updateDoctor = () => {
    axios.post( "http://localhost:8090/api/admin/updatedoctor",
      {email, firstName, lastName, phoneNumber, speciality, price, grade, experience, classification, hospitals },
      { headers: {"Authorization": accessToken}})
    window.location.reload();
  }

  return (
    <Paper elevation={1}>
  <Stack
    direction="column"
    justifyContent="center"
    alignItems="flex-center"
    spacing={2}
    marginLeft={2}
    >
    <Box marginLeft={2}>
      <h2>My Profile</h2>
    </Box>
      <TextField
        value={email}
        label={"Email"}
        onChange={(e) => setEmail(e.target.value)}
      />
      <TextField
        value={firstName}
        label={"First Name"}
        onChange={(e) => setFName(e.target.value)}
      />

      <TextField
        value={lastName}
        label={"Last Name"}
        onChange={(e) => setLName(e.target.value)}
      />
      <TextField
        value={phoneNumber}
        label={"Phone Number"}
        onChange={(e) => setPhone(e.target.value)}
      />
      <TextField
        value={speciality}
        label={"Speciality"}
        onChange={(e) => setSpec(e.target.value)}
      />
      <TextField
        value={price}
        label={"Price, Lei"}
        onChange={(e) => setPrice(e.target.value)}
      />
      <TextField
        value={grade}
        label={"Grade"}
        onChange={(e) => setGrade(e.target.value)}
      />
      <TextField
        value={experience}
        label={"Experience, years"}
        onChange={(e) => setExp(e.target.value)}
      />
      <TextField
        value={classification}
        label={"Classification"}
        onChange={(e) => setClass(e.target.value)}
      />
      <TextField
        value={hospitals}
        label={"Hospitals"}
        onChange={(e) => setHospitals(e.target.value)}
      />
      <Button variant="outlined" onClick={() => {updateDoctor()}}>Update</Button>
    </Stack>
      </Paper>
  );
};
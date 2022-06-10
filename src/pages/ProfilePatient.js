import TextField from "@mui/material/TextField";
import React, { useEffect, useState } from 'react';
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper"
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import axios from 'axios';
import { api } from '../api';



export const ProfilePatient = () => {
  const [textValue, setTextValue] = useState("");
  const [rows, setRows] = useState([]);

  const [firstName, setFName] = useState("");
  const [lastName, setLName] = useState("");
  const [age, setAge] = useState("");
  const [phoneNumber, setPhone] = useState("");
  const [middleName, setMid] = useState("");

  const onTextChange = (e) => setTextValue(e.target.value);
  const handleSubmit = () => console.log(textValue);
  const handleReset = () => setTextValue("");

  const accessToken = localStorage.getItem("token");

  const posy = async () => {
    const result = await axios(
      api.dashboard.getPatientData(), {
        params: { email: localStorage.getItem("email") },
        headers: { "Authorization": accessToken }
      }
    );
    // console.log(result)
    setRows(result.data);
    setFName(result.data.firstName)
    setAge(result.data.age)
    setLName(result.data.lastName)
    setPhone(result.data.phoneNumber)
    setMid(result.data.middleName)
  };

  useEffect(() => {
    posy();
  }, [])

  const updateDoctor = () => {
    axios.post( "http://localhost:8090/api/patient/update",
      {firstName, lastName, middleName, age, phoneNumber},
      { headers: {"Authorization": accessToken}})
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
        value={middleName}
        label={"Middle Name"}
        onChange={(e) => setMid(e.target.value)}
      />
      <TextField
        value={age}
        label={"Age"}
        onChange={(e) => setAge(e.target.value)}
      />
      <TextField
        value={phoneNumber}
        label={"Phone Number"}
        onChange={(e) => setPhone(e.target.value)}
      />
      <Button variant="outlined" onClick={() => {updateDoctor()}}>Update</Button>
    </Stack>
    </Paper>
  );
};
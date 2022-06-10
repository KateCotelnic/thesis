import TextField from "@mui/material/TextField";
import React, { useEffect, useState } from 'react';
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper"
import Stack from '@mui/material/Stack';
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
    console.log(result)
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
    <Stack
      direction="column"
      justifyContent="center"
      alignItems="flex-start"
      spacing={2}
    >
      <h2>My Profile</h2>
      <TextField
        label={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <TextField
        label={firstName}
        onChange={(e) => setFName(e.target.value)}
      />

      <TextField
        label={lastName}
        onChange={(e) => setLName(e.target.value)}
      />
      <TextField
        label={phoneNumber}
        onChange={(e) => setPhone(e.target.value)}
      />
      <TextField
        label={speciality}
        onChange={(e) => setSpec(e.target.value)}
      />
      <TextField
        label={price}
        onChange={(e) => setPrice(e.target.value)}
      />
      <TextField
        label={grade}
        onChange={(e) => setGrade(e.target.value)}
      />
      <TextField
        label={experience}
        onChange={(e) => setExp(e.target.value)}
      />
      <TextField
        label={classification}
        onChange={(e) => setClass(e.target.value)}
      />
      <TextField
        label={hospitals}
        onChange={(e) => setHospitals(e.target.value)}
      />
      <Button onClick={() => {updateDoctor()}}>Update</Button>
    </Stack>
  );
};
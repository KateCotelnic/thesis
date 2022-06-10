import TextField from "@mui/material/TextField";
import React, { useEffect, useState } from 'react';
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper"
import Stack from '@mui/material/Stack';
import MenuItem from '@mui/material/MenuItem';
import {Select} from '@mui/material';
import Box from '@mui/material/Box';
import { DateTimePicker, LocalizationProvider } from '@mui/lab';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import axios from 'axios';
import moment from 'moment';

const mockHospitals = [
  {
    key: 1,
    value: 'Medpark International Hospital',
    label: 'Medpark International Hospital',
  },
  {
    key: 2,
    value: 'Sancos',
    label: 'Sancos',
  },
  {
    key: 3,
    value: 'Allclinic',
    label: 'Allclinic',
  },
  {
    key: 4,
    value: 'Forum Clinic',
    label: 'Forum Clinic',
  },
  {
    key: 5,
    value: 'Estetic Med Center',
    label: 'Estetic Med Center',
  },
  {
    key: 6,
    value: 'Tibetmed',
    label: 'Tibetmed',
  },
  {
    key: 7,
    value: 'Expert Clinics',
    label: 'Expert Clinics',
  },
  {
    key: 8,
    value: 'OftalmoCorrect ProMed Family&Kids',
    label: 'OftalmoCorrect ProMed Family&Kids',
  },
  {
    key: 9,
    value: 'OftalmoCorrect ProMed Family&Kids',
    label: 'OftalmoCorrect ProMed Family&Kids',
  },
]

const mockDoctors = [
  {
    key: 1,
    value: 'catalin.schiopu@isa.utm.md',
    label: 'catalin.schiopu@isa.utm.md',
  },
  {
    key: 2,
    value: 'polina@gmail.com',
    label: 'polina@gmail.com',
  },
  {
    key: 3,
    value: 'esmae@gmail.com',
    label: 'esmae@gmail.com',
  },
  {
    key: 4,
    value: 'cherish@gmail.com',
    label: 'cherish@gmail.com',
  },
  {
    key: 5,
    value: 'kyandev@gmail.com',
    label: 'kyandev@gmail.com',
  },
  {
    key: 6,
    value: 'holli@gmail.com',
    label: 'holli@gmail.com',
  },
  {
    key: 7,
    value: 'felicity@gmail.com',
    label: 'felicity@gmail.com',
  },
  {
    key: 8,
    value: 'zakifor@gmail.com',
    label: 'zakifor@gmail.com',
  },
  {
    key: 9,
    value: 'hopperlacy@gmail.com',
    label: 'hopperlacy@gmail.com',
  },
]

const styles = theme => ({
  input: {
    color: "blue"
  }
});

export const AddApp = () => {
  const [textValue, setTextValue] = useState("");
  const [rows, setRows] = useState([]);

  const [doctorEmail, setDocEmail] = useState("");
  const [hospitalName, setHospName] = useState("");
  const [date, setDT] = useState("");
  const [date2, setDE] = useState("");
  const [duration, setDuration] = useState("");

  const accessToken = localStorage.getItem("token");

  const startDate = moment(date).format('YYYY-MM-DD HH:mm')
  // console.log(startDate);
  const endDate = moment(date2).format('YYYY-MM-DD HH:mm')
  // console.log(endDate);

  const doctorsList = async () => {
    const result = await axios(
      "http://localhost:8090/api/doctors", {params: {hospitalName}}
    );
    setRows(result.data)
    // console.log(rows)
  };

  const addAppointment = () => {
    const patientEmail = localStorage.getItem("email");
    axios.post( "http://localhost:8090/api/patient/newAppointment",
      {doctorEmail, patientEmail, hospitalName, startDate, endDate },
      { headers: {"Authorization": accessToken}})
  }

  return (
    <Paper elevation={1}>
      <Box marginLeft={2}>
      <h2>Add appointment</h2>
      </Box>
      <Stack
        direction="row"
        justifyContent="left"
        alignItems="flex-left"
        spacing={2}
        marginLeft={2}
      >
        <Box>
      <Select
        labelId="Hospital"
        value={hospitalName}
        onChange={(e) => setHospName(e.target.value)}
        label={"Hospital"}
      >
        {mockHospitals.map((option) => (
          <MenuItem key={option.key} value={option.value}>
            {option.label}
          </MenuItem>
        ))}
      </Select>
        </Box>

        <Box>
      <Select
        labelId="Doctor"
        value={doctorEmail}
        onChange={(e) => setDocEmail(e.target.value)}
        label={"Hospital"}
      >
        {mockDoctors.map((option) => (
          <MenuItem key={option.key} value={option.value}>
            {option.label}
          </MenuItem>
        ))}
      </Select>
        </Box>

        <Box color="#0000FF">
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DateTimePicker
          renderInput={(props) => <TextField {...props} />}
          label="Date Start"
          value={date}
          onChange={(newValue) => {
            setDT(newValue);
          }}
        />
      </LocalizationProvider>
        </Box>

        <Box>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DateTimePicker
          renderInput={(props) => <TextField {...props} />}
          label="Date End"
          value={date2}
          onChange={(newValue) => {
            setDE(newValue);
          }}
        />
      </LocalizationProvider>
        </Box>
        <Box>
      <Button variant="outlined" size="large" onClick={() => {addAppointment()}}>Add</Button>
    </Box>
      </Stack>
    </Paper>
  );
};
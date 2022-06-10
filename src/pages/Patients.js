import React, { useState, useEffect } from 'react';
import axios from 'axios';
import moment from 'moment';
import PropTypes from 'prop-types';
import { alpham, styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import TableSortLabel from '@mui/material/TableSortLabel';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Checkbox from '@mui/material/Checkbox';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import FormControlLabel from '@mui/material/FormControlLabel';
import Switch from '@mui/material/Switch';
import DeleteIcon from '@mui/icons-material/Delete';
import FilterListIcon from '@mui/icons-material/FilterList';
import { visuallyHidden } from '@mui/utils';
import { makeStyles, tableCellClasses } from '@mui/material';

import { api } from '../api';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

export default class Patients extends React.Component {
  constructor( props ) {
    super( props );
    this.state = {
      rows: [],
    };
  }

  // sortArray(arr, orderBy) {
  //   switch (orderBy) {
  //     case "asc":
  //     default:
  //       return arr.sort((a, b) =>
  //         a.price > b.price ? 1 : b.price > a.price ? -1 : 0
  //       );
  //     case "desc":
  //       return arr.sort((a, b) =>
  //         a.price < b.price ? 1 : b.price < a.price ? -1 : 0
  //       );
  //   }
  // };

  componentDidMount() {
    this.getAppointments();
  }

  getAppointments = () => {
    const accessToken = localStorage.getItem("token");
    axios.get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
      .then( ( response ) => {
        const appointments = response.data.appointmentsDoctor;
        console.log(appointments)
        const appointmentsInfo = appointments.map((appointment) => {
          return {
            id: appointment.id,
            hospital: appointment.hospital,
            startDate: moment(appointment.startDate).format('MMMM Do YYYY, h:mm'),
            status: appointment.status,
            fullName: `${appointment.firstNamePatient} ${appointment.lastNamePatient}`,
            agePatient: appointment.agePatient,
            phone: appointment.phone_number
          };
        })
        this.setState({
          rows: appointmentsInfo
        })
        console.log(this.state.rows);
      } )
      .catch( error => {
        alert( error.response.data.message );
      } );
  }

  render() {
    return(
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Full Name</StyledTableCell>
              <StyledTableCell align="center">Age</StyledTableCell>
              <StyledTableCell align="center">Phone</StyledTableCell>
              <StyledTableCell align="center">Hospital</StyledTableCell>
              <StyledTableCell align="center">Visit Time</StyledTableCell>
              <StyledTableCell align="center">Status</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.rows.map((row) => (
              <StyledTableRow key={row.id}>
                <StyledTableCell component="th" scope="row">
                  {row.fullName}
                </StyledTableCell>
                <StyledTableCell align="center">{row.agePatient}</StyledTableCell>
                <StyledTableCell align="center">{row.phone}</StyledTableCell>
                <StyledTableCell align="center">{row.hospital}</StyledTableCell>
                <StyledTableCell align="center">{row.startDate}</StyledTableCell>
                <StyledTableCell align="center">{row.status}</StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  }
}

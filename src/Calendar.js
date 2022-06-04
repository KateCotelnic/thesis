import * as React from 'react';
import { useState, useEffect } from 'react';
import moment from 'moment';
import Paper from '@mui/material/Paper';
import { ViewState, EditingState, IntegratedEditing } from '@devexpress/dx-react-scheduler';
import { styled } from '@mui/material/styles';
import {
  Scheduler,
  Appointments,
  AppointmentForm,
  AppointmentTooltip,
  WeekView,
  EditRecurrenceMenu,
  AllDayPanel,
  ConfirmationDialog,
  CurrentTimeIndicator,
  Toolbar,
  DateNavigator,
  TodayButton,
} from '@devexpress/dx-react-scheduler-material-ui';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import InputAdornment from '@mui/material/InputAdornment';
import appointments from './demo-data/today-appointments';
import { api } from './api';

const PREFIX = 'Demo';

const currentDate = moment();
const date = currentDate.date();

const makeTodayAppointment = (startDate, endDate) => {
  const days = moment(startDate).diff(endDate, 'days');
  const nextStartDate = moment(startDate)
    .year(currentDate.year())
    .month(currentDate.month())
    .date(date);
  const nextEndDate = moment(endDate)
    .year(currentDate.year())
    .month(currentDate.month())
    .date(date + days);

  return {
    startDate: nextStartDate.toDate(),
    endDate: nextEndDate.toDate(),
  };
};

// const myData = () => {
//   const myEmail = localStorage.getItem("email");
//   const accessToken = localStorage.getItem("token");
//   return axios.get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
//     .then( ( response ) => {
//       const appointments = response.data.appointmentsDoctor;
//       const appointmentsInfo = appointments.map((appointment) => {
//         const mapData = [{
//             id: appointment.id,
//             hospital: appointment.hospital,
//             startDate: new Date(appointment.startDate),
//             endDate: new Date(appointment.endDate),
//             status: appointment.status,
//             firstNamePatient: appointment.firstNamePatient,
//             lastNamePatient: appointment.lastNamePatient,
//             agePatient: appointment.agePatient
//           }];
//         return mapData;
//       })
//       console.log(appointmentsInfo);
//     } )
//     .catch( error => {
//       alert( error.response.data.message );
//     } );
// }

// const myData = () => {
//   const myEmail = localStorage.getItem("email");
//   const accessToken = localStorage.getItem("token");
//   return axios.get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
//     .then( ( response ) => {
//       const appointments = response.data.appointmentsDoctor;
//       const appointmentsInfo = appointments.map((appointment) => {
//         const mapData = {
//           id: appointment.id,
//           hospital: appointment.hospital,
//           startDate: new Date(appointment.startDate),
//           endDate: new Date(appointment.endDate),
//           status: appointment.status,
//           firstNamePatient: appointment.firstNamePatient,
//           lastNamePatient: appointment.lastNamePatient,
//           agePatient: appointment.agePatient
//         };
//         return mapData;
//       })
//       const calendar = appointmentsInfo.map(({ startDate, endDate, ...restArgs }) => {
//         const result = {
//           ...makeTodayAppointment(startDate, endDate),
//           ...restArgs,
//         };
//         date += 1;
//         if (date > 31) date = 1;
//         return result;
//       });
//       console.log(calendar);
//     } )
//     .catch( error => {
//       alert( error.response.data.message );
//     } );
// }
const classes = {
  checkBoxContainer: `${PREFIX}-checkBoxContainer`,
  textField: `${PREFIX}-textField`,
};
// #FOLD_BLOCK
const StyledGrid = styled(Grid)(({ theme: { spacing } }) => ({
  [`&.${classes.checkBoxContainer}`]: {
    paddingTop: spacing(1),
    paddingBottom: spacing(1),
    paddingLeft: spacing(4),
  },
}));
// #FOLD_BLOCK
const StyledTextField = styled(TextField)(({ theme: { spacing } }) => ({
  [`&.${classes.textField}`]: {
    marginRight: spacing(4),
    marginLeft: spacing(1),
    width: '120px',
  },
}));

// #FOLD_BLOCK
const ShadeCellsCheckBox = ({ shadePreviousCells, handleChange }) => (
  <FormControlLabel
    control={(
      <Checkbox
        checked={shadePreviousCells}
        onChange={() => handleChange('shadePreviousCells')}
        color="primary"
      />
    )}
    label="Shade previous cells"
  />
);

// #FOLD_BLOCK
const ShadePreviousAppointmentsCheckBox = ({ shadePreviousAppointments, handleChange }) => (
  <FormControlLabel
    control={(
      <Checkbox
        checked={shadePreviousAppointments}
        onChange={() => handleChange('shadePreviousAppointments')}
        color="primary"
      />
    )}
    label="Shade previous appointments"
  />
);
// #FOLD_BLOCK
// const CheckBoxContainer = (({
//                               shadePreviousCells, shadePreviousAppointments, handleCheckboxChange,
//                               // #FOLD_BLOCK
//                             }) => (
//   <StyledGrid item container direction="column" className={classes.checkBoxContainer} xs={6}>
//     <ShadeCellsCheckBox
//       shadePreviousCells={shadePreviousCells}
//       handleChange={handleCheckboxChange}
//     />
//     <ShadePreviousAppointmentsCheckBox
//       shadePreviousAppointments={shadePreviousAppointments}
//       handleChange={handleCheckboxChange}
//     />
//   </StyledGrid>
// ));
//
// // #FOLD_BLOCK
// const UpdateIntervalBox = (({
//                               updateInterval, onValueChange,
//                               // #FOLD_BLOCK
//                             }) => (
//   <Grid item container xs={6} alignItems="center" justifyContent="flex-end">
//     <Typography>
//       Update every:
//     </Typography>
//     <StyledTextField
//       className={classes.textField}
//       variant="outlined"
//       onChange={event => onValueChange(event.target.value)}
//       value={updateInterval / 1000}
//       type="number"
//       InputProps={{
//         endAdornment: <InputAdornment position="end">s</InputAdornment>,
//       }}
//     />
//   </Grid>
// ));

const current = new Date();

export default class Calendar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: appointments,
      // data: [],
      currentDate: new Date(),
      // appointmentsDoctor: [{
      //     id: "",
      //     hospital: "",
      //     startDate: startDateDefault.toISOString(),
      //     endDate: "",
      //     status: "",
      //     firstNamePatient: "",
      //     lastNamePatient: "",
      //     agePatient: "",
      //     phoneNumber: ""
      //   }],
      apiDoctorAppointments: [],
      addedAppointment: {},
      appointmentChanges: {},
      editingAppointment: undefined,
      shadePreviousCells: true,
      shadePreviousAppointments: true,
      updateInterval: 10000,
    };

    this.currentDateChange = (currentDate) => { this.setState({ currentDate }) };
    this.commitChanges = this.commitChanges.bind(this);
    this.changeAddedAppointment = this.changeAddedAppointment.bind(this);
    this.changeAppointmentChanges = this.changeAppointmentChanges.bind(this);
    this.changeEditingAppointment = this.changeEditingAppointment.bind(this);

    // const accessToken = localStorage.getItem("token");
    // const fdk = axios.get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
    //   .then( ( response ) => {
    //     const appointments = response.data.appointmentsDoctor;
    //     const appointmentsInfo = appointments.map((appointment) => {
    //       const mapData = {
    //         id: appointment.id,
    //         hospital: appointment.hospital,
    //         startDate: new Date(appointment.startDate),
    //         endDate: new Date(appointment.endDate),
    //         status: appointment.status,
    //         firstNamePatient: appointment.firstNamePatient,
    //         lastNamePatient: appointment.lastNamePatient,
    //         agePatient: appointment.agePatient
    //       };
    //       return mapData;
    //     })
    //     const calendar = appointmentsInfo.map(({ startDate, endDate, ...restArgs }) => {
    //       const result = {
    //         ...makeTodayAppointment(startDate, endDate),
    //         ...restArgs,
    //       };
    //       date += 1;
    //       if (date > 31) date = 1;
    //       return result;
    //     });
    //     console.log(calendar);
    //     console.log(appointments);
    //   })
    //   .catch( error => {
    //     alert( error.response.data.message );
    //   } );
    // console.log(fdk);

    // this.onCommitChanges = this.commitChanges.bind(this);
    // this.handleCheckboxChange = this.handleCheckboxChange.bind(this);
    // this.handleUpdateIntervalChange = (nextValue) => {
    //   this.setState({
    //     updateInterval: nextValue * 1000,
    //   });
    // };
  }

  // handleCheckboxChange(stateField) {
  //   const { [stateField]: fieldToChange } = this.state;
  //   this.setState({
  //     [stateField]: !fieldToChange,
  //   });
  // }

 componentDidMount = async () => {
   const myEmail = localStorage.getItem("email");
   const accessToken = localStorage.getItem("token");
   let fdk;
   try {
     fdk = await axios.get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
     // .then( ( response ) => {
     //   const appointments = response.data.appointmentsDoctor;
     //   const appointmentsInfo = appointments.map((appointment) => {
     //     const mapData = {
     //       id: appointment.id,
     //       hospital: appointment.hospital,
     //       startDate: new Date(appointment.startDate),
     //       endDate: new Date(appointment.endDate),
     //       status: appointment.status,
     //       firstNamePatient: appointment.firstNamePatient,
     //       lastNamePatient: appointment.lastNamePatient,
     //       agePatient: appointment.agePatient
     //     };
     //     return mapData;
     //   })
     // };
   } catch( e ) {
       alert( "Error happened! Call developer!" );
     } this.setState(
     {
       apiDoctorAppointments: fdk.data
     }
   );
   //     const calendar = appointmentsInfo.map(({ startDate, endDate, ...restArgs }) => {
   //       const result = {
   //         ...makeTodayAppointment(startDate, endDate),
   //         ...restArgs,
   //       };
   //       date += 1;
   //       if (date > 31) date = 1;
   //       return result;
   //     });
   //     console.log(calendar);
   //     console.log(appointments);
   //   })
   //   .catch( error => {
   //     alert( error.response.data.message );
   //   } );
   console.log(this.apiDoctorAppointments);
 }

  changeAddedAppointment(addedAppointment) {
    this.setState({ addedAppointment });
    console.log(this.setState({ addedAppointment }));
  }

  changeAppointmentChanges(appointmentChanges) {
    this.setState({ appointmentChanges });
  }

  changeEditingAppointment(editingAppointment) {
    this.setState({ editingAppointment });
  }

  commitChanges({ added, changed, deleted }) {
    this.setState((state) => {
      let { data } = state;
      if (added) {
        const startingAddedId = data.length > 0 ? data[data.length - 1].id + 1 : 0;
        data = [...data, { id: startingAddedId, ...added }];
      }
      if (changed) {
        data = data.map(appointment => (
          changed[appointment.id] ? { ...appointment, ...changed[appointment.id] } : appointment));
      }
      if (deleted !== undefined) {
        data = data.filter(appointment => appointment.id !== deleted);
      }
      return { data };
    });
  }

  render() {
    const {
      currentDate, data, addedAppointment, appointmentChanges, editingAppointment, shadePreviousCells,
      updateInterval, shadePreviousAppointments,
    } = this.state;
    console.log(data);
    return (
      <Paper>
        {/* <button onClick={myData}>Here</button> */}
        <Scheduler
          data={data}
          height={930}
        >
          <ViewState
            // currentDate={currentDate}
            onCurrentDateChange={this.currentDateChange}
            defaultCurrentDate={currentDate}
          />
          <EditingState
            onCommitChanges={this.commitChanges}
            addedAppointment={addedAppointment}
            onAddedAppointmentChange={this.changeAddedAppointment}
            appointmentChanges={appointmentChanges}
            onAppointmentChangesChange={this.changeAppointmentChanges}
            editingAppointment={editingAppointment}
            onEditingAppointmentChange={this.changeEditingAppointment}
          />
          <WeekView
            startDayHour={9}
            endDayHour={17}
          />
          <AllDayPanel />
          <EditRecurrenceMenu />
          <Toolbar />
          <DateNavigator />
          <TodayButton />
          <ConfirmationDialog />
          <Appointments />
          <AppointmentTooltip
            showOpenButton
            showDeleteButton
          />
          <AppointmentForm />
          <CurrentTimeIndicator
            shadePreviousCells={shadePreviousCells}
            shadePreviousAppointments={shadePreviousAppointments}
            updateInterval={updateInterval}
          />
        </Scheduler>
      </Paper>
    );
  }
}


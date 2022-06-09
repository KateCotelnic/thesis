import moment from 'moment';
import axios from 'axios';
import { appointments } from './appointments';
import { api } from '../api';

const currentDate = moment();
let date = currentDate.date();

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
//
// const plsWork = myData();
// console.log(plsWork);
// console.log(appointments);
// appointments.map
export default appointments.map(({ startDate, endDate, ...restArgs }) => {
  const result = {
    ...makeTodayAppointment(startDate, endDate),
    ...restArgs,
  };
  // date += 1;
  if (date > 31) date = 1;
  return result;
});

// export default myData();

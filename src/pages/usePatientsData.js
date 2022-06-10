import axios from 'axios';
import { useQuery } from 'react-query';
import { api } from '../api';

const accessToken = localStorage.getItem("token");

export const usePatientsData = () =>
  useQuery(
    'Appointments',
    () =>
      axios
        .get(api.dashboard.getDoctorData(), { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}})
        .then((res) => res.data.appointmentsDoctor)
  );

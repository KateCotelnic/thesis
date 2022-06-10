import axios from 'axios';
import { useQuery } from 'react-query';

const accessToken = localStorage.getItem("token");

export const useAppointmentEnums = () =>
  useQuery(
    'Search Enums',
    () =>
      axios
        .get('http://localhost:8090/api/patient/getAppointmentEnums', {headers: {"Authorization": accessToken}})
        .then((res) => res.data)
  );

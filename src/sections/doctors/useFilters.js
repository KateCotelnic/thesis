import axios from 'axios';
import { useQuery } from 'react-query';

export const useFilters = (filters) =>
  useQuery(['Filters', filters], fetchApi, {
    retry: 0,
    staleTime: 60 * 1000,
  });

const fetchApi = async (context) => {
  const [, { page, area, speciality, classification }] = context.queryKey;
  const _area = area === 'All' ? '' : area;
  const _speciality = speciality === 'All' ? '' : speciality;
  const _classification = classification === 'All' ? '' : classification;

  const res = await axios.get(
    `http://localhost:8090/api/doctors/param?area=${_area}&speciality=${_speciality}&classification=${_classification}`
  );
  return res.data;
};

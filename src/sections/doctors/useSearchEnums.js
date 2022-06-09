import axios from 'axios';
import { useQuery } from 'react-query';

export const useSearchEnums = () =>
  useQuery(
    'Search Enums',
    () =>
      axios
        .get('http://localhost:8090/api/searchenums')
        .then((res) => res.data),
    { staleTime: Infinity }
  );

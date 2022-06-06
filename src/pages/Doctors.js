import { useState } from 'react';
import { Box, Container, Typography } from '@mui/material';
import { useSearchEnums } from '../sections/doctors/useSearchEnums';
import Page from '../components/Page';
import FilterSelect from '../sections/doctors/FilterSelect';
import { useFilters } from '../sections/doctors/useFilters';

const doctorsFiltersContainerStyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  gap: '40px',
  flexWrap: 'wrap',
};

export default function Doctors() {
  const [filters, setFilters] = useState({
    page: 1,
    area: 'All',
    speciality: 'All',
    classification: 'All',
  });

  const searchEnums = useSearchEnums();
  const results = useFilters(filters);

  console.log(results.data);

  const handleFiltersChange = (e, select) => {
    setFilters((filters) => ({
      ...filters,
      page: 1,
      [`${select}`]: e.target.value,
    }));
  };

  return (
    <Page title="Dashboard: Doctors">
      <Container>
        <Typography variant="h4" sx={{ mb: 5 }}>
          Doctors
        </Typography>

        {searchEnums.data && (
          <Box sx={doctorsFiltersContainerStyle}>
            <FilterSelect
              onChange={(e) => handleFiltersChange(e, 'area')}
              values={['All', ...searchEnums.data.areas]}
              selectedValue={filters.area}
              label="Areas"
            />
            <FilterSelect
              onChange={(e) => handleFiltersChange(e, 'speciality')}
              values={['All', ...searchEnums.data.specialities]}
              selectedValue={filters.speciality}
              label="Specialities"
            />
            <FilterSelect
              onChange={(e) => handleFiltersChange(e, 'classification')}
              values={['All', ...searchEnums.data.classifications]}
              selectedValue={filters.classification}
              label="Classifications"
            />
          </Box>
        )}

        {results.data && (
          <Box sx={{ marginTop: '40px', display: 'flex', gap: '50px' }}>
            {results.data.map((doctor) => (
              <Box>
                <Box>firstName: {doctor.firstName}</Box>
                <Box>lastName: {doctor.lastName}</Box>
                <Box>speciality: {doctor.speciality}</Box>
                <Box>classification: {doctor.classification}</Box>
                <Box>experience: {doctor.experience}</Box>
                <Box>grade: {doctor.grade}</Box>
                <Box>hospitals: {doctor.hospitals}</Box>
                <Box>price: {doctor.price}</Box>
                <Box>rating: {doctor.rating}</Box>
                <Box>phoneNumber: {doctor.phoneNumber}</Box>
              </Box>
            ))}
          </Box>
        )}
      </Container>
    </Page>
  );
}

import React, { useState } from 'react';
import { Box, Container, Typography } from '@mui/material';
import Paper from '@mui/material/Paper';
import { useSearchEnums } from '../sections/doctors/useSearchEnums';
import Page from '../components/Page';
import FilterSelect from '../sections/doctors/FilterSelect';
import { useFilters } from '../sections/doctors/useFilters';
import PaginationContainer from '../sections/doctors/PaginationContainer';

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

  const handleFiltersChange = (e, select) => {
    setFilters((filters) => ({
      ...filters,
      page: 1,
      [`${select}`]: e.target.value,
    }));
  };

  return (
    <Paper elevation={1}>
    <Page title="Dashboard: Doctors">
      <Container sx={{ padding: '40px', minWidth: '320px' }}>
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
          <Box
            sx={{
              paddingTop: '40px',
              display: 'flex',
              gap: '50px',
              flexWrap: 'wrap',
            }}
          >
            {results.data?.list.map((doctor) => (
              <Paper
                elevation={3}
                sx={{ padding: '20px', maxWidth: '300px' }}
                key={doctor.phoneNumber}
              >
                <Box>First Name: {doctor.firstName}</Box>
                <Box>Last Name: {doctor.lastName}</Box>
                <Box>Speciality: {doctor.speciality}</Box>
                <Box>Classification: {doctor.classification}</Box>
                <Box>Experience: {doctor.experience}</Box>
                <Box>Grade: {doctor.grade}</Box>
                <Box>Hospitals: {doctor.hospitals.join(', ')}</Box>
                <Box>Price: {doctor.price}</Box>
                <Box>Rating: {doctor.rating}</Box>
                <Box>Phone Number: {doctor.phoneNumber}</Box>
              </Paper>
            ))}
          </Box>
        )}
        <PaginationContainer filters={filters} setFilters={setFilters} />
      </Container>
    </Page>
    </Paper>
  );
}

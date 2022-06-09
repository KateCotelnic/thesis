import Box from '@mui/material/Box';
import Pagination from './Pagination';
import { useFilters } from './useFilters';

const PaginationContainerStyle = {
  display: 'block',
  width: 'fit-content',
  margin: '0 auto',
  marginTop: 4,
};

const PaginationContainer = ({ filters, setFilters }) => {
  const response = useFilters(filters);

  const totalResultsCount = response.data?.totalElements || 0;

  const pagesCount = response.data?.totalPages;

  const currentPage = filters.page;

  const handleChange = (e, page) => {
    if (currentPage !== page) {
      setFilters({ ...filters, page });
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    }
  };

  return totalResultsCount > 0 ? (
    <Box sx={PaginationContainerStyle}>
      <Pagination
        handleChange={handleChange}
        pagesCount={pagesCount || 0}
        page={currentPage}
        siblingCount={1}
      />
    </Box>
  ) : null;
};

export default PaginationContainer;

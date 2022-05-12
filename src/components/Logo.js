import PropTypes from 'prop-types';
import { Link as RouterLink } from 'react-router-dom';
// @mui
import { useTheme } from '@mui/material/styles';
import { Box } from '@mui/material';

// ----------------------------------------------------------------------

Logo.propTypes = {
  disabledLink: PropTypes.bool,
  sx: PropTypes.object,
};

export default function Logo({ disabledLink = false, sx }) {
  const theme = useTheme();

  const PRIMARY_LIGHT = theme.palette.primary.light;

  const PRIMARY_MAIN = theme.palette.primary.main;

  const PRIMARY_DARK = theme.palette.primary.dark;

  // OR
  // const logo = <Box component="img" src="static/icon.svg" sx={{ width: 30, height: 30, ...sx }} />

  const logo = (
    <Box sx={{ width: 40, height: 40, ...sx }}>
      <svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" clip-rule="evenodd" d="M21.671 6.80092C23.4182 5.73302 25.6603 5.73303 27.4076 6.80092L29.4455 8.04646C32.3025 9.79264 35.5581 10.1824 38.8277 10.5739C39.8847 10.7005 40.9432 10.8272 41.9901 11C41.9901 11 42.0132 12.8335 41.9889 13.6659L41.78 20.817C41.6008 26.9498 38.8735 32.7654 34.1951 36.991C31.988 38.9844 29.7054 40.7851 27.0825 42.2813C25.5274 43.1684 23.6035 43.2387 21.9814 42.4676C18.953 41.028 16.271 39.2023 13.8227 36.991C9.14422 32.7654 6.41696 26.9498 6.23778 20.817L6.02952 13.6887C6.00485 12.8445 6.02952 11 6.02952 11C6.8795 10.8996 7.74583 10.8173 8.61872 10.7344C12.4722 10.3682 16.4534 9.98986 19.7195 7.99367L21.671 6.80092ZM21.9998 22V15H25.9998V22H32.9998V26H25.9998V33H21.9998V26H14.9998V22H21.9998Z" fill="#333333"/>
      </svg>
    </Box>
  );


  if (disabledLink) {
    return <>{logo}</>;
  }

  return <RouterLink to="/">{logo}</RouterLink>;
}

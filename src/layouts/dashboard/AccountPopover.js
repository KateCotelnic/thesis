import { useEffect, useRef, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
// @mui
import { alpha } from '@mui/material/styles';
import { Box, Divider, Typography, Stack, MenuItem, Avatar, IconButton } from '@mui/material';
import axios from 'axios';
// components
import MenuPopover from '../../components/MenuPopover';
// mocks_
import account from '../../_mock/account';
import { api } from '../../api';
import FormDialog from '../../pages/ChangePasswordDialog';

// ----------------------------------------------------------------------

const MENU_OPTIONS = [
  {
    label: 'Profile',
    icon: 'eva:person-fill',
    linkTo: '#',
  },
  {
    label: 'Change password',
    icon: 'eva:person-fill',
    linkTo: '#',
  },
];

// ----------------------------------------------------------------------

function stringToColor(string) {
  let hash = 0;
  let i;

  /* eslint-disable no-bitwise */
  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = '#';

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }
  /* eslint-enable no-bitwise */

  return color;
}

function stringAvatar(name) {
  return {
    sx: {
      bgcolor: stringToColor(name),
    },
    children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
  };
}

export default function AccountPopover() {
  const anchorRef = useRef(null);

  const [open, setOpen] = useState(null);
  const [modal, setModal] = useState(false);
  const [profile, setProfile] = useState([]);

  const accessToken = localStorage.getItem("token");
  const role = localStorage.getItem("role") === 'DOCTOR';
  const urlD = api.dashboard.getDoctorData();
  const urlP = api.dashboard.getPatientData();
  const nav = role ? urlD : urlP;

  useEffect(async () => {
    const result = await axios(
      nav, { params: { email: localStorage.getItem("email") }, headers: {"Authorization": accessToken}}
    );
    setProfile(result.data);
  }, [])


  const refreshPage = ()=>{
    window.location.reload();
  }

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    refreshPage();
  };

  const profileAvatar = `${profile.firstName} ${profile.lastName}`

  const handleOpen = (event) => {
    setOpen(event.currentTarget);
  };


  const handleClose = () => {
    setOpen(null);
  };

  return (
    <>
      <IconButton
        ref={anchorRef}
        onClick={handleOpen}
        sx={{
          p: 0,
          ...(open && {
            '&:before': {
              zIndex: 1,
              content: "''",
              width: '100%',
              height: '100%',
              borderRadius: '50%',
              position: 'absolute',
              bgcolor: (theme) => alpha(theme.palette.grey[900], 0.8),
            },
          }),
        }}
      >
        <Avatar {...stringAvatar(profileAvatar)} />
      </IconButton>

      <MenuPopover
        open={Boolean(open)}
        anchorEl={open}
        onClose={handleClose}
        sx={{
          p: 0,
          mt: 1.5,
          ml: 0.75,
          '& .MuiMenuItem-root': {
            typography: 'body2',
            borderRadius: 0.75,
          },
        }}
      >
        <Box sx={{ my: 1.5, px: 2.5 }}>
          <Typography variant="subtitle2" noWrap>
            {profileAvatar}
          </Typography>
          <Typography variant="body2" sx={{ color: 'text.secondary' }} noWrap>
            {localStorage.getItem("email")}
          </Typography>
        </Box>

        <Divider sx={{ borderStyle: 'dashed' }} />

        <Stack sx={{ p: 1 }}>
          {/* {MENU_OPTIONS.map((option) => ( */}
          {/*  <MenuItem key={option.label} to={option.linkTo} component={RouterLink} onClick={handleClose}> */}
          {/*    {option.label} */}
          {/*  </MenuItem> */}
          {/* ))} */}
          <MenuItem onClick={() => setModal(true)}>
            {modal && <FormDialog
              setOpen={setModal}
            />}
            Change password
          </MenuItem>
        </Stack>

        <Divider sx={{ borderStyle: 'dashed' }} />

        <MenuItem onClick={logout} sx={{ m: 1 }}>
          Logout
        </MenuItem>
      </MenuPopover>
    </>
  );
}

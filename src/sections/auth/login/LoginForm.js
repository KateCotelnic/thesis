import * as Yup from 'yup';
import { useState } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { useFormik, Form, FormikProvider } from 'formik';
import axios from "axios";
// material
import { Link, Stack, Checkbox, TextField, IconButton, InputAdornment, FormControlLabel } from '@mui/material';
import { LoadingButton } from '@mui/lab';
import { api } from '../../../api';
// component
import Iconify from '../../../components/Iconify';


async function loginUser(credentials) {
  return axios.post( api.login(), credentials )
    .then( ( response ) => {
      localStorage.setItem( "logined", "1" );
      localStorage.setItem( "role", response.data.role );
      localStorage.setItem( "token", response.data.token );
      // window.location = "/dashboard";
    } )
    .catch( error => {
      alert( error.response.data.message );
    } );
}

export default function LoginForm() {
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  const handleSubmit = async e => {
    e.preventDefault();
    const response = await loginUser({
      email,
      password
    });
    console.log(response)
    if ('token' in localStorage) {
          localStorage.setItem("email", email);
          window.location.href = "/dashboard/app";
    } else {
      alert("Unauthorized");
    }
  }

  const LoginSchema = Yup.object().shape({
    email: Yup.string().email('Email must be a valid email address').required('Email is required'),
    password: Yup.string().required('Password is required'),
  });

  const formik = useFormik({
    initialValues: {
      email: '',
      password: '',
      remember: true,
    },
    validationSchema: LoginSchema,
    // -----------------------------------------------------------------------
    onSubmit: () => {
      navigate('/dashboard', { replace: true });
    },
    // -----------------------------------------------------------------------
  });

  const { errors, touched, values, isSubmitting, getFieldProps } = formik;

  const handleShowPassword = () => {
    setShowPassword((show) => !show);
  };

  return (
    <FormikProvider value={formik}>
      <Form autoComplete="off" noValidate onSubmit={handleSubmit}>
        <Stack spacing={3}>
          <TextField
            fullWidth
            autoComplete="email"
            type="email"
            label="Email address"
            error={Boolean(touched.email && errors.email)}
            helperText={touched.email && errors.email}
            onChange={e => setEmail(e.target.value)}
          />

          <TextField
            fullWidth
            autoComplete="current-password"
            type={showPassword ? 'text' : 'password'}
            label="Password"
            onChange={e => setPassword(e.target.value)}
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton onClick={handleShowPassword} edge="end">
                    <Iconify icon={showPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
                  </IconButton>
                </InputAdornment>
              ),
            }}
            error={Boolean(touched.password && errors.password)}
            helperText={touched.password && errors.password}
          />
        </Stack>

        <Stack direction="row" alignItems="center" justifyContent="space-between" sx={{ my: 2 }}>
          {/* <FormControlLabel */}
          {/*  control={<Checkbox {...getFieldProps('remember')} checked={values.remember} />} */}
          {/*  label="Remember me" */}
          {/* /> */}

          <Link  component={RouterLink} to="/register" variant="subtitle2" underline="hover">
            Register
          </Link>
        </Stack>

        <LoadingButton fullWidth size="large" type="submit" variant="contained" loading={isSubmitting}>
          Login
        </LoadingButton>
      </Form>
    </FormikProvider>
  );
}

// {...getFieldProps('email')}
// {...getFieldProps('password')}

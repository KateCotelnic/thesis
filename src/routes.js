import { Navigate, useRoutes } from 'react-router-dom';
// layouts
import DashboardLayout from './layouts/dashboard';
import LogoOnlyLayout from './layouts/LogoOnlyLayout';
//
import Blog from './pages/Blog';
import User from './pages/User';
import Login from './pages/Login';
import NotFound from './pages/Page404';
import Register from './pages/Register';
import Products from './pages/Products';
import Calendar from './pages/DashboardApp';
// import DashboardApp from './pages/DashboardApp';
import Doctors from './pages/Doctors';
import Patients from './pages/Patients';
import Test from './pages/Test';
import Test2 from './pages/Test2';
import { Profile } from './pages/Profile';
import CalendarPatient from './pages/CalendarPatient';
import { ProfilePatient } from './pages/ProfilePatient';
import { AddApp } from './pages/AddAppointment';
// ----------------------------------------------------------------------

export default function Router() {
  return useRoutes([
    {
      path: '/dashboard',
      element: <DashboardLayout />,
      children: [
        { path: 'app', element: <Calendar /> },
        { path: 'patients', element: <Test2 /> },
        { path: 'home', element: <Products /> },
        { path: 'blog', element: <Blog /> },
        { path: 'profile', element: <Profile />},
        { path: 'calendarpat', element: <CalendarPatient />},
        { path: 'profilepat', element: <ProfilePatient />},
        { path: 'doctorslist', element: <Doctors />},
        { path: 'addappointment', element: <AddApp />}

      ],
    },
    {
      path: '/',
      element: <LogoOnlyLayout />,
      children: [
        { path: '/', element: <Navigate to="/dashboard/app" /> },
        { path: 'login', element: <Login /> },
        { path: 'register', element: <Register /> },
        { path: 'doctors', element: <Doctors /> },
        { path: '404', element: <NotFound /> },
        { path: '*', element: <Navigate to="/404" /> },
      ],
    },
    { path: '*', element: <Navigate to="/404" replace /> },
  ]);
}

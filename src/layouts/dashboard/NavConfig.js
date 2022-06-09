// component
import Iconify from '../../components/Iconify';

// ----------------------------------------------------------------------

const getIcon = (name) => <Iconify icon={name} width={22} height={22} />;
const userRole = localStorage.getItem("role");
const token = localStorage.getItem("token");

const navConfigGuest = [
  {
    title: 'Home',
    path: '/dashboard/home',
    icon: getIcon('eva:home-fill'),
  },
  // {
  //   title: 'blog',
  //   path: '/dashboard/blog',
  //   icon: getIcon('eva:file-text-fill'),
  // },
  {
    title: 'login',
    path: '/login',
    icon: getIcon('eva:lock-fill'),
  },
  {
    title: 'register',
    path: '/register',
    icon: getIcon('eva:person-add-fill'),
  },
  // {
  //   title: 'Not found',
  //   path: '/404',
  //   icon: getIcon('eva:alert-triangle-fill'),
  // },
];

const navConfigDoctor = [
  {
    title: 'calendar',
    path: '/dashboard/app',
    icon: getIcon('bi:calendar-check-fill'),
  },
  {
    title: 'patients',
    path: '/dashboard/patients',
    icon: getIcon('eva:people-fill'),
  }
];

const navConfigPatient = [
  {
    title: 'calendar',
    path: '/dashboard/app',
    icon: getIcon('bi:calendar-check-fill'),
  },
  {
    title: 'patients',
    path: '/dashboard/patients',
    icon: getIcon('eva:people-fill'),
  }
];

// const role = localStorage.getItem("role") === 'DOCTOR';
const role = localStorage.getItem("role") === 'DOCTOR';
const nav = role ? navConfigDoctor : navConfigPatient;

export default nav;



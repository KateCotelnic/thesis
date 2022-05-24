import { Navigate, useLocation } from 'react-router-dom';
import Router from './routes';
import ThemeProvider from './theme';
// components
import ScrollToTop from './components/ScrollToTop';
import { BaseOptionChartStyle } from './components/chart/BaseOptionChart';
import Login from './pages/Login';


// ----------------------------------------------------------------------

// function PrivateRoute( props ) {
//   let location = useLocation();
//   const { logined, children } = props;
//   if( logined === false ) {
//     return <Navigate to="/login" state={ { from: location } } replace />;
//   }
//   return children;
// }
// function PublicRoute( props ) {
//   let location = useLocation();
//   const { logined, children } = props;
//   if( logined === true ) {
//     return <Navigate to="/dashboard" state={ { from: location } } replace />;
//   }
//   return children;
// }

export default function App() {

  const token = localStorage.getItem('token');
  const logined = localStorage.getItem("logined") === "1";

  if(!token) {
    return <Login />
  }

  return (
    <ThemeProvider>
      <ScrollToTop />
      <BaseOptionChartStyle />
      <Router />
    </ThemeProvider>
  );
}

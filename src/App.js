import { Navigate, useLocation } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import Router from './routes';
import ThemeProvider from './theme';
// components
import ScrollToTop from './components/ScrollToTop';
import { BaseOptionChartStyle } from './components/chart/BaseOptionChart';
import Login from './pages/Login';
import Products from './pages/Products';

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
  const queryClient = new QueryClient();

  const token = localStorage.getItem('token');

  if(!token) {
    return <Login />
  }

  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider>
        <ScrollToTop />
        <BaseOptionChartStyle />
        <Router />
      </ThemeProvider>
    </QueryClientProvider>
  );
}

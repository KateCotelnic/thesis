import logo from './logo.svg';
import './App.css';
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import React, { Component } from "react";
import { BrowserRouter, Routes, Route, Navigate, useLocation } from "react-router-dom";

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

function PrivateRoute( props ) {
  let location = useLocation();
  const { isLogined, children } = props;
  if( isLogined === false ) {
    // Redirect them to the /login page, but save the current location they were
    // trying to go to when they were redirected. This allows us to send them
    // along to that page after they login, which is a nicer user experience
    // than dropping them off on the home page.
    return <Navigate to="/login" state={ { from: location } } replace />;
  }
  return children;
}
function PublicRoute( props ) {
  let location = useLocation();
  const { isLogined, children } = props;
  if( isLogined === true ) {
    return <Navigate to="/dashboard" state={ { from: location } } replace />;
  }
  return children;
}

class App extends Component {

  constructor( props ) {
    super( props );
    this.state = {
      isLogined: localStorage.getItem( "isLogined" ) === "1" ? true : false,
      email: localStorage.getItem( "email" ) !== null ? localStorage.getItem( "email" ) : "",
    }
  }

  login = ( email, token ) => {
    this.setState( { isLogined: true, email: email } );
    localStorage.setItem( "isLogined", "1" );
    localStorage.setItem( "email", email );
    localStorage.setItem( "token", token );
  }
  logout = () => {
    localStorage.setItem( "isLogined", "0" );
    localStorage.setItem( "email", "" );
    localStorage.setItem( "token", "" );
    this.setState( { isLogined: false, email: ""} );
  }

  render() {
    return (
          <BrowserRouter>
            <Routes>
              <Route path="/">
                <Route index element={ <PublicRoute isLogined={ this.state.isLogined }><Login login={ this.login }/></PublicRoute> } />
                <Route path="login" element={ <PublicRoute isLogined={ this.state.isLogined }><Login login={ this.login }/></PublicRoute> } />
              </Route>
              <Route path="dashboard" element={ <PrivateRoute isLogined={ this.state.isLogined }><Dashboard logout={ this.logout }/></PrivateRoute> }/>
              <Route path="*" element={ <Navigate to="/"/> } />
            </Routes>
          </BrowserRouter>
    );
  }
}

export default App;

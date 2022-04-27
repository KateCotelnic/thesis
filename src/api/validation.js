import axios from "axios";

const valid = axios.create( { withCredentials: true } );
valid.interceptors.response.use(
    function( response ) { return response; },
    function( error ) {
        if ( error.response.status === 401 ) {
            localStorage.setItem( "isLogined", "0" );
            window.location.reload();
        } else {
            return Promise.reject( error );
        }
    }
);
export default valid;
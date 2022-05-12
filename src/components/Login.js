import React, { Component } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import valid from '../api/validation';
import { api } from '../api/api';
import axios from "axios";

const classes = ( theme ) => ( {
    root : {
        backgroundColor: "#70a63a",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height : "inherit", width : "100%",
        "& .Mui-focused" : { color: '#2c3956' },
        '& .MuiOutlinedInput-input': {
            paddingLeft: 15,
            borderRadius: 6,
            height: 51,
            paddingTop: 0,
            paddingBottom: 0
        },
        "& .MuiOutlinedInput-root" : {
            height : 51,
            "& fieldset" : { borderRadius: 6, borderColor: '#70a63a' },
            '&:hover fieldset': { border: '2px solid #70a63a' },
            '&.Mui-focused fieldset': { border: '2px solid #70a63a' }
        },
    },
    dialog : {
        backgroundColor: "white",
        display : "flex",
        flexDirection : "column",
        alignItems : "center",
        justifyContent : "center",
        width: 400,
        height: 300,
        borderRadius: 15
    },
    textfield : {  marginTop : 25, width : 289 },
    button : {
        marginTop : 10, width : 289, backgroundColor: "#70a63a",
        "&:hover": { backgroundColor: "#e36c27" },
        "& .MuiButton-label" : { textTransform : "uppercase", color : "white", fontSize : 16 },
    },
    logo : { marginBottom: 32 },
    flashMessage : { position : "absolute", display : "flex", flexDirection : "row-reverse", alignItems : "center", width : "100%", top : 0, height : 51, backgroundColor : "#fd000b" },
    flashMessageText : { marginRight : 20, color : "white", fontSize : 15, marginLeft : 20 }
} );

class Login extends Component {
    constructor( props ) {
        super( props );
        this.state = {
            email : "",
            password : ""
        }
        document.title = "Login";
    }
    login = () => {
        if( this.state.email === "" || this.state.password === "" ) {
            alert("Login or password field can't be empty!");
            return;
        }
        const data = {
            email: this.state.email,
            password: this.state.password
        }
        axios.post( "http://localhost:8090/api/login", data )
            .then( ( response ) => {
                localStorage.setItem( "token", response.data.token );
                console.log("Success! Data is ", response.data)
            } )
            .catch( error => {
                alert( error.response.data.message );
            } );
    }

    email_Change = ( event ) => {
        this.setState( { email: event.target.value } );
    }
    password_Change = ( event ) => {
        this.setState( { password: event.target.value } );
    }

    handleKeyPress = ( event ) => {
        if( event.key === "Enter" ) {
            if( this.state.email === "" || this.state.password === "" ) {
                alert("Login or password field can't be empty!");
                return;
            }
            this.login();
        }
    }

    getTest = () => {
        console.log("test")
        axios
            .get( "http://localhost:8090/api/login/hi")
            .then( ( response ) => {
                const testString = response.data;
                    console.log(testString);
            } );
    }

    render() {
        return (
            <div>
                {
                    this.props.loginError
                        ?
                        <div>
                            <div>{ this.props.loginError }</div>
                        </div>
                        : null
                }
                <form>
                    <div style={{ fontWeight: "bold"}}>Welcome to eHealth</div>
                    <TextField
                        autoComplete="email"
                        label="Email"
                        variant="outlined"
                        onChange = { this.email_Change }
                        InputLabelProps={{ shrink: true }}
                    />
                    <TextField
                        label="Password"
                        type="password"
                        autoComplete="current-password"
                        variant="outlined"
                        onChange = { this.password_Change }
                        onKeyPress={this.handleKeyPress}
                        InputLabelProps={ { shrink : true } }
                    />
                    <div style={ { marginTop: 20, textAlign: 'center' } }>
                        <Button onClick= { this.login }>Login</Button>
                        <Button onClick= { this.getTest }>Test</Button>
                    </div>
                </form>
            </div>
        );
    }
}
export default Login;

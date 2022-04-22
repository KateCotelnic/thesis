#Requests from frontend to server:

## Authentication

&nbsp; **login doctor**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/login/doctor
<br/> request body: 
{
"email": "mail@mail.com",
"password": "pas"
}
<br/> response body:
{
"email": "mail@mail.com",
"token": "eyJhbGc...uYP5Y"
}

&nbsp; **login patient**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/login/patient
<br/> request body:
{
"email": "mail@mail.com",
"password": "pas"
}
<br/> response body:
{
"email": "mail@mail.com",
"token": "eyJhbGc...uYP5Y"
}

######requests between services (for server)
MS -> RS
<br/>
<br/> method: GET
<br/> URL: http://localhost:8091/rs/getUserByEmail?email=mail@mail.com
<br/> response body contains:
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String email;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String password;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String role;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private boolean isEnable;

## Registration

&nbsp; **register patient**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/register
<br/> request body:
{
"email": "mail@mail.com",
"password": "pas"
}
<br/> response body:
{
"email": "mail@mail.com",
"token": "eyJhbGc...uYP5Y"
} 

######requests between services (for server)
MS -> RS
<br/>
<br/> method: GET
<br/> URL: http://localhost:8091/rs/existUserByEmail?email=mail@mail.com
<br/> response body contains:
<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; String "yes" or "no"

<br/> method: POST
<br/> URL: http://localhost:8091/rs/insertUser
<br/> request body contains:
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String email;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String password;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String role;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private boolean isEnable;




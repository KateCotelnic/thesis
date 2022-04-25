#Requests from frontend to server:

All requests except authentication, registration and \\\\\\\\\\ should have a header with key "Authorization" and value token("eyJhb...IgC")

## Authentication

&nbsp; **login doctor**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/login/doctor
<br/> request body:
{
"email": "polly@email.com",
"password": "pp123"
}
<br/> response body:
{
"email": "polly@mail.com",
"token": "eyJhbGc...uYP5Y"
}

&nbsp; **login patient**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/login/patient
<br/> request body:
{
"email": "emily@email.com",
"password": "ee123"
}
<br/> response body:
{
"email": "emily@mail.com",
"token": "eyJhbGc...uYP5Y"
}

&nbsp; **login admin**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/login/patient
<br/> request body:
{
"email": "katie@email.com",
"password": "kk123"
}
<br/> response body:
{
"email": "katie@mail.com",
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
"email": "newuser@email.com",
"password": "nn123",
"firstName": "new",
"lastName": "user",
"phoneNumber": "039485853",
"middleName": "middle", (can be null)
"photo": "string"  (can be null)
}
<br/> response body:
{
"email": "newuser@mail.com",
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
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  private String password;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  private String role;
<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private boolean isEnable;
  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String firstName;
 <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    private String lastName;
  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String middleName;
  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String phoneNumber;
 <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    private String photo;



## Admin panel

&nbsp; **get all doctor**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/getDoctors
<br/> response body:


######requests between services (for server)


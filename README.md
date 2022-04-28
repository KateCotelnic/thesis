#Requests from frontend to server:

All requests except authentication, registration and for unauthorized user should have a header with key "Authorization" and value token("eyJhb...IgC").

## Authentication

&nbsp; **login doctor**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/login/doctor
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
<br/> URL: http://localhost:8090/api/login/patient
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
<br/> URL: http://localhost:8090/api/login/patient
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

[//]: # (######requests between services &#40;for server&#41;)

[//]: # (MS -> RS)

[//]: # (<br/>)

[//]: # (<br/> method: GET)

[//]: # (<br/> URL: http://localhost:8091/rs/getUserByEmail?email=mail@mail.com)

[//]: # (<br/> response body contains:)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String email;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String password;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String role;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private boolean isEnable;)


## Registration

&nbsp; **register patient**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/register
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

[//]: # (######requests between services &#40;for server&#41;)

[//]: # (MS -> RS)

[//]: # (<br/>)

[//]: # (<br/> method: GET)

[//]: # (<br/> URL: http://localhost:8091/rs/existUserByEmail?email=mail@mail.com)

[//]: # (<br/> response body contains:)

[//]: # (<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; String "yes" or "no")

[//]: # ()
[//]: # (<br/> method: POST)

[//]: # (<br/> URL: http://localhost:8091/rs/insertUser)

[//]: # (<br/> request body contains:)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private String email;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  private String password;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  private String role;)

[//]: # (<br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; private boolean isEnable;)

[//]: # (  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String firstName;)

[//]: # ( <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    private String lastName;)

[//]: # (  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String middleName;)

[//]: # (  <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   private String phoneNumber;)

[//]: # ( <br/> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    private String photo;)



## Admin panel

&nbsp; **get all doctor**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/admin/doctors
<br/> response body:
[
{
"email": "polly@email.com",
"firstName": "Polina",
"lastName": "Murphy",
"middleName": null,
"phoneNumber": "0948573035",
"speciality": "PSYCHIATRIST",
"price": "300",
"photo": null,
"grade": "FIRST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
},
{
"email": "tommy@email.com",
"firstName": "Tom",
"lastName": "Reyes",
"middleName": null,
"phoneNumber": "05948386785",
"speciality": "THERAPIST",
"price": "200",
"photo": null,
"grade": "HIGHEST",
"experience": "12",
"description": null,
"classification": "FAMILY",
"rating": "4.5",
"hospitals": [
"Medpark International Hospital",
"Repromed"
]
}
]

&nbsp; **get doctor by email**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/doctor?email=polly@email.com
<br/> response body:
{
"email": "polly@email.com",
"firstName": "Polina",
"lastName": "Murphy",
"middleName": null,
"phoneNumber": "0948573035",
"speciality": "PSYCHIATRIST",
"price": "300",
"photo": null,
"grade": "FIRST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
],
"comments": [],
"appointmentsDoctor": [
{
"hospital": "Medpark International Hospital",
"date": "2022-04-20T10:20",
"duration": "15",
"status": "DONE",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "0",
"phoneNumber": null
},
{
"hospital": "Medpark International Hospital",
"date": "2022-04-20T11:30",
"duration": "30",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "0",
"phoneNumber": null
}
]
}

&nbsp; **add new doctor**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/admin/newdoctor
<br/> request body:
{
"email": "newdoctor@email.com",
"password": "nn123",
"firstName": "New",
"lastName": "Doctor",
"middleName": null,
"photo": null,
"description": null,
"phoneNumber": "24325352",
"speciality": "PSYCHOLOGIST",
"price": "200",
"grade": "SECOND",
"experience": "10",
"classification": "CHILDREN",
"hospitals": [
"Repromed"
]
}
<br/> response body:
{
"email": "newdoctor@email.com",
"firstName": "New",
"lastName": "Doctor",
"middleName": null,
"phoneNumber": "24325352",
"speciality": "PSYCHOLOGIST",
"price": "200",
"photo": null,
"grade": "SECOND",
"experience": "10",
"description": null,
"classification": "CHILDREN",
"rating": "0.0",
"hospitals": [
"Repromed"
],
"comments": [],
"appointmentsDoctor": []
}

&nbsp; **delete a doctor**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/admin/doctor?email=newdoctor1@email.com
<br/> response body:
"OK"

&nbsp; **update the doctor**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/admin/updatedoctor
<br/> request body:
{
"email": "newdoctor2@email.com",
"firstName": "Updated",
"lastName": "Doctor",
"phoneNumber": "24325352",
"speciality": "PSYCHOLOGIST",
"price": "200",
"grade": "SECOND",
"experience": "10",
"classification": "CHILDREN",
"hospitals": [
"Repromed", "Medpark International Hospital"
]
}
<br/> response body:
{
"email": "newdoctor2@email.com",
"firstName": "Updated",
"lastName": "Doctor",
"middleName": null,
"phoneNumber": "24325352",
"speciality": "PSYCHOLOGIST",
"price": "200",
"photo": null,
"grade": "SECOND",
"experience": "10",
"description": null,
"classification": "CHILDREN",
"rating": "0.0",
"hospitals": [
"Repromed",
"Medpark International Hospital"
],
"comments": [],
"appointmentsDoctor": []
}

&nbsp; **get enums for updating and creating of new doctor**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/admin/enums
<br/> response body:
{
"specialities": [
"[ACUPUNCTURIST, OBSTETRICIAN_GYNECOLOGIST, ALLERGIST, ANDROLOGIST, VENEREOLOGIST, VERTEBROLOGIST, GASTROENTEROLOGIST, HEMATOLOGIST, HEPATOLOGIST, GYNECOLOGIST, DERMATOLOGIST, NUTRITIONIST, INFECTIONIST, CARDIOLOGIST, KINESITHERAPIST, COSMETOLOGIST, MAMMOLOGIST, MASSEUR, NEUROLOGIST, NEUROSURGEON, NEPHROLOGIST, ONCOLOGIST, ORTHOPEDIST_TRAUMATOLOGIST, OTOLARYNGOLOGIST, OPHTHALMOLOGIST, PEDIATRICIAN, PLASTIC_SURGEON, PROCTOLOGIST, PSYCHIATRIST, PSYCHO_NEUROLOGIST, PSYCHOLOGIST, PSYCHOTHERAPIST, PULMONOLOGIST, RADIOLOGIST, REHABILITOLOGIST, RHEUMATOLOGIST, REPRODUCTOLOGIST, REFLEXOLOGIST, SEXOLOGIST, FAMILY_DOCTOR, DENTIST, THERAPIST, TRICHOLOGIST, ULTRASOUND_SPECIALIST, UROLOGIST, PHYSIOTHERAPIST, PHYTOTHERAPIST, PHLEBOLOGIST, SURGEON, ENDOCRINOLOGIST, ENDOSCOPIST, AESTHETIC_SURGEON]"
],
"grades": [
"[FIRST, SECOND, HIGHEST]"
],
"classifications": [
"[CHILDREN, ADULT, FAMILY]"
]
}


## Unauthorized user

## User authorized as patient

## User authorized as doctor
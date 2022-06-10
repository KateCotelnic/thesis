#Requests from frontend to server:

All requests except authentication, registration and for unauthorized user should have a header with key "Authorization" and value token("eyJhb...IgC").

## Authentication

&nbsp; **login**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/login
<br/> request body:
{
"email": "ecaterina.cotelnic@isa.utm.md",
"password": "ee123"
}
<br/> response body:
{
"email": "ecaterina.cotelnic@isa.utm.md",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlY2F0ZXJpbmEuY290ZWxuaWNAaXNhLnV0bS5tZCIsInJvbGUiOiJQQVRJRU5UIiwiaWF0IjoxNjU0ODY1NDQ4LCJleHAiOjE2NTU0NzAyNDh9.Oasoqxrhupiwlvk270BOzyZU3Du-KKiMnOKDCabPPY0",
"role": "PATIENT",
"firstName": "Ecaterina",
"lastName": "Cotelnic",
"middleName": ""
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
"email": "newuser@email.com",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd1c2VyQGVtYWlsLmNvbSIsInJvbGUiOiJQQVRJRU5UIiwiaWF0IjoxNjU0ODY1NTM5LCJleHAiOjE2NTU0NzAzMzl9.yZHzBbId-rSKUyvUd1b7zX9IkNzvg7i8fgt9aS_oLr8",
"role": "PATIENT",
"firstName": "new",
"lastName": "user",
"middleName": "middle"
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

&nbsp; **get current user/ admin details**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/details
<br/> response body:
{
"firstName": "Catherine",
"lastName": "Cot",
"middleName": "",
"age": "27",
"phoneNumber": "049584732",
"photo": null,
"appointments": []
}

&nbsp; **change password**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/changePassword
<br/> request body:
{
"oldPassword" : "ee123",
"newPassword": "ee1234"
}
<br/> response body:
"OK"

&nbsp; **get all doctors**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/admin/doctors?page=2
<br/> response body:
{
"list": [
{
"email": "holli@gmail.com",
"firstName": "Holli",
"lastName": "Wheatley",
"middleName": "",
"phoneNumber": "069887409",
"speciality": "DENTIST",
"price": "500",
"photo": null,
"grade": "HIGHEST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
},
{
"email": "bonnie@gmail.com",
"firstName": "Bonnie",
"lastName": "Boyce",
"middleName": "",
"phoneNumber": "068765498",
"speciality": "UROLOGIST",
"price": "200",
"photo": null,
"grade": "SECOND",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": []
},
{
"email": "felicity@gmail.com",
"firstName": "Felicity",
"lastName": "Hurley",
"middleName": "",
"phoneNumber": "069890945",
"speciality": "ENDOCRINOLOGIST",
"price": "230",
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
"email": "rosalind@gmail.com",
"firstName": "Rosalind",
"lastName": "Dillard",
"middleName": "",
"phoneNumber": "069895432",
"speciality": "ONCOLOGIST",
"price": "300",
"photo": null,
"grade": "HIGHEST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": []
},
{
"email": "zakifor@gmail.com",
"firstName": "Zaki",
"lastName": "Foreman",
"middleName": "",
"phoneNumber": "069543209",
"speciality": "PULMONOLOGIST",
"price": "300",
"photo": null,
"grade": "SECOND",
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
"middleName": "",
"phoneNumber": "079857463",
"speciality": "THERAPIST",
"price": "100",
"photo": null,
"grade": "HIGHEST",
"experience": "12",
"description": null,
"classification": "FAMILY",
"rating": "0.0",
"hospitals": []
}
],
"totalPages": 2,
"totalElements": 16
}

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
"comments": [
{
"id": "20",
"body": "body",
"rating": "4",
"date": "2023-06-20",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "21",
"body": "updated comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "23",
"body": "body of new comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
}
],
"appointmentsDoctor": [
{
"id": "13",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 10:20",
"duration": "15",
"status": "DONE",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "14",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 11:30",
"duration": "30",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "17",
"hospital": "Medpark International Hospital",
"date": "2022-07-20 10:20",
"duration": "30",
"status": "REQUESTED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "19",
"hospital": "Medpark International Hospital",
"date": "2023-06-20 10:20",
"duration": "60",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "18",
"hospital": "Medpark International Hospital",
"date": "2022-06-20 10:20",
"duration": "60",
"status": "APPROVED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
}
],
"freeTime": [
{
"id": "25",
"cronExpression": "0 0 9-17 * * MON-FRI"
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
"appointmentsDoctor": [],
"freeTime": []
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
],
"hospitals": [
"Medpark International Hospital",
"Repromed"
]
}

&nbsp; **get all hospitals**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/hospitals?page=1
<br/> response body:
{
"list": [
{
"hospitalName": "Medpark International Hospital",
"cityArea": "CIOCANA",
"photo": null,
"phoneNumber": "022 400 040",
"website": "https://medpark.md/en",
"address": "Andrei Doga 24 street"
},
{
"hospitalName": "Repromed",
"cityArea": "BOTANICA",
"photo": null,
"phoneNumber": null,
"website": null,
"address": "Strada Cuza Vodă 29/1"
}
],
"totalPages": 1,
"totalElements": 2
}

&nbsp; **create new hospital**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/admin/newhospital
<br/> request body:
{
"hospitalName": "New Hospital",
"cityArea": "CENTRU",
"photo": null,
"phoneNumber": null,
"website": null,
"address": "address"
}
<br/> response body:
{
"hospitalName": "New Hospital",
"cityArea": "CENTRU",
"photo": null,
"phoneNumber": null,
"website": null,
"address": "address"
}

&nbsp; **delete a hospital**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/admin/hospital?hospitalName=New Hospital1
<br/> response body:
"OK"

&nbsp; **update the hospital**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/admin/updatehospital
<br/> request body:
{
"hospitalName": "New Hospital",
"cityArea": "CENTRU",
"photo": "photo",
"phoneNumber": null,
"website": null,
"address": "address"
}
<br/> response body:
{
"hospitalName": "New Hospital",
"cityArea": "CENTRU",
"photo": "photo",
"phoneNumber": null,
"website": null,
"address": "address"
}

&nbsp; **get enums for creating and updating hospitals**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/admin/hospitalEnums
<br/> response body:
{
"areas": [
"[BOTANICA, CENTRU, CIOCANA, BUIUCANI, RISCANI]"
]
}

&nbsp; **get comments by doctor email**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/commentsByDoctor?doctorEmail=tommy@email.com
<br/> response body:
[
{
"id": "1",
"body": "I liked the consultation, it went quickly and efficiently.",
"rating": "5",
"date": "2021-11-28",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": "",
"photoPatient": null
},
{
"id": "2",
"body": null,
"rating": "4",
"date": "2020-03-12",
"firstNamePatient": "Ecaterina",
"lastNamePatient": "Cotelnic",
"middleNamePatient": "",
"photoPatient": null
}
]

&nbsp; **delete comment**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/patient/deleteComment?id=22
<br/> response body:
"OK"

## Unauthorized user

&nbsp; **get all hospitals**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/hospitals?page=1
<br/> response body:
{
"list": [
{
"hospitalName": "Medpark International Hospital",
"cityArea": "CIOCANA",
"photo": null,
"phoneNumber": "022 400 040",
"website": "https://medpark.md/en",
"address": "Andrei Doga 24 street"
},
{
"hospitalName": "Repromed",
"cityArea": "BOTANICA",
"photo": null,
"phoneNumber": null,
"website": null,
"address": "Strada Cuza Vodă 29/1"
}
],
"totalPages": 1,
"totalElements": 2
}

&nbsp; **get doctors by hospital**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/doctors?hospitalName=Repromed&page=1
<br/> response body:
{
"list": [
{
"email": "polina@gmail.com",
"firstName": "Polina",
"lastName": "Murphy",
"middleName": "",
"phoneNumber": "068493784",
"speciality": "PSYCHIATRIST",
"price": "300",
"photo": null,
"grade": "SECOND",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital",
"Repromed"
]
},
{
"email": "catalin.schiopu@isa.utm.md",
"firstName": "Catalin",
"lastName": "Schiopu",
"middleName": "",
"phoneNumber": "069857403",
"speciality": "PSYCHIATRIST",
"price": "330",
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
"email": "esmae@gmail.com",
"firstName": "Esmae",
"lastName": "Thompson",
"middleName": "",
"phoneNumber": "069253640",
"speciality": "HEMATOLOGIST",
"price": "250",
"photo": null,
"grade": "HIGHEST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
},
{
"email": "hopperlacy@gmail.com",
"firstName": "Lacy",
"lastName": "Hopper",
"middleName": "",
"phoneNumber": "069857102",
"speciality": "PLASTIC_SURGEON",
"price": "500",
"photo": null,
"grade": "SECOND",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
},
{
"email": "cherish@gmail.com",
"firstName": "Cherish",
"lastName": "Avila",
"middleName": "",
"phoneNumber": "069840583",
"speciality": "PEDIATRICIAN",
"price": "100",
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
"email": "kyandev@gmail.com",
"firstName": "Kyan",
"lastName": "Devlin",
"middleName": "",
"phoneNumber": "069482029",
"speciality": "RADIOLOGIST",
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
"email": "holli@gmail.com",
"firstName": "Holli",
"lastName": "Wheatley",
"middleName": "",
"phoneNumber": "069887409",
"speciality": "DENTIST",
"price": "500",
"photo": null,
"grade": "HIGHEST",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
},
{
"email": "felicity@gmail.com",
"firstName": "Felicity",
"lastName": "Hurley",
"middleName": "",
"phoneNumber": "069890945",
"speciality": "ENDOCRINOLOGIST",
"price": "230",
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
"email": "zakifor@gmail.com",
"firstName": "Zaki",
"lastName": "Foreman",
"middleName": "",
"phoneNumber": "069543209",
"speciality": "PULMONOLOGIST",
"price": "300",
"photo": null,
"grade": "SECOND",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital"
]
}
],
"totalPages": 1,
"totalElements": 9
}

&nbsp; **get doctors by area/speciality/classification (any combination is allowed)**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/doctors/param?area=BOTANICA&speciality=&classification=&page=1
<br/> response body:
{
"list": [
{
"email": "polina@gmail.com",
"firstName": "Polina",
"lastName": "Murphy",
"middleName": "",
"phoneNumber": "068493784",
"speciality": "PSYCHIATRIST",
"price": "300",
"photo": null,
"grade": "SECOND",
"experience": "3",
"description": "Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.",
"classification": "ADULT",
"rating": "0.0",
"hospitals": [
"Medpark International Hospital",
"Repromed"
]
}
],
"totalPages": 1,
"totalElements": 1
}

&nbsp; **get speciality, areas and classification are for search**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/searchenums
<br/> response body:
{
"areas": [
"BOTANICA",
"CENTRU",
"CIOCANA",
"BUIUCANI",
"RISCANI",
"TELECENTRU"
],
"specialities": [
"ACUPUNCTURIST",
"OBSTETRICIAN_GYNECOLOGIST",
"ALLERGIST",
"ANDROLOGIST",
"VENEREOLOGIST",
"VERTEBROLOGIST",
"GASTROENTEROLOGIST",
"HEMATOLOGIST",
"HEPATOLOGIST",
"GYNECOLOGIST",
"DERMATOLOGIST",
"NUTRITIONIST",
"INFECTIONIST",
"CARDIOLOGIST",
"KINESITHERAPIST",
"COSMETOLOGIST",
"MAMMOLOGIST",
"MASSEUR",
"NEUROLOGIST",
"NEUROSURGEON",
"NEPHROLOGIST",
"ONCOLOGIST",
"ORTHOPEDIST_TRAUMATOLOGIST",
"OTOLARYNGOLOGIST",
"OPHTHALMOLOGIST",
"PEDIATRICIAN",
"PLASTIC_SURGEON",
"PROCTOLOGIST",
"PSYCHIATRIST",
"PSYCHO_NEUROLOGIST",
"PSYCHOLOGIST",
"PSYCHOTHERAPIST",
"PULMONOLOGIST",
"RADIOLOGIST",
"REHABILITOLOGIST",
"RHEUMATOLOGIST",
"REPRODUCTOLOGIST",
"REFLEXOLOGIST",
"SEXOLOGIST",
"FAMILY_DOCTOR",
"DENTIST",
"THERAPIST",
"TRICHOLOGIST",
"ULTRASOUND_SPECIALIST",
"UROLOGIST",
"PHYSIOTHERAPIST",
"PHYTOTHERAPIST",
"PHLEBOLOGIST",
"SURGEON",
"ENDOCRINOLOGIST",
"ENDOSCOPIST",
"AESTHETIC_SURGEON"
],
"classifications": [
"CHILDREN",
"ADULT",
"FAMILY"
]
}

## User authorized as patient

&nbsp; **get current user details**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/details
<br/> response body:
{
"firstName": "Emily",
"lastName": "Muller",
"middleName": null,
"age": "17",
"phoneNumber": "3215153425",
"photo": null,
"appointments": 
{
"id": "13",
"hospital": "Medpark International Hospital",
"date": "2022-04-20T10:20",
"duration": "15",
"status": "DONE",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"id": "14",
"hospital": "Medpark International Hospital",
"date": "2022-04-20T11:30",
"duration": "30",
"status": "DECLINED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"id": "15",
"hospital": "Medpark International Hospital",
"date": "2022-05-10T14:00",
"duration": "60",
"status": "REQUESTED",
"firstNameDoctor": "Tom",
"lastNameDoctor": "Reyes",
"middleNameDoctor": null,
"emailDoctor": "tommy@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "THERAPIST",
"price": "200"
},
{
"id": "16",
"hospital": "Repromed",
"date": "2022-05-01T12:00",
"duration": "45",
"status": "APPROVED",
"firstNameDoctor": "Tom",
"lastNameDoctor": "Reyes",
"middleNameDoctor": null,
"emailDoctor": "tommy@email.com",
"phoneNumberHospital": null,
"address": "Strada Cuza Vodă 29/1",
"speciality": "THERAPIST",
"price": "200"
},
{
"id": "17",
"hospital": "Medpark International Hospital",
"date": "2022-07-20T10:20",
"duration": "30",
"status": "REQUESTED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"id": "18",
"hospital": "Medpark International Hospital",
"date": "2022-06-20T10:20",
"duration": "60",
"status": "REQUESTED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"id": "19",
"hospital": "Medpark International Hospital",
"date": "2023-06-20T10:20",
"duration": "60",
"status": "DECLINED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
}
]
}

&nbsp; **update current user details**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/patient/update
<br/> request body:
{
"firstName": "Emily",
"lastName": "Muller",
"middleName": null,
"age": "17",
"phoneNumber": "3215153425",
"photo": null
}
<br/> response body:
{
"firstName": "Emily",
"lastName": "Muller",
"middleName": null,
"age": "17",
"phoneNumber": "3215153425",
"photo": null,
"appointments": [
{
"hospital": "Medpark International Hospital",
"date": "2022-04-20T10:20",
"duration": "15",
"status": "DONE",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"hospital": "Medpark International Hospital",
"date": "2022-04-20T11:30",
"duration": "30",
"status": "DECLINED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
},
{
"hospital": "Medpark International Hospital",
"date": "2022-05-10T14:00",
"duration": "60",
"status": "REQUESTED",
"firstNameDoctor": "Tom",
"lastNameDoctor": "Reyes",
"middleNameDoctor": null,
"emailDoctor": "tommy@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "THERAPIST",
"price": "200"
},
{
"hospital": "Repromed",
"date": "2022-05-01T12:00",
"duration": "45",
"status": "APPROVED",
"firstNameDoctor": "Tom",
"lastNameDoctor": "Reyes",
"middleNameDoctor": null,
"emailDoctor": "tommy@email.com",
"phoneNumberHospital": null,
"address": "Strada Cuza Vodă 29/1",
"speciality": "THERAPIST",
"price": "200"
},
{
"hospital": "Medpark International Hospital",
"date": "2022-07-20T10:20",
"duration": "30",
"status": "REQUESTED",
"firstNameDoctor": "Polina",
"lastNameDoctor": "Murphy",
"middleNameDoctor": null,
"emailDoctor": "polly@email.com",
"phoneNumberHospital": "022 400 040",
"address": "Andrei Doga 24 street",
"speciality": "PSYCHIATRIST",
"price": "300"
}
]
}

&nbsp; **delete current user**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/delete
<br/> response body:
"OK"

&nbsp; **change password**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/changePassword
<br/> request body:
{
"oldPassword" : "ee123",
"newPassword": "ee1234"
}
<br/> response body:
"OK"

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
"comments": [
{
"id": "20",
"body": "body",
"rating": "4",
"date": "2023-06-20",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "21",
"body": "updated comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "23",
"body": "body of new comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
}
],
"appointmentsDoctor": [
{
"id": "13",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 10:20",
"duration": "15",
"status": "DONE",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "14",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 11:30",
"duration": "30",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "17",
"hospital": "Medpark International Hospital",
"date": "2022-07-20 10:20",
"duration": "30",
"status": "REQUESTED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "19",
"hospital": "Medpark International Hospital",
"date": "2023-06-20 10:20",
"duration": "60",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "18",
"hospital": "Medpark International Hospital",
"date": "2022-06-20 10:20",
"duration": "60",
"status": "APPROVED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
}
],
"freeTime": [
{
"id": "25",
"cronExpression": "0 0 9-17 * * MON-FRI"
}
]
}

&nbsp; **get comments by doctor email**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/commentsByDoctor?doctorEmail=tommy@email.com
<br/> response body:
[
{
"id": "7",
"body": "I liked the consultation, it went quickly and efficiently.",
"rating": "5",
"date": "2021-11-28",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "8",
"body": null,
"rating": "4",
"date": "2020-03-12",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"photoPatient": null
}
]

&nbsp; **create new comment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/patient/newComment
<br/> request body:
{
"body": "body of new comment",
"rating": "5",
"patientEmail": "jeffrey@email.com",
"doctorEmail": "polly@email.com"
}
<br/> response body:
{
"commentId": "23",
"body": "body of new comment",
"rating": "5",
"date": "2022-05-05",
"patientEmail": "jeffrey@email.com",
"doctorEmail": "polly@email.com"
}

&nbsp; **update comment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/patient/updateComment
<br/> request body:
{
"commentId": "21",
"body": "updated comment",
"rating": "5"
}
<br/> response body:
{
"commentId": "21",
"body": "updated comment",
"rating": "5",
"date": "2022-05-05",
"patientEmail": "jeffrey@email.com",
"doctorEmail": "polly@email.com"
}

&nbsp; **delete comment**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/deleteComment?id=22
<br/> response body:
"OK"

&nbsp; **create appointment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/patient/newAppointment
<br/> request body:
{
"doctorEmail": "catalin.schiopu@isa.utm.md",
"patientEmail": "ecaterina.cotelnic@isa.utm.md",
"hospitalName": "Medpark International Hospital",
"startDate": "2022-06-04 10:20",
"endDate": "2022-06-04 11:00"
}
<br/> response body:
{
"id": "14",
"doctorEmail": "catalin.schiopu@isa.utm.md",
"patientEmail": "ecaterina.cotelnic@isa.utm.md",
"hospitalName": "Medpark International Hospital",
"startDate": "2022-06-04T10:20",
"endDate": "2022-06-04T11:00",
"status": "REQUESTED",
"sentNotification": "false"
}

&nbsp; **get enums for creating an appointment**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/patient/getAppointmentEnums
<br/> response body:
{
"statuses": [
"[REQUESTED, APPROVED, DECLINED, DONE]"
]
}

&nbsp; **cancel appointment**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/patient/cancelAppointment?id=19
<br/> response body:
"OK"


## User authorized as doctor

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
"comments": [
{
"id": "20",
"body": "body",
"rating": "4",
"date": "2023-06-20",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "21",
"body": "updated comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"id": "23",
"body": "body of new comment",
"rating": "5",
"date": "2022-05-05",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
}
],
"appointmentsDoctor": [
{
"id": "13",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 10:20",
"duration": "15",
"status": "DONE",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "14",
"hospital": "Medpark International Hospital",
"date": "2022-04-20 11:30",
"duration": "30",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "17",
"hospital": "Medpark International Hospital",
"date": "2022-07-20 10:20",
"duration": "30",
"status": "REQUESTED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "19",
"hospital": "Medpark International Hospital",
"date": "2023-06-20 10:20",
"duration": "60",
"status": "DECLINED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
},
{
"id": "18",
"hospital": "Medpark International Hospital",
"date": "2022-06-20 10:20",
"duration": "60",
"status": "APPROVED",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"agePatient": "17",
"phoneNumber": null
}
],
"freeTime": [
{
"id": "25",
"cronExpression": "0 0 9-17 * * MON-FRI"
}
]
}

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
"appointmentsDoctor": [],
"freeTime": []
}

&nbsp; **delete current user**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/delete
<br/> response body:
"OK"

&nbsp; **change password**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/changePassword
<br/> request body:
{
"oldPassword" : "ee123",
"newPassword": "ee1234"
}
<br/> response body:
"OK"

&nbsp; **get comments by doctor email**
<br/>
<br/> method: GET
<br/> URL: http://localhost:8090/api/commentsByDoctor?doctorEmail=tommy@email.com
<br/> response body:
[
{
"body": "I liked the consultation, it went quickly and efficiently.",
"rating": "5",
"date": "2021-11-28",
"firstNamePatient": "Jeffrey",
"lastNamePatient": "Jef",
"middleNamePatient": null,
"photoPatient": null
},
{
"body": null,
"rating": "4",
"date": "2020-03-12",
"firstNamePatient": "Emily",
"lastNamePatient": "Muller",
"middleNamePatient": null,
"photoPatient": null
}
]

&nbsp; **send a request to delete comment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/doctor/deleteComments
<br/> request body:
{
"commentId": 21,
"reason": "not related comment"
}
<br/> response body:
"OK"

&nbsp; **accept appointment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/doctor/acceptAppointment?id=8
<br/> response body:
{
"id": "8",
"doctorEmail": "catalin.schiopu@isa.utm.md",
"patientEmail": "ecaterina.cotelnic@isa.utm.md",
"hospitalName": "Medpark International Hospital",
"startDate": "2022-06-10T12:00",
"endDate": "2022-06-10T12:20",
"status": "APPROVED",
"sentNotification": "true"
}

&nbsp; **decline appointment**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/doctor/declineAppointment?id=8
<br/> response body:
{
"id": "8",
"doctorEmail": "catalin.schiopu@isa.utm.md",
"patientEmail": "ecaterina.cotelnic@isa.utm.md",
"hospitalName": "Medpark International Hospital",
"startDate": "2022-06-10T12:00",
"endDate": "2022-06-10T12:20",
"status": "APPROVED",
"sentNotification": "true"
}

&nbsp; **add free time**
<br/>
<br/> method: POST
<br/> URL: http://localhost:8090/api/doctor/addFreeTime
<br/> request body:
{
"cronExpression": "0 0 9-17 * * MON-FRI"
}
<br/> response body:
{
"id": "27",
"cronExpression": "0 0 9-17 * * MON-FRI",
"doctorEmail": "polly@email.com"
}

&nbsp; **delete free time**
<br/>
<br/> method: DELETE
<br/> URL: http://localhost:8090/api/doctor/freeTime?id=26
<br/> response body:
"OK"
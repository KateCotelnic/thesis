import { usePatientsData } from './usePatientsData';

// ----------------------------------------------------------------------

export default function PatientsData() {
  const patientsData = usePatientsData();
  console.log(patientsData);

  const patients = patientsData.data;

  const patientsMap = patients.map((patient) => {
    return {
      id: patient.id,
      hospital: patient.hospital,
      startDate: new Date(patient.startDate),
      endDate: new Date(patient.endDate),
      status: patient.status,
      title: `${patient.firstNamePatient} ${patient.lastNamePatient}`,
      agePatient: patient.agePatient
    };
  })

  console.log(patientsMap);
}

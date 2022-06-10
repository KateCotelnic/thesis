const host = "http://localhost:8090/api";

export const api = {
  login: () => `${host}/login`,
  register: () => `${host}/register`,
  dashboard: {
    getDoctorData: () => `${host}/doctor`,
    getPatientData: () => `${host}/details`,
    postChangePassword: () => `${host}/changePassword`,
  },
}
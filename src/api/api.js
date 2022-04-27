//Insert your backend host
const host = "http://localhost:3000/";

//Insert your endpoints. I tested with /app/login
export const api = {
    app: {
        login: () => `${host}/app/login`,
        register: () => `${host}/app/register`
    }
    //dashboard: {}
}
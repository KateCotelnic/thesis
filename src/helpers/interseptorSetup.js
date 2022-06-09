import axios from "axios";
import { router } from "@/router";

const service = axios.create({
  baseURL: "http://localhost:8090/api",
  timeout: 5 * 1000,
});

service.interceptors.request.use(
  (config) => {
    config.headers["Access-Control-Allow-Origin"] = "*";
    config.headers["Access-Control-Allow-Credentials"] = "true";
    config.headers["Content-Type"] = "application/json";
    if (localStorage.getItem("currentUser")) {
      config.headers["Authorization"] = JSON.parse(
        localStorage.getItem("currentUser")
      ).token;
    }

    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);
service.interceptors.response.use(
  (response) => {
    if (response.status !== 200) {
      if (
        response.status === 508 ||
        response.status === 512 ||
        response.status === 514
      ) {
        localStorage.removeItem("currentUser");
        router.push("/login");
      }
      return Promise.reject(new Error(response.error || "Error"));
    } else {
      return response.data;
    }
  },
  (error) => {
    return Promise.reject(error.message);
  }
);

export default service;

import axios from "axios";

const { VITE_API_URL } = import.meta.env;

const instance = axios.create({
  baseURL: VITE_API_URL,
});

instance.defaults.headers.common["content-type"] = "application/json";

export { instance };

import { getToken } from "@/utils/oidcUtils";
import axios from "axios";

const { VITE_API_URL } = import.meta.env;

const getConfiguration = async () =>
  await axios.get(`${VITE_API_URL}/config`, {
    headers: {
      Authorization: `Bearer ${await getToken()}`,
      "content-type": "application/jwt",
    },
  });

export { getConfiguration };

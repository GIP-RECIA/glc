import { getToken } from "@/utils/oidcUtils";
import axios from "axios";

const { VITE_API_URL } = import.meta.env;

const getFonctions = async () =>
  await axios.get(`${VITE_API_URL}/fonction`, {
    headers: {
      Authorization: `Bearer ${await getToken()}`,
      "content-type": "application/jwt",
    },
  });

export { getFonctions };

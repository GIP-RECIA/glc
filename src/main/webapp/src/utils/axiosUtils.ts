import oidc from "@uportal/open-id-connect";
import axios from "axios";

const { VITE_API_URL, VITE_USER_INFO_API_URL } = import.meta.env;

const getToken = async (): Promise<string | undefined> => {
  try {
    const { encoded } = await oidc({
      userInfoApiUrl: VITE_USER_INFO_API_URL,
    });

    return `Bearer ${encoded}`;
  } catch (e) {
    console.error(e);
  }
};

const instance = axios.create({
  baseURL: VITE_API_URL,
});

instance.defaults.headers.common["content-type"] = "application/jwt";

export { getToken, instance };

import oidc from "@uportal/open-id-connect";
import axios from "axios";

axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

const { VITE_API_URL, VITE_USER_INFO_API_URL } = import.meta.env;

console.log(import.meta.env);

const getToken = async (): Promise<string | undefined> => {
  try {
    const { encoded } = await oidc({
      userInfoApiUrl: VITE_USER_INFO_API_URL,
    });

    return encoded;
  } catch (e) {
    console.error(e);
  }
};

const getFonctions = async () =>
  await axios.get(`${VITE_API_URL}/fonction/`, {
    headers: {
      // Authorization: `Bearer ${await getToken()}`,
      "content-type": "application/jwt",
    },
  });

export { getFonctions };

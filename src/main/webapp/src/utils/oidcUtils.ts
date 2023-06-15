import oidc from "@uportal/open-id-connect";

const getToken = async (): Promise<string | undefined> => {
  try {
    const { encoded } = await oidc({
      userInfoApiUrl: import.meta.env.VITE_USER_INFO_API_URL,
    });

    return encoded;
  } catch (e) {
    console.error(e);
  }
};

export { getToken };

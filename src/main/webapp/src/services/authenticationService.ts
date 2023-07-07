import { instance as axios } from "@/utils/axiosUtils";

const signIn = async () => await axios.get("/account/signin");

export { signIn };

import { instance as axios } from "@/utils/axiosUtils";

const getConfiguration = async () => await axios.get("/config");

export { getConfiguration };

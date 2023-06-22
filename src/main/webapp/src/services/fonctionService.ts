import { instance as axios } from "@/utils/axiosUtils";

const getFonctions = async () => await axios.get("/fonction");

export { getFonctions };

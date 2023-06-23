import { instance as axios } from "@/utils/axiosUtils";

const getPersonne = async (id: number) => await axios.get(`/personne/${id}`);

export { getPersonne };

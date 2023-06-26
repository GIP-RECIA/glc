import { instance as axios } from "@/utils/axiosUtils";

const getPersonne = async (id: number) => await axios.get(`/personne/${id}`);

const searchPersonne = async (name: string) =>
  await axios.get(`/personne?name=${name}`);

export { getPersonne, searchPersonne };

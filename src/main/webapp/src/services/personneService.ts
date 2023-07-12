import { instance as axios } from "@/utils/axiosUtils";

const getPersonne = async (id: number) => await axios.get(`/personne/${id}`);

const searchPersonne = async (name: string) =>
  await axios.get(`/personne?name=${name}`);

const setPersonneAdditionalFonctions = async (
  id: number,
  additionalFonctions: Array<string>
) => await axios.post(`/personne/${id}/fonction`, { additionalFonctions });

const setPersonneAdditionalTeachings = async (
  id: number,
  additionalTeaching: Array<string>
) => {};

export {
  getPersonne,
  searchPersonne,
  setPersonneAdditionalFonctions,
  setPersonneAdditionalTeachings,
};

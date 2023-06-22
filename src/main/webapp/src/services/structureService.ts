import { instance as axios } from "@/utils/axiosUtils";

const getEtablissements = async () =>
  await axios.get("/structure/etablissement");

const getEtablissement = async (id: number) =>
  await axios.get(`/structure/etablissement/${id}`);

export { getEtablissements, getEtablissement };

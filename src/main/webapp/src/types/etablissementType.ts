import type { Adresse } from "@/types/adresseType";

export type Etablissement = {
  id: number;
  uai: string;
  etat: string;
  etatAlim: string;
  anneeScolaire: string;
  adresse: Adresse;
  categorie: string;
  mail: string;
  nom: string;
  nomCourt: string;
  siren: string;
  siteWeb: string;
  modeleLogin: string;
  logo: string;
  filieres: Array<number>;
};

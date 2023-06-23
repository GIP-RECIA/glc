import type { SimplePersonne } from "./personneType";
import type { Adresse } from "@/types/adresseType";
import type { Filiere } from "@/types/filiereType";

export type Etablissement = {
  id: number;
  uai: string;
  etat: string;
  etatAlim: string;
  source: string;
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
  personnes: Array<SimplePersonne>;
  filieres: Array<Filiere>;
};

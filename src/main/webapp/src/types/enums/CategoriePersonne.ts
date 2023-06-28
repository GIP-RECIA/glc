import type { enumValues } from "@/types/enumValuesType";

export enum CategoriePersonne {
  Eleve = "Eleve",
  Enseignant = "Enseignant",
  Non_enseignant_collectivite_locale = "Non_enseignant_collectivite_locale",
  Non_enseignant_etablissement = "Non_enseignant_etablissement",
  Non_enseignant_service_academique = "Non_enseignant_service_academique",
  Personne_relation_eleve = "Personne_relation_eleve",
  Personnel_exterieur = "Personnel_exterieur",
  Responsable_Entreprise = "Responsable_Entreprise",
  Tuteur_stage = "Tuteur_stage",
}

const getCategoriePersonne = (categorie: string): enumValues => {
  switch (categorie) {
    case CategoriePersonne.Eleve.toString():
      return { i18n: "" };
    case CategoriePersonne.Enseignant.toString():
      return { i18n: "" };
    case CategoriePersonne.Non_enseignant_collectivite_locale.toString():
      return { i18n: "" };
    case CategoriePersonne.Non_enseignant_etablissement.toString():
      return { i18n: "" };
    case CategoriePersonne.Non_enseignant_service_academique.toString():
      return { i18n: "" };
    case CategoriePersonne.Personne_relation_eleve.toString():
      return { i18n: "" };
    case CategoriePersonne.Personnel_exterieur.toString():
      return { i18n: "" };
    case CategoriePersonne.Responsable_Entreprise.toString():
      return { i18n: "" };
    case CategoriePersonne.Tuteur_stage.toString():
      return { i18n: "" };
    default:
      throw new Error(
        `Non-existent categorie personne in switch: ${categorie}`
      );
  }
};

export { getCategoriePersonne };

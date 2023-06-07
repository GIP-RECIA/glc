package fr.recia.glc.db.entities.common.enums;

public enum CategoriePersonne {
  /**
   * Un élève.
   */
  Eleve,
  /**
   * Un enseignant.
   */
  Enseignant,
  /**
   * Une personne non enseignante d'une collectivité locale.
   */
  Non_enseignant_collectivite_locale,
  /**
   * Une personne non enseignante faisant parti du personnel d'un établissement.
   */
  Non_enseignant_etablissement,
  /**
   * Une personne non enseignante d'un service académique.
   */
  Non_enseignant_service_academique,
  /**
   * Une personne étant uniquement une personne en custommappings avec un élève.
   */
  Personne_relation_eleve,
  /**
   * Une personne du personnel extérieur.
   */
  Personnel_exterieur,
  /**
   * Un responsable d'entreprise.
   */
  Responsable_Entreprise,
  /**
   * Un tuteur de stage.
   */
  Tuteur_stage
}

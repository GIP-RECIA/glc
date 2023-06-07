package fr.recia.glc.db.entities.common.enums;

public enum CategorieFonction {
  /**
   * Prof principal, délégué de classe, délégué vie scolaire, délégué remplaçant1, délégué remplaçant2.
   */
  Classe,
  /**
   * Domaines d'exercices.
   */
  Domaine,
  /**
   * Domaines d'exercices au sein d'un groupement d'établissements.
   */
  Domaine_Groupement,
  /**
   * Fonction dans une structure, avec le type de fonction filière et discipline de poste associée.
   */
  Fonction,
  /**
   * Enseignant qui enseigne pour un MEF dans un établissement.
   */
  MEF
}

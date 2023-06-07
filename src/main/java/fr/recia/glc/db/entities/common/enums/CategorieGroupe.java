package fr.recia.glc.db.entities.common.enums;

public enum CategorieGroupe {
  /**
   * Groupe de type classe au sein d'un établissement.
   */
  Classe,
  /**
   * Groupe de type groupe au sein d'un établissement.
   */
  Groupe,
  /**
   * Groupe de type groupement d'établissment.
   */
  Groupement_etablissement,
  /**
   * Groupe de type profil, rassemblant des personnes d'un certain profile.
   */
  Profil,
  /**
   * Groupe de type relation avec un élève,
   * désignant ainsi toutes les personnes en relation avec un élève.
   */
  Relation_eleve,
  /**
   * Groupe de type rôle applicatif, permettant de définir des rôles
   * pour des groupes d'utilisateurs au sein d'une application.
   */
  Role_applicatif,
  /**
   * Groupe de fusion de comptes, interne à sarapis.
   */
  Fusion
}

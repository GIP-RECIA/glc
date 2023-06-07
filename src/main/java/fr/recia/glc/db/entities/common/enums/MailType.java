package fr.recia.glc.db.entities.common.enums;

public enum MailType {
  /**
   * Mail à usage public.
   */
  Public,
  /**
   * Mail à usage interne.
   */
  Interne,
  /**
   * Mail sans usage.
   */
  Unused,
  /**
   * Mail en provenance du SI non modifiable.
   */
  SI
}

package fr.recia.glc.db.entities.common.enums;

public enum PropertyName {
  /**
   * Défini le droit à la modification des données de la BD.
   * Export compris, car les acquittements effectuent des modifications).
   */
  CAN_WORK_ON_DB,
  /**
   * Définie l'état d'execution du cron de sarapis.
   */
  CRON_STATS,
  /**
   * Définie une volonté d'arrêt.
   */
  FORCE_STOP
}

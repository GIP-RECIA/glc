package fr.recia.glc.db.entities.common;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity extends AbstractSimpleEntity implements Serializable {

  /**
   * Gestion des versions de l'objet, incrémentation de +1 à chaque modification de type update ou merge.
   */
  @Version
  private long version;
  /**
   * Création automatique de la date de création de l'objet lors de la construction.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreation;
  /**
   * Donne l'information de la date de modification de l'objet.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateModification;
  /**
   * Donne l'information de la date d'acquittement de l'objet lors de l'export.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateAcquittement;

  /**
   * Constructeur de l'objet AbstractEntity.java.
   */
  public AbstractEntity() {
    super();
  }

  /**
   * Setter automatique du membre dateCreation lors de la creation de l'objet.
   */
  @PrePersist
  public void prePersistOps() {
    Date d = new Date();
    d.setTime(Calendar.getInstance().getTimeInMillis());
    if (this.dateCreation == null) {
      this.dateCreation = d;
    }
    if (this.dateModification == null) {
      this.dateModification = d;
    }
  }

  /**
   * Setter automatique du membre dateModification lors de la modification de l'objet.
   * WARNING : Surchargé dans entity APersonne pour éviter la màj auto lors de la modification en delete.
   */
  @PreUpdate
  public void preUpdateOps() {
    this.dateModification.setTime(Calendar.getInstance().getTimeInMillis());
  }

  @Override
  public String toString() {
    return "AbstractEntity [" +
      super.toString() + ", " +
      this.version + ", " +
      this.dateCreation + ", " +
      this.dateModification +
      "]";
  }

}

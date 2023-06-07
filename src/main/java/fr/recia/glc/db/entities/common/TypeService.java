package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"libelleService", "source"})
})
@Getter
@Setter
public class TypeService extends AbstractSimpleEntity {

  /**
   * Nom du service.
   */
  @Column(nullable = false)
  private String libelleService;
  /**
   * Source de l'alimentation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  /**
   * Constructeur de l'objet TypeService.java.
   */
  public TypeService() {
    super();
  }

  /**
   * Constructeur de l'objet TypeService.java.
   *
   * @param libelleService Nom du service.
   * @param source         Source d'alimentation.
   */
  public TypeService(final String libelleService, final String source) {
    super();
    this.libelleService = libelleService;
    this.source = source;
  }

  @Override
  public String toString() {
    return "TypeService [" +
      super.toString() + ", " +
      this.libelleService + ", " +
      this.source +
      "]";
  }

}

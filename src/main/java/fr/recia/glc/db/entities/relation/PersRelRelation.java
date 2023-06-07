package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.entities.common.enums.CategorieRelation;
import fr.recia.glc.db.entities.common.enums.LienParente;
import fr.recia.glc.db.entities.common.enums.ResponsableLegal;
import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.personne.PersonneRelationEleve;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "PERSREL")
@Getter
@Setter
public class PersRelRelation extends AMappingRelation {

  /**
   * Type énuméré du type de lien de parenté.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private LienParente lienParente;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean responsableFinancier;
  /**
   * Type énuméré du type de la relation de stage.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I15)
  private ResponsableLegal responsableLegal;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean contact;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean paiement;
  /* La relation est une adresse de domiciliation. */
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean adresse;

  /**
   * Empty Constructor, must not be used.
   */
  public PersRelRelation() {
    super();
  }

  /**
   * @param source
   * @param persRelEleve
   * @param eleve
   * @param lienParente
   * @param responsableFinancier
   * @param responsableLegal
   * @param contact
   * @param paiement
   * @param adresse
   */
  public PersRelRelation(final String source, final PersonneRelationEleve persRelEleve, final Eleve eleve,
                         final LienParente lienParente, final boolean responsableFinancier,
                         final ResponsableLegal responsableLegal, final boolean contact,
                         final boolean paiement, final boolean adresse) {
    super(source, eleve, persRelEleve, CategorieRelation.PersRel);
    this.lienParente = lienParente;
    this.responsableFinancier = responsableFinancier;
    this.responsableLegal = responsableLegal;
    this.contact = contact;
    this.paiement = paiement;
    this.adresse = adresse;
  }

  @Override
  public String toString() {
    return "PersRelRelation [lienParente=" +
      this.lienParente + ", responsableFinancier=" +
      this.responsableFinancier + ", responsableLegal=" +
      this.responsableLegal + ", contact=" +
      this.contact + ", paiement=" +
      this.paiement + ", adresse=" +
      this.adresse + ", toString()=" +
      super.toString() +
      "]";
  }

}

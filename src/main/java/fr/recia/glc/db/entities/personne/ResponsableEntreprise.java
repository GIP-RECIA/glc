package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.enums.CategoriePersonne;
import fr.recia.glc.db.entities.structure.AStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class ResponsableEntreprise extends APersonne implements Serializable {

  /**
   * Constructeur de l'objet ResponsableEntreprise.java.
   */
  public ResponsableEntreprise() {
    super();
    this.setCategorie(CategoriePersonne.Responsable_Entreprise);
  }

  /**
   * Constructeur de l'objet ResponsableEntreprise.java.
   *
   * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
   * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources,
   *                      mais unique uniquement pour le périmètre de la source.
   * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
   * @param givenName     Prénom usuel.
   * @param sn            Nom d'usage.
   * @param societe       Société dont la personne est le responsable.
   */
  public ResponsableEntreprise(final Date anneeScolaire, final CleJointure cleJointure,
                               final String cn, final String givenName, final String sn, final AStructure societe) {
    super(anneeScolaire, CategoriePersonne.Responsable_Entreprise, cleJointure, cn, givenName, sn);
    this.setStructRattachement(societe);
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.personne.APersonne#toString()
   */
  @Override
  public String toString() {
    return "ResponsableEntreprise [" + super.toString() + "]";
  }

}

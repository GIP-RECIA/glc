package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.enums.CategoriePersonne;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Enseignant extends APersonnel {

  /**
   * Constructeur de l'objet Enseignant.java.
   */
  public Enseignant() {
    super();
    this.setCategorie(CategoriePersonne.Enseignant);
  }

  /**
   * Constructeur de l'objet Enseignant.java.
   *
   * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
   * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources, mais unique uniquement pour le périmètre de la
   *                      source.
   * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
   * @param givenName     Prénom usuel.
   * @param sn            Nom d'usage.
   */
  public Enseignant(final Date anneeScolaire, final CleJointure cleJointure,
                    final String cn, final String givenName, final String sn) {
    super(anneeScolaire, CategoriePersonne.Enseignant, cleJointure, cn, givenName, sn);
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.personne.APersonne#toString()
   */
  @Override
  public String toString() {
    return "Enseignant [" + super.toString() + "]";
  }

}

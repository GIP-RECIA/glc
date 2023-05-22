/*
 * Copyright (C) 2023 GIP-RECIA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.Etat;
import fr.recia.glc.db.entities.relation.AMappingRelation;
import fr.recia.glc.db.entities.relation.CategorieRelation;
import fr.recia.glc.db.entities.relation.MAStageRelation;
import fr.recia.glc.db.entities.structure.AStructure;

import jakarta.persistence.Entity;
import java.util.Date;
import java.util.EnumSet;

/**
 * Bean - Entity.
 * APersonne étendu en TuteurStage.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>uid, login, password, cn, sn, displayName, givenName, CategoriePersonne,
 * cleJointure, anneeScolaire, etat, societe, fonction.</DD></DL>
 * <b>Attention la société est donnée dans le champs structRattachement
 * et fonction représente la liste des élèves suivis en stage ou en apprentissage. </b>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
public class TuteurStage extends APersonne {

	/**Identifiant de sérialisation. */
	private static final long serialVersionUID = 7505676238668581501L;

	//Attributs

	//Constructeurs
	/**
	 * Constructeur de l'objet TuteurStage.java.
	 */
	public TuteurStage() {
		super();
		this.setCategorie(CategoriePersonne.Tuteur_stage);
	}

	/**
	 * Constructeur de l'objet TuteurStage.java.
	 * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
	 * @param cleJointure Clé de jointure, identifiant unique fourni par les différentes sources,
	 * mais unique uniquement pour le périmètre de la source.
	 * @param cn Nom canonique de la personne : NOM + Prénom usuels.
	 * @param givenName Prénom usuel.
	 * @param sn Nom d'usage.
	 * @param societe Société dont la personne est le responsable.
	 */
	public TuteurStage(final Date anneeScolaire, final CleJointure cleJointure,
                     final String cn, final String givenName, final String sn, final AStructure societe) {
		super(anneeScolaire, CategoriePersonne.Tuteur_stage, cleJointure, cn, givenName, sn);
		this.setStructRattachement(societe);
	}

	//Accesseurs
	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.personne.APersonne#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TuteurStage [");
		sb.append(super.toString());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Effectue la vérification si la personne est en relation qu'avec des élèves incertains.
	 * @return <code>boolean</code> Faux si au moins une relation est avec un élève valide.
	 */
	public boolean hasAllRelationWithIncertain() {
		boolean b = true;
		if (getRelationsTo() != null && !getRelationsTo().isEmpty()) {
			for (AMappingRelation relation : getRelationsTo()) {
				if (CategorieRelation.MAStage.equals(relation.getCategorie())) {
					MAStageRelation relv = (MAStageRelation) relation;
					EnumSet<Etat> set = EnumSet.of(Etat.Incertain, Etat.Delete);
					if (!set.contains(relv.getEleve().getEtat())) {
						b = false;
						break;
					}
				}
			}
		}
		return b;
	}

}
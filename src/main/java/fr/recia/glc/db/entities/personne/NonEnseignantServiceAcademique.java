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

import jakarta.persistence.Entity;
import java.util.Date;

/**
 * Bean - Entity. APersonne étendu en NonEnseignantServiceAcademique.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>uid, login, password, cn, sn, displayName, givenName, CategoriePersonne, cleJointure, anneeScolaire, etat.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 11 juin 08
 */
@Entity
public class NonEnseignantServiceAcademique extends APersonnel {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 3057421206801658038L;

	// Constructeurs
	/**
	 * Constructeur de l'objet NonEnseignantServiceAcademique.java.
	 */
	public NonEnseignantServiceAcademique() {
		super();
		this.setCategorie(CategoriePersonne.Non_enseignant_service_academique);
	}

	/**
	 * Constructeur de l'objet NonEnseignantServiceAcademique.java.
	 *
	 * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
	 * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources, mais unique uniquement pour le périmètre de la
	 *                      source.
	 * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
	 * @param givenName     Prénom usuel.
	 * @param sn            Nom d'usage.
	 */
	public NonEnseignantServiceAcademique(final Date anneeScolaire, final CleJointure cleJointure,
                                        final String cn, final String givenName, final String sn) {
		super(anneeScolaire, CategoriePersonne.Non_enseignant_service_academique, cleJointure, cn, givenName, sn);
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 *
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.personne.APersonne#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("NonEnseignantServiceAcademique [");
		sb.append(super.toString());
		sb.append("]");
		return sb.toString();
	}

}

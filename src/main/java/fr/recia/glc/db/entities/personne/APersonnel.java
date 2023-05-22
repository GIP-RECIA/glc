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
import fr.recia.glc.db.entities.common.TypeService;
import fr.recia.glc.db.entities.education.CategorieDiscipline;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class APersonnel extends APersonne {

	/**
	 * Relation bidirectionnelle. Liste des catégories de disciplines de poste d'une personne (Enseignant, NonEnsEtab) au sein d'un établissement.
	 */
	@Fetch(FetchMode.JOIN)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "apersonnes_catdisciplines", foreignKey = @ForeignKey(name = "FK_APERSONNE_ID_CAT_DISCIPLINE_ID"), joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "id", table = "apersonne"), inverseJoinColumns = @JoinColumn(name = "CATDISCIPLINE_ID", referencedColumnName = "id"))
	private Set<CategorieDiscipline> categorieDisciplines = new HashSet<>();

	/**
	 * Relation bidirectionnelle. Liste des services de la personne (NonEns Etab, NonEnsCollLoc, NonEnsServAc, Enseignant)
	 */
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "apersonnes_services", foreignKey = @ForeignKey(name = "FK_APERSONNE_ID_SERVICE_ID"), joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "id", table = "apersonne"), inverseJoinColumns = @JoinColumn(name = "SERVICE_ID", referencedColumnName = "id"))
	private Set<TypeService> services = new HashSet<>();

	public APersonnel() {
		super();
	}

	public APersonnel(final Date anneeScolaire, final CategoriePersonne categorie, final CleJointure cleJointure,
                    final String cn, final String givenName, final String sn) {
		super(anneeScolaire, categorie, cleJointure, cn, givenName, sn);
	}

	/**
	 * Getter du membre categorieDisciplines.
	 *
	 * @return <code>Set< CategorieDiscipline ></code> le membre categorieDisciplines.
	 */
	public Set<CategorieDiscipline> getCategorieDisciplines() {
		return this.categorieDisciplines;
	}

	/**
	 * Setter du membre categorieDisciplines.
	 *
	 * @param disciplines la nouvelle valeur du membre categorieDisciplines.
	 */
	public void setCategorieDisciplines(final Set<CategorieDiscipline> disciplines) {
		this.categorieDisciplines = disciplines;
	}

	/**
	 * Ajoute une catégrorie de discipline à la liste.
	 *
	 * @param categorieDiscipline une nouvelle catégorie à ajouter.
	 */
	public void addCategorieDiscipline(final CategorieDiscipline categorieDiscipline) {
		this.categorieDisciplines.add(categorieDiscipline);
	}

	/**
	 * @return the services
	 */
	public Set<TypeService> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(final Set<TypeService> services) {
		this.services = services;
	}

	/**
	 * Ajoute un service à la liste.
	 *
	 * @param service
	 */
	public void addService(final TypeService service) {
		this.getServices().add(service);
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 *
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.personne.APersonne#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("APersonnel [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.categorieDisciplines);
		sb.append(", ");
		sb.append(this.services);
		sb.append("]");
		return sb.toString();
	}

}

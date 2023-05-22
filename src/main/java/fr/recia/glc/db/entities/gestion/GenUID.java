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
package fr.recia.glc.db.entities.gestion;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity
 * Gestion de la ganération des uid avec sauvegarde de la dernière valeur insérée,
 * autoincremént pour les clé suivantes.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>l, c, xx, iiii.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 12 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"l", "c", "xx"})
})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GenUID extends AbstractEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -4581509331475159425L;

	//Attributs
	/** Code région. */
	@Column(length = IntConst.I1)
	private String l;
	/** Code département. */
	@Column(length = IntConst.I1)
	private String c;
	/** Code de l'année d'insertion. */
	@Column(length = IntConst.I2)
	private String xx;
	/** Code alphanumérique autoincrémenté, sauvegardé sous forme d'entier. */
	private int iiii;

	//Constructeur
	/**
	 * Constructeur de l'objet GenUID.java.
	 */
	public GenUID() {
		super();
	}

	/**
	 * Constructeur de l'objet GenUID.java.
	 * @param l Code région.
	 * @param c Code département.
	 */
	public GenUID(final String l, final String c) {
		super();
		this.l = l;
		this.c = c;
	}

	//Accesseurs
	/**
	 * Getter du membre l.
	 * @return <code>String</code> le membre l.
	 */
	public String getL() {
		return this.l;
	}

	/**
	 * Setter du membre l.
	 * @param l la nouvelle valeur du membre l.
	 */
	public void setL(final String l) {
		this.l = l;
	}

	/**
	 * Getter du membre c.
	 * @return <code>String</code> le membre c.
	 */
	public String getC() {
		return this.c;
	}

	/**
	 * Setter du membre c.
	 * @param c la nouvelle valeur du membre c.
	 */
	public void setC(final String c) {
		this.c = c;
	}

	/**
	 * Getter du membre xx.
	 * @return <code>String</code> le membre xx.
	 */
	public String getXx() {
		return this.xx;
	}

	/**
	 * Setter du membre xx.
	 * @param xx la nouvelle valeur du membre xx.
	 */
	public void setXx(final String xx) {
		this.xx = xx;
	}

	/**
	 * Getter du membre iiii.
	 * @return <code>String</code> le membre iiii.
	 */
	public int getIiii() {
		return this.iiii;
	}

	/**
	 * Setter du membre iiii.
	 * @param iiii la nouvelle valeur du membre iiii.
	 */
	public void setIiii(final int iiii) {
		this.iiii = iiii;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("GenUID [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.l);
		sb.append(", ");
		sb.append(this.xx);
		sb.append(", ");
		sb.append(this.c);
		sb.append(", ");
		sb.append(this.iiii);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Donne la valeur de hachage de l'instance.
	 * @return <code>int</code> La valeur du hash.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.c == null) {
			result = prime * result;
		} else {
			result = prime * result + this.c.hashCode();
		}
		result = prime * result + this.iiii;
		if (this.l == null) {
			result = prime * result;
		} else {
			result = prime * result + this.l.hashCode();
		}
		if (this.xx == null) {
			result = prime * result;
		} else {
			result = prime * result + this.xx.hashCode();
		}
		return result;
	}

	/**
	 * Teste si un objet est égal à cette instance.
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof GenUID)) { return false; }
		final GenUID other = (GenUID) obj;
		if (this.c == null) {
			if (other.c != null) { return false; }
		} else if (!this.c.equals(other.c)) { return false; }
		if (this.iiii != other.iiii) { return false; }
		if (this.l == null) {
			if (other.l != null) { return false; }
		} else if (!this.l.equals(other.l)) { return false; }
		if (this.xx == null) {
			if (other.xx != null) { return false; }
		} else if (!this.xx.equals(other.xx)) { return false; }
		return true;
	}

}

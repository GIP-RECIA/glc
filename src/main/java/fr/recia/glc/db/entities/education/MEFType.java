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
package fr.recia.glc.db.entities.education;

/**
 * Type énumré des types de MEF.
 * MEF de type académique ou national.
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
public enum MEFType {
	/** MEF de type Académique. */
	Academique,
	/** MEF de type National. */
	National,
	/** MEF de type Cfa. */
	Cfa
}
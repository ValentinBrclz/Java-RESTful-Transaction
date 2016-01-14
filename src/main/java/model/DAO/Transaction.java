/*
 * Java RESTful Transaction
 *
 * Wikipedia FR Bot that signs when users forget to do so
 * Copyright (C) 2016 Valentin Berclaz
 * <http://www.valentinberclaz.com/>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

/**
 * "Transaction" object class
 *
 * @author Valentin Berclaz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {
	// UID
	private static final long serialVersionUID = 354034056054L;

	// Variables
	private long id;
	private Long parent_id;
	private double amount;
	private String type;

	/**
	 * Constructor of the transaction
	 *
	 * @param id        The id of the transaction
	 * @param parent_id The id of the parent of the transaction
	 * @param amount    The amount of the transaction
	 * @param type      The type of the transaction
	 * @throws IllegalArgumentException
	 */
	public Transaction(long id, Long parent_id, double amount, String type) throws IllegalArgumentException {
		setId(id);
		setParent_id(parent_id);
		setAmount(amount);
		setType(type);
	}

	/**
	 * Default constructor
	 */
	public Transaction() {
	}

	/**
	 * Get the id of the transaction
	 *
	 * @return The id of the transaction
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the id of the transaction
	 *
	 * @param id The id of the transaction
	 * @throws IllegalArgumentException
	 */
	public void setId(long id) throws IllegalArgumentException {
		if (id > 0)
			this.id = id;
		else
			throw new IllegalArgumentException("The id has to be greater than 0.");
	}

	/**
	 * Get the parent id of the transaction
	 *
	 * @return The id of the parent
	 */
	public Long getParent_id() {
		return parent_id;
	}

	/**
	 * Set the parent id of the transaction
	 *
	 * @param parent_id The id of the parent
	 * @throws IllegalArgumentException
	 */
	public void setParent_id(Long parent_id) throws IllegalArgumentException {
		if (parent_id == null || parent_id > 0)
			this.parent_id = parent_id;
		else
			throw new IllegalArgumentException("The parent id has to be greater than 0 or null.");
	}

	/**
	 * Get the amount of the transaction
	 *
	 * @return The amount of the transaction
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Set the amount of the transaction
	 *
	 * @param amount The amount of the transaction (negative or positive)
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Get the type of the transaction
	 *
	 * @return The type of the transaction
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the type of the transaction
	 *
	 * @param type The type of the transaction
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Override
	public boolean equals(Object obj) {
		// Test if the references are equals
		if (obj == this) {
			return true;
		}

		// Verification of the parameter
		if (obj instanceof Transaction) {

			// Vérification des valeurs des attributs
			Transaction o = (Transaction) obj;

			return getId() == o.getId() &&
					getParent_id().equals(o.getParent_id()) &&
					getAmount() == o.getAmount() &&
					getType().equals(o.getType());
		}

		return false;
	}

	/**
	 * Returns a hash code value for the object. This method is
	 * supported for the benefit of hash tables such as those provided by
	 * {@link HashMap}.
	 *
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see System#identityHashCode
	 */
	@Override
	public int hashCode() {
		int result = 0;

		result = 13 * result + Long.valueOf(getId()).hashCode();
		result = 13 * result + (getParent_id() != null ? getParent_id().hashCode() : 0);
		result = 13 * result + (getType() != null ? getType().hashCode() : 0);
		result = 13 * result + Double.valueOf(getAmount()).hashCode();

		return result;
	}
}

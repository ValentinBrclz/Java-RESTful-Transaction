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

/**
 * "Sum" object class
 *
 * @author Valentin Berclaz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sum implements Serializable {
	// UID
	private static final long serialVersionUID = 670421290672L;

	// Variables
	private double sum;

	/**
	 * Default constructor
	 */
	public Sum() {
	}

	/**
	 * Get the sum
	 *
	 * @return The sum
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * Set the sum
	 *
	 * @param sum The sum of the transaction
	 */
	public void setSum(double sum) {
		this.sum = sum;
	}
}

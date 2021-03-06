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

package model.DAL;

import model.DAO.Transaction;

import java.util.ArrayList;

/**
 * Interface that defines functions that any database needs to implement
 *
 * @author Valentin Berclaz
 */
public interface DatabaseInterface {
	/**
	 * Get a specific transaction via its id
	 *
	 * @param id The id of the transaction
	 * @return The transaction with the specified id
	 */
	Transaction getTransaction(Long id);

	/**
	 * Get transactions ids that correspond to a specified type
	 *
	 * @param type The type of the transactions
	 * @return The transactions ids with the specified type
	 */
	ArrayList<Long> getTransactionsIdsByType(String type);

	/**
	 * Get the children of the transaction
	 *
	 * @param id The id of the parent transaction
	 * @return The requested children
	 */
	ArrayList<Long> getTransactionChildren(Long id);

	/**
	 * Add a transaction to the database
	 *
	 * @param transaction The transaction to add
	 * @throws IllegalArgumentException if the id is already present in the database
	 */
	void addTransaction(Transaction transaction) throws IllegalArgumentException;
}

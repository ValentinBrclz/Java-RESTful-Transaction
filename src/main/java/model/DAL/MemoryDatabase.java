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
import java.util.concurrent.ConcurrentHashMap;

/**
 * Local database in memory
 *
 * @author Valentin Berclaz
 */
public class MemoryDatabase implements DatabaseInterface {
	private ConcurrentHashMap<Long, Transaction> db;
	private ConcurrentHashMap<String, ArrayList<Long>> typeIndex;
	private ConcurrentHashMap<Long, ArrayList<Long>> parentIndex;

	/**
	 * Private constructor (singleton)
	 */
	private MemoryDatabase() {
		db = new ConcurrentHashMap<Long, Transaction>();
		typeIndex = new ConcurrentHashMap<String, ArrayList<Long>>();
		parentIndex = new ConcurrentHashMap<Long, ArrayList<Long>>();
	}

	/**
	 * Holding class for the singleton
	 */
	private static class DatabaseHolder {
		/**
		 * Instance unique non préinitialisée
		 */
		private final static DatabaseInterface instance = new MemoryDatabase();
	}

	/**
	 * Get the current database
	 *
	 * @return The current instance of the Database
	 */
	public static DatabaseInterface getInstance() {
		return DatabaseHolder.instance;
	}

	/**
	 * Get a specific transaction via its id
	 *
	 * @param id The id of the transaction
	 * @return The transaction with the specified id
	 */
	@Override
	public Transaction getTransaction(Long id) {
		return db.get(id);
	}

	/**
	 * Get transactions ids that correspond to a specified type
	 *
	 * @param type The type of the transactions
	 * @return The transactions ids with the specified type
	 */
	@Override
	public ArrayList<Long> getTransactionsIdsByType(String type) {
		return typeIndex.get(type);
	}

	/**
	 * Get the children of the transaction
	 *
	 * @param id The id of the parent transaction
	 * @return The requested children
	 */
	@Override
	public ArrayList<Long> getTransactionChildren(Long id) {
		return parentIndex.get(id);
	}

	/**
	 * Add a transaction to the database
	 *
	 * @param transaction The transaction to add
	 * @throws IllegalArgumentException
	 */
	@Override
	public void addTransaction(Transaction transaction) throws IllegalArgumentException {
		// Check that the id does not exist
		if (db.get(transaction.getId()) != null)
			throw new IllegalArgumentException("The id already exist in the database");

		// Add the transaction in the main database
		db.put(transaction.getId(), transaction);

		// Add the transaction id in the TypeIndex
		ArrayList<Long> typeList;
		if (typeIndex.get(transaction.getType()) == null) {
			typeList = new ArrayList<Long>();
			typeList.add(transaction.getId());
			typeIndex.put(transaction.getType(), typeList);
		} else {
			typeList = typeIndex.get(transaction.getType());
			typeList.add(transaction.getId());
			typeIndex.replace(transaction.getType(), typeList);
		}

		// Add the transaction id in the ParentIndex
		if (transaction.getParent_id() != null) {
			ArrayList<Long> parentList;
			if (parentIndex.get(transaction.getParent_id()) == null) {
				parentList = new ArrayList<Long>();
				parentList.add(transaction.getId());
				parentIndex.put(transaction.getParent_id(), parentList);
			} else {
				parentList = parentIndex.get(transaction.getParent_id());
				parentList.add(transaction.getId());
				parentIndex.replace(transaction.getParent_id(), parentList);
			}
		}
	}
}

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

/**
 * Local database in memory
 *
 * @author Valentin Berclaz
 */
public class Database implements DatabaseInterface {

    /**
     * Get a specific transaction via its id
     *
     * @param id The id of the transaction
     * @return The transaction with the specified id
     */
    @Override
    public Transaction getTransaction(long id) {
        return null;
    }

    /**
     * Get transactions ids that correspond to a specified type
     *
     * @param type The type of the transactions
     * @return The transactions ids with the specified type
     */
    @Override
    public long[] getTransactionsIdsByType(String type) {
        return new long[0];
    }

    /**
     * Get transactions that have a specific parent
     *
     * @param parent_id The id of the parent of the transactions
     * @return The transactions with the specified parent
     */
    @Override
    public Transaction[] getTransactionsByParent(long parent_id) {
        return new Transaction[0];
    }

    /**
     * Add a transaction to the database
     *
     * @param transaction The transaction to add
     */
    @Override
    public void addTranscation(Transaction transaction) {

    }

    /**
     * Update the transaction with the specified id
     *
     * @param id             The id of the transaction to update
     * @param newTransaction The new and modified transaction
     */
    @Override
    public void updateTransaction(long id, Transaction newTransaction) {

    }
}

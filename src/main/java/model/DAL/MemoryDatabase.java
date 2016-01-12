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

    /**
     * Constructor
     */
    public MemoryDatabase() {
        db = new ConcurrentHashMap<Long, Transaction>();
        typeIndex = new ConcurrentHashMap<String, ArrayList<Long>>();
    }

    /**
     * Get a specific transaction via its id
     *
     * @param id The id of the transaction
     * @return The transaction with the specified id
     */
    @Override
    public Transaction getTransaction(long id) {
        // TODO test nullpointer
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
        // TODO test nullpointer
        return typeIndex.get(type);
    }

    /**
     * Get the sum of the transaction and all its children
     *
     * @param id The id of the parent transaction
     * @return The requested sum
     */
    @Override
    public Double getSum(long id) {
        // TODO implement function nullpointer
        return null;
    }

    /**
     * Add a transaction to the database
     *
     * @param transaction The transaction to add
     */
    @Override
    public void addTransaction(Transaction transaction) {
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
    }
}

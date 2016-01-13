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
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test of the class "MemoryDatabase"
 *
 * @author Valentin Berclaz
 */
public class MemoryDatabaseTest {
	private static DatabaseInterface db = MemoryDatabase.getInstance();
	private static Transaction referenceTransaction = new Transaction(1L, null, 20.0, "grocery");
	private static Transaction referenceChildTransaction = new Transaction(2L, 1L, 10.0, "grocery");

	@BeforeClass
	public static void onlyOnce() throws Exception {
		// Add transaction
		db.addTransaction(referenceTransaction);
		db.addTransaction(referenceChildTransaction);
	}

	@Test
	public void testGetTransaction() throws Exception {
		// Get the transaction
		Transaction t = db.getTransaction(1L);

		Assert.assertEquals(t, referenceTransaction);
	}

	@Test
	public void testGetTransactionsIdsByType() throws Exception {
		ArrayList<Long> ids = db.getTransactionsIdsByType("grocery");

		Assert.assertEquals(ids.get(0).toString(), referenceTransaction.getId() + "");
	}

	@Test
	public void testGetTransactionChildren() throws Exception {
		ArrayList<Long> children = db.getTransactionChildren(referenceTransaction.getId());

		Assert.assertEquals(children.get(0).toString(), referenceChildTransaction.getId() + "");
	}
}
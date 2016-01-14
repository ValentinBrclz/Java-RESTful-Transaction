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

package controller;

import model.DAL.DatabaseInterface;
import model.DAL.MemoryDatabase;
import model.DAO.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Transaction controller
 *
 * @author Valentin Berclaz
 */
@Path("/transactionservice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionService {
	// Launching the database
	private DatabaseInterface db = MemoryDatabase.getInstance();

	@GET
	@Path("transaction/{id: [1-9][0-9]*}")
	public Transaction getTransaction(@PathParam("id") long id) {
		Transaction transaction = db.getTransaction(id);

		if (transaction == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			return transaction;
		}
	}

	@PUT
	@Path("transaction/{id: [1-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTransaction(@PathParam("id") long id, Transaction transaction) {
		try {
			// Set the id
			transaction.setId(id);

			// Add the transaction to the database
			db.addTransaction(transaction);

			return Response.ok("{\"status\":\"ok\"}").build();

		} catch (IllegalArgumentException e) {

			throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
	}

	@GET
	@Path("types/{type}")
	public Long[] getTransactionsIdByType(@PathParam("type") String type) {
		List<Long> transactionsIds = db.getTransactionsIdsByType(type);

		if (transactionsIds == null || transactionsIds.size() == 0) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			return transactionsIds.toArray(new Long[transactionsIds.size()]);
		}
	}

	@GET
	@Path("sum/{id: [1-9][0-9]*}")
	public String getSum(@PathParam("id") long id) {
		Transaction baseTransaction = getTransaction(id);
		if (baseTransaction == null) { // Test if it exists
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			// Get the sum recursively
			Double sum = getSumRecursively(baseTransaction.getAmount(), baseTransaction.getId());

			return "{ \"sum\": " + sum + " }";
		}
	}

	/**
	 * Get the sum of children recurisvely
	 *
	 * @param sum the sum of the parent(s)
	 * @param id  the id of the actual parent
	 * @return the sum of all children
	 */
	private double getSumRecursively(Double sum, Long id) {
		List<Long> children = db.getTransactionChildren(id);
		if (children != null) {
			for (Long child : children) {
				sum += getTransaction(child).getAmount();
				sum = getSumRecursively(sum, child);
			}
		}
		return sum;
	}
}


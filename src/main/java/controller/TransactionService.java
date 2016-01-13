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
import java.util.ArrayList;

/**
 * Transaction controller
 *
 * @author Valentin Berclaz
 */
@Path("/transactionservice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionService {
	private DatabaseInterface db = new MemoryDatabase();

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
	public String addTransaction(@PathParam("id") long id, Transaction input) {
		// TODO addTransaction() to implement

		System.out.println(input);
		System.out.println(input.getAmount());
		System.out.println(input.getType());
		System.out.println(input.getParent_id());

		return "ok";
	}

	@GET
	@Path("types/{type}")
	public long[] getTransactionsIdByType(@PathParam("type") String type) {
		ArrayList<Long> transactionsIds = db.getTransactionsIdsByType(type);

		if (transactionsIds == null || transactionsIds.size() == 0) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			return new long[]{1L, 2L}; //TODO
		}
	}

	@GET
	@Path("sum/{id: [1-9][0-9]*}")
	public double getSum(@PathParam("id") long id) {
		Double sum = db.getSum(id);

		if (sum == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			return sum;
		}
	}
}


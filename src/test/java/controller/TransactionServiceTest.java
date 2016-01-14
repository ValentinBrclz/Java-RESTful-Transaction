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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import model.DAO.Sum;
import model.DAO.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * Test of the class "TransactionService"
 *
 * @author Valentin Berclaz
 */
// Make sure the add() test is done before anyhting else
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	// Configuration
	private static final String URL = "http://localhost:8080";
	private static final String PATH = "/transactionservice";
	private static final String TRANSACTION_PATH = "/transaction";
	private static final String TYPE_PATH = "/types";
	private static final String SUM_PATH = "/sum";

	// Variables
	private Client client;
	private Transaction[] referenceTransaction = {
			new Transaction(1L, null, 20.0, "grocery"),
			new Transaction(2L, 1L, 50.0, "grocery")
	};

	@Before
	public void setUp() throws Exception {
		// (Re)Initialise 'client'
		client = Client.create();
	}

	@Test
	public void testAddTransaction() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + TRANSACTION_PATH + "/" + referenceTransaction[0].getId());

		ClientResponse response = webResource
				.accept("application/json")
				.type("application/json")
				.put(ClientResponse.class, referenceTransaction[0]);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : " + response.getStatus());
		} else {
			String output = response.getEntity(String.class);

			Assert.assertEquals("{\"status\":\"ok\"}", output);
		}

		webResource = client
				.resource(URL + PATH + TRANSACTION_PATH + "/" + referenceTransaction[1].getId());

		response = webResource
				.accept("application/json")
				.type("application/json")
				.put(ClientResponse.class, referenceTransaction[1]);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : " + response.getStatus());
		} else {
			String output = response.getEntity(String.class);

			Assert.assertEquals("{\"status\":\"ok\"}", output);
		}
	}

	@Test
	public void testGetTransaction() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + TRANSACTION_PATH + "/" + referenceTransaction[0].getId());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : " + response.getStatus());
		} else {
			Transaction returnedTransaction = response.getEntity(Transaction.class);

			Assert.assertEquals(referenceTransaction[0], returnedTransaction);
		}
	}

	@Test
	public void testGetTransactionsIdByType() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + TYPE_PATH + "/" + referenceTransaction[0].getType());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : " + response.getStatus());
		} else {
			//noinspection EmptyClass
			List<Long> arrayList = response.getEntity(new GenericType<List<Long>>() {
			});

			Assert.assertEquals(referenceTransaction[0].getId() + "", arrayList.get(0).toString());
		}
	}

	@Test
	public void testGetSum() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + SUM_PATH + "/" + referenceTransaction[0].getId());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : " + response.getStatus());
		} else {
			double sum = response.getEntity(Sum.class).getSum();

			Assert.assertEquals(
					referenceTransaction[0].getAmount() + referenceTransaction[1].getAmount(),
					sum,
					0.0);
		}
	}
}
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
 *
 * TODO Implement tests
 */
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
	private Transaction testTransaction1 = new Transaction(1L, null, 20.0, "grocery");
	private Transaction testTransaction2 = new Transaction(2L, null, 50.0, "grocery");

	@Before
	public void setUp() throws Exception {
		client = Client.create();
	}

	// TODO testAddTransaction()
	/*@Test
	public void testAddTransaction() throws Exception {
		WebResource webResource = client
				.resource(URL+PATH+TRANSACTION_PATH+"/1");

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : "
					+ response.getStatus());
		}
		else {
			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

			Assert.fail("Not yet implemented");
		}
	}*/

	@Test
	public void testGetTransaction() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + TRANSACTION_PATH + "/" + testTransaction1.getId());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			Transaction returnedTransaction = response.getEntity(Transaction.class);

			Assert.assertEquals(returnedTransaction, testTransaction1);
		}
	}

	@Test
	public void testGetTransactionsIdByType() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + TYPE_PATH + "/" + testTransaction1.getType());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			//noinspection EmptyClass
			List<Long> arrayList = response.getEntity(new GenericType<List<Long>>() {
			});

			Assert.assertEquals(arrayList.get(0).toString(), testTransaction1.getId() + "");
		}
	}

	@Test
	public void testGetSum() throws Exception {
		WebResource webResource = client
				.resource(URL + PATH + SUM_PATH + "/" + testTransaction1.getId());

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			Assert.fail("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			double sum = response.getEntity(Sum.class).getSum();

			Assert.assertEquals(sum,
					testTransaction1.getAmount() + testTransaction2.getAmount(),
					0.0);
		}
	}
}
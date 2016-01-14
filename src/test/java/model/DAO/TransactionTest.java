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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the class "Transaction"
 *
 * @author Valentin Berclaz
 */
public class TransactionTest {
	private Transaction t1, t2;

	@Before
	public void setUp() throws Exception {
		t1 = new Transaction(1L, null, 20.0, "grocery");
		t2 = new Transaction(2L, 1L, 50.0, "grocery");
	}

	@Test
	public void testGetId() throws Exception {
		Assert.assertEquals(t1.getId(), 1L);
		Assert.assertEquals(t2.getId(), 2L);
	}

	@Test
	public void testSetId() throws Exception {
		t1.setId(3L);
		Assert.assertEquals(t1.getId(), 3L);
	}

	@Test
	public void testGetParent_id() throws Exception {
		Assert.assertNull(t1.getParent_id());
		Assert.assertEquals(t2.getParent_id().toString(), 1L + "");
	}

	@Test
	public void testSetParent_id() throws Exception {
		t1.setParent_id(3L);
		Assert.assertNotNull(t1.getParent_id());
	}

	@Test
	public void testGetAmount() throws Exception {
		Assert.assertEquals(t1.getAmount(), 20.0, 0.0);
		Assert.assertEquals(t1.getAmount(), 20.0, 0.0);
	}

	@Test
	public void testSetAmount() throws Exception {
		t1.setAmount(100.0);
		Assert.assertEquals(t1.getAmount(), 100.0, 0.0);
	}

	@Test
	public void testGetType() throws Exception {
		Assert.assertEquals(t1.getType(), "grocery");
		Assert.assertEquals(t2.getType(), "grocery");
	}

	@Test
	public void testSetType() throws Exception {
		t1.setType("car");
		Assert.assertEquals(t1.getType(), "car");
	}
}
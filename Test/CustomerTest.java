import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Philip Zamayeri
 * Date: 2020-10-15
 * Time: 15:40
 * Project: Gym
 * Copyright: MIT
 */
public class CustomerTest {

    @Test
    public void isCustomerMemberTest(){
        Customer c1 = new Customer("","", LocalDate.now());
        Customer c2 = new Customer("","", LocalDate.now().minusYears(3));
        c1.isCustomerMember();
        assertTrue(c1.getActiveMembership());
        assertFalse(c2.getActiveMembership());

    }
}

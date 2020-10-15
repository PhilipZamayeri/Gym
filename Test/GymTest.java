import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Philip Zamayeri
 * Date: 2020-10-15
 * Time: 09:46
 * Project: Gym
 * Copyright: MIT
 */
public class GymTest {
    Gym gym = new Gym();
    Path customerPath = Paths.get("src/customers.txt");
    //Path newPath = Paths.get("Test/customerTest");
    List<Customer> customerList;



    @Test
    public void setupCustomerListTest() {
        customerList = gym.setupCustomerList(customerPath);
        assertNotNull(customerList.get(0));
        assertEquals(14, customerList.size());
        assertEquals("Alhambra Aromes", customerList.get(0).getName());
        assertEquals("7603021234", customerList.get(0).getIdNr());
        assertFalse(customerList.isEmpty());
    }

    @Test
    public void checkIfMemberTest(){
        gym.setupCustomerList(customerPath);
        assertEquals("Personen finns ej i systemet.", gym.checkIfMember("kalle"));
        assertEquals("Diamanda Djedi är medlem.", gym.checkIfMember("Diamanda Djedi"));
        assertEquals("Alhambra Aromes är inte längre medlem.", gym.checkIfMember("Alhambra Aromes"));
    }
}





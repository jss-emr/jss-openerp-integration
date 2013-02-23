package org.jss.openerp.web.service;

import junit.framework.TestCase;
import org.jss.openerp.web.client.OpenERPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-Test.xml"})
public class OpenERPServiceIT extends TestCase {

    OpenERPService openerpService;

    OpenERPClient openERPClient;
    public @Value("${host}") String host;
    public @Value("${port}") int port;
    public @Value("${database}") String database;
    public @Value("${user}") String user;
    public @Value("${password}") String password;


    public void setUp() {
        try {
            openERPClient = new OpenERPClient(host,port,database,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        openerpService = new OpenERPService(openERPClient);

    }

    public void tearDown()  {

    }

    @Test
    public void shouldCreateFindAndDeleteCustomer() throws Exception {
        setUp();

        String name= "Raman Singh";
        String patientId ="12345";
        openerpService.createCustomer(name,patientId);
        openerpService.deleteCustomerWithPatientReference(patientId);

    }
}

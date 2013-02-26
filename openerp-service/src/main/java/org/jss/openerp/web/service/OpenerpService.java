package org.jss.openerp.web.service;


import org.apache.log4j.Logger;
import org.jss.openerp.web.client.OpenERPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class OpenERPService {
    public @Value("${host}") String host;
    public @Value("${port}") int port;
    public @Value("${database}") String database;
    public @Value("${user}") String user;
    public @Value("${password}") String password;


    OpenERPClient openERPClient;
    private static Logger logger =Logger.getLogger("OpenERPService") ;

    @Autowired
    public OpenERPService(OpenERPClient client){
        this.openERPClient = client;
    }

    public void createCustomer(String name, String patientId) throws Exception {
        if(noCustomersFound(findCustomerWithPatientReference(patientId))){
            openERPClient.create("res.partner",name, patientId);
        } else
            raiseDuplicateException(patientId);
    }


    private Object[] findCustomerWithPatientReference(String patientId) throws Exception {
        Object args[]={"ref","=",patientId};
        Vector params = new Vector();
        params.addElement(args);
        return (Object[])openERPClient.search("res.partner", params);
    }

    public void deleteCustomerWithPatientReference(String patientId) throws Exception {
        Object[] customerIds = (Object[]) findCustomerWithPatientReference(patientId);
        Vector params = new Vector();
        params.addElement((Integer)customerIds[0]);
        openERPClient.delete("res.partner", params);
    }

    Vector paramsForCreation(String name, String customerId) {
        Vector params = new Vector();
        Object args1[]={"ref",customerId};
        params.addElement(args1);

        Object args2[]={"name",name};
        params.addElement(args2);

        return params;
    }

    private boolean noCustomersFound(Object[] customers) {
        return customers.length == 0;
    }

    private void raiseDuplicateException(String patientId) throws Exception {
        logger.error("Customer with "+patientId+" already exists");
        throw new Exception("Customer with "+patientId+" already exists");
    }
}


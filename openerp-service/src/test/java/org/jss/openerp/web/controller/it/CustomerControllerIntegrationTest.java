package org.jss.openerp.web.controller.it;

import org.joda.time.DateTime;
import org.jss.openerp.web.controller.CustomerController;
import org.jss.test.utils.MVCTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-Web.xml", "classpath*:applicationContext-Test.xml"})
public class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController controller;

    @Test
    public void shouldReturnOkForACreateRequest() throws Exception {
        DefaultPatient patient = new DefaultPatient();
        MVCTestUtils.mockMvc(controller)
                .perform(post("/customer")
                        .param("patientName", patient.patientName)
                        .param("patientId", patient.patientId)
                )
                .andExpect(status().isOk());
//        MVCTestUtils.mockMvc(controller)
//                .perform(delete("/customer/{patientId}")
//                        .param("patientId", patient.patientId)
//                )
//                .andExpect(status().isOk());
    }

    private class DefaultPatient {
        public  String patientName = "BhimSingh" + DateTime.now().getMillis();
        public String patientId = "124155"+ DateTime.now().getMillis();
        public String company = "JSS";
    }
}

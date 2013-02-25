package org.jss.openerp.web.controller.it;

import org.joda.time.DateTime;
import org.jss.openerp.web.controller.OpenERPController;
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
public class OpenerpControllerIntegrationTest {

    @Autowired
    OpenERPController controller;

    @Test
    public void shouldReturnOkForATypicalRequest() throws Exception {
        DefaultPatient patient = new DefaultPatient();
        MVCTestUtils.mockMvc(controller)
                .perform(post("/customer")
                        .param("patientName", patient.patientName)
                        .param("patientId", patient.patientId)
                )
                .andExpect(status().isOk());
    }

    private class DefaultPatient {
        public  String patientName = "RamSingh" + DateTime.now().getMillis();
        public String patientId = "123455";
        public String company = "JSS";
    }
}

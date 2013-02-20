package org.jss.openerp.web.controller.it;

import org.joda.time.DateTime;
import org.jss.mocks.MockHttpClient;
import org.jss.openerp.web.controller.OpenerpController;
import org.jss.test.utils.ApplicationContextProvider;
import org.jss.test.utils.MVCTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-Web.xml", "classpath*:applicationContext-Mocks.xml"})
public class OpenerpControllerIntegrationTest {

    @Autowired
    OpenerpController controller;

    @Test
    public void shouldReturnOkForATypicalRequest() throws Exception {
        DefaultPatient patient = new DefaultPatient();
        MVCTestUtils.mockMvc(controller)
                .perform(post("/openerp/customer/create")
                        .param("patientName", patient.patientName)
                        .param("patientId", patient.patientId)
                        .param("company", patient.company)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotFailForNullCompany() throws Exception {
        DefaultPatient patient = new DefaultPatient();
        MVCTestUtils.mockMvc(controller)
                .perform(post("/openerp/customer/create")
                        .param("patientName", patient.patientName)
                        .param("patientId", patient.patientId)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotFailForBlankCompany() throws Exception {
        DefaultPatient patient = new DefaultPatient();
        MVCTestUtils.mockMvc(controller)
                .perform(post("/openerp/customer/create")
                        .param("patientName", patient.patientName)
                        .param("patientId", patient.patientId)
                        .param("company", "")
                )
                .andExpect(status().isOk());

    }

//    @Test(expected = NestedServletException.class)
//    public void shouldThrowExceptionIfApplicationThrowsIt() throws Exception {
//        MockHttpClient httpClient = contextHttpClient();
//        httpClient.throwException(new RuntimeException("Exception from Tally call"));
//
//        DefaultPatient patient = new DefaultPatient();
//        MVCTestUtils.mockMvc(controller)
//                .perform(post("/openerp/customer/create")
//                        .param("patientName", patient.patientName)
//                        .param("patientId", patient.patientId)
//                        .param("company", patient.company)
//                );
//    }


    private MockHttpClient contextHttpClient() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return (MockHttpClient) applicationContext.getBean("httpClient");
    }


    private class DefaultPatient {
        public  String patientName = "RamSingh" + DateTime.now().getMillis();
        public String patientId = "123455";
        public String company = "JSS";
    }
}

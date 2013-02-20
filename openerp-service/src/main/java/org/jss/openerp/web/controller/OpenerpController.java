package org.jss.openerp.web.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jss.openerp.web.service.OpenerpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/openerp/**")
public class OpenerpController {

    OpenerpService openerpService;

    private static Logger logger =Logger.getLogger("OpenerpController") ;

    @Autowired
    public OpenerpController(OpenerpService ledgerService){
       this.openerpService = ledgerService;
    }

    @RequestMapping(value = "/customer/create", method = RequestMethod.POST,headers="Accept=application/json")
    public @ResponseBody ResponseEntity<String> createCustomer(@RequestParam String patientName, @RequestParam String patientId, @RequestParam(required = false) String company) throws IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(StringUtils.isBlank(patientName) || StringUtils.isBlank(patientId)) {
            return new ResponseEntity<String>("Either patientName or patientId is blank",responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR)  ;
        }

        openerpService.createCustomer(patientName, patientId);

        return new ResponseEntity<String>(responseHeaders,HttpStatus.OK)  ;
    }

  }



package com.ddabadi.iso.iso8583.rest;

import com.ddabadi.iso.iso8583.model.Message;
import com.ddabadi.iso.iso8583.service.IsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping (value = "api")
public class IsoRest {

    @Autowired private IsoService isoService;

    @GetMapping(value = "asciiMsg/{reqString}")
    public String getRequestAscii(@PathVariable String reqString){

        return isoService.unpackMsgAscii(reqString);

//        return res.toString();
    };

    @GetMapping(value = "hexMsg/{reqString}", produces = "application/json")
    public String getRequestHex(@PathVariable String reqString){

//        Map<Integer, String> res = isoService.unpackMsgHex(reqString);
//        return res.toString();
        return isoService.unpackMsgHex(reqString);

    };

}

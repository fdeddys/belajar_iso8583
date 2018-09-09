package com.ddabadi.iso.iso8583.service;


import com.ddabadi.iso.iso8583.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class IsoService {

    public String unpackMsgAscii(String message){

        Map<Integer, String> res = new HashMap<>();
        System.out.printf("Message = %s%n", message);
        Message msg = null;
        try {
            // Load package from resources directory.
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.unpack(message.getBytes());
            msg = this.mapIsoToObj(isoMsg, res);

//            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
//                if (isoMsg.hasField(i)) {
//                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
////                    res.put(i,isoMsg.getString(i));
//                }
//            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(msg);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } catch (ISOException e) {
            System.out.println(e);

        }

        return null;
//        return msg.toString();
    }

    private Message mapIsoToObj(ISOMsg isoMsg,Map<Integer, String> res){
        Message message = new Message();
        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i)) {
                res.put(i,isoMsg.getString(i));
                switch (i){
                    case 0: message.setBit0(isoMsg.getString(i)); break;
                    case 1: message.setBit1(isoMsg.getString(i)); break;
                    case 2: message.setBit2(isoMsg.getString(i)); break;
                    case 3: message.setBit3(isoMsg.getString(i)); break;
                    case 4: message.setBit4(isoMsg.getString(i)); break;
                    case 5: message.setBit5(isoMsg.getString(i)); break;
                    case 6: message.setBit6(isoMsg.getString(i)); break;
                    case 7: message.setBit7(isoMsg.getString(i)); break;
                    case 8: message.setBit8(isoMsg.getString(i)); break;
                    case 9: message.setBit9(isoMsg.getString(i)); break;

                    case 10: message.setBit10(isoMsg.getString(i)); break;
                    case 11: message.setBit11(isoMsg.getString(i)); break;
                    case 12: message.setBit12(isoMsg.getString(i)); break;
                    case 13: message.setBit13(isoMsg.getString(i)); break;
                    case 14: message.setBit14(isoMsg.getString(i)); break;
                    case 15: message.setBit15(isoMsg.getString(i)); break;
                    case 16: message.setBit16(isoMsg.getString(i)); break;
                    case 17: message.setBit17(isoMsg.getString(i)); break;
                    case 18: message.setBit18(isoMsg.getString(i)); break;
                    case 19: message.setBit19(isoMsg.getString(i)); break;

                    case 20: message.setBit20(isoMsg.getString(i)); break;
                    case 21: message.setBit21(isoMsg.getString(i)); break;
                    case 22: message.setBit22(isoMsg.getString(i)); break;
                    case 23: message.setBit23(isoMsg.getString(i)); break;
                    case 24: message.setBit24(isoMsg.getString(i)); break;
                    case 25: message.setBit25(isoMsg.getString(i)); break;
                    case 26: message.setBit26(isoMsg.getString(i)); break;
                    case 27: message.setBit27(isoMsg.getString(i)); break;
                    case 28: message.setBit28(isoMsg.getString(i)); break;
                    case 29: message.setBit29(isoMsg.getString(i)); break;

                    case 30: message.setBit30(isoMsg.getString(i)); break;
                    case 31: message.setBit31(isoMsg.getString(i)); break;
                    case 32: message.setBit32(isoMsg.getString(i)); break;
                    case 33: message.setBit33(isoMsg.getString(i)); break;
                    case 34: message.setBit34(isoMsg.getString(i)); break;
                    case 35: message.setBit35(isoMsg.getString(i)); break;
                    case 36: message.setBit36(isoMsg.getString(i)); break;
                    case 37: message.setBit37(isoMsg.getString(i)); break;
                    case 38: message.setBit38(isoMsg.getString(i)); break;
                    case 39: message.setBit39(isoMsg.getString(i)); break;

                    case 40: message.setBit40(isoMsg.getString(i)); break;
                    case 41: message.setBit41(isoMsg.getString(i)); break;
                    case 42: message.setBit42(isoMsg.getString(i)); break;
                    case 43: message.setBit43(isoMsg.getString(i)); break;
                    case 44: message.setBit44(isoMsg.getString(i)); break;
                    case 45: message.setBit45(isoMsg.getString(i)); break;
                    case 46: message.setBit46(isoMsg.getString(i)); break;
                    case 47: message.setBit47(isoMsg.getString(i)); break;
                    case 48: message.setBit48(isoMsg.getString(i)); break;
                    case 49: message.setBit49(isoMsg.getString(i)); break;

                    case 50: message.setBit50(isoMsg.getString(i)); break;
                    case 51: message.setBit51(isoMsg.getString(i)); break;
                    case 52: message.setBit52(isoMsg.getString(i)); break;
                    case 53: message.setBit53(isoMsg.getString(i)); break;
                    case 54: message.setBit54(isoMsg.getString(i)); break;
                    case 55: message.setBit55(isoMsg.getString(i)); break;
                    case 56: message.setBit56(isoMsg.getString(i)); break;
                    case 57: message.setBit57(isoMsg.getString(i)); break;
                    case 58: message.setBit58(isoMsg.getString(i)); break;
                    case 59: message.setBit59(isoMsg.getString(i)); break;
                }
            }
        }
        return message;
    }


    public String unpackMsgHex(String message){

        String newMsg = this.convertToAscii(message);
        return unpackMsgAscii(newMsg);
    }

    private String convertToAscii(String msg){
        msg.substring(4).toString();
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < msg.length(); i += 2) {
            String str = msg.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        String message = output.toString();
        String h = message.trim().substring(1);
        message = h;
//        System.out.println("hasil ==> " + message);

        return message;
    }

}

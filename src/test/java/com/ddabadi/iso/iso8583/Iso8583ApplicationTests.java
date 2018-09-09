package com.ddabadi.iso.iso8583;

import org.jpos.iso.AsciiHexInterpreter;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SocketUtils;

import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Iso8583ApplicationTests {


    // msg Ascii
    // String message = "02003220000000808000000010000000001500120604120000000112340001840";

    // msg Hex
    String msg = "00843032303036323341343030313038433138303030303631333430303333383030303030383133313231313534303030303035313231313534303831333038313336303132303330303831353334313337313134352035345254534D534132303132313131313131313131313130313838393838383132333435363738393033303133363003";

//    String msg="009930323130373233413430303130414331383031383036313334303033333830303030303030303030303030303030303831333132313135343030303030353132313135343038313330383133363031323033303038313533343133373131343520323235345254534D534132303132313131313131313131313130313838393838383132333435363738393033303133363030303030303003";
    String message;

    @Before
    public void convertToAscii(){
        msg.substring(4).toString();
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < msg.length(); i += 2) {
            String str = msg.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        message = output.toString();
        String h = message.trim().substring(1);
        message = h;
        System.out.println("hasil ==> " + message);
    }

	public void contextLoads() {
	}

	@Test
	public void tes1Unpack(){

        System.out.printf("Message = %s%n", message);
        try {
            // Load package from resources directory.
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.unpack(message.getBytes());

            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            System.out.println(e);
        }
	}


    public void packIso(){
        try {
            // Load package from resources directory.
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            GenericPackager packager = new GenericPackager(is);

            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.setMTI("0200");

            isoMsg.set(3, "000010");
            isoMsg.set(4, "1500");
            isoMsg.set(7, "1206041200");
            isoMsg.set(11, "000001");
            isoMsg.set(41, "12340001");
            isoMsg.set(49, "840");
            byte[] result = isoMsg.pack();
            System.out.println("hasil result " + result.toString());

            System.out.printf("MTI = %s%n", isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }


        } catch (ISOException e) {
            System.out.println(e);
        }
    }

}

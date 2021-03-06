package com.dimidev.vdab.spring.pizzeria.restclients;

import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

//@Component -> created custom beans for restclients in BeanFactory
//@Qualifier("EcbRate")
@Order(1)
public class ECBRateClient implements CurrencyRateClient {

// MEMBER VARS

    private final URL ecbRateUrl;

// CONSTRUCTORS

    public ECBRateClient(
            @Value("${ecbRateUrl}") URL ecbRateUrl) {
        this.ecbRateUrl = ecbRateUrl;
    }


// METHODS
/*
 Factory design pattern > XMLInputFactory
      creates a XMLStreamReader using static method newInstance & using rest client url as inputstream.

 Iterate sequential through XML tags with hasNext()
 and search for the wanted xml attribute value using .getAttribute(namespace, attributeName) everywhere
        XMLStreamConstant.START_ELEMENT == reader.next()  -> returns int ~ which kind of xml element

 close the reader & catch exceptions
 */

    // OVERRIDDEN METHODS

    @Override
    public BigDecimal getDollarRating() {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try (InputStream inputStream = ecbRateUrl.openStream()) {
            XMLStreamReader xmlStreamReader  =xmlInputFactory.createXMLStreamReader(inputStream);
            try {
                while (xmlStreamReader.hasNext()) {
                    if (xmlStreamReader.next() == XMLStreamConstants.START_ELEMENT
                            && "USD".equals(xmlStreamReader.getAttributeValue(null, "currency"))) {
                        return new BigDecimal(xmlStreamReader.getAttributeValue(null, "rate"));
                    }
                }
                throw new CurrencyRateConvertorException("The XML source from ECB doesn't provide a USD rate");
            } finally {
                xmlStreamReader.close();
            }
        } catch (IOException | NumberFormatException | XMLStreamException ex) {
            throw new CurrencyRateConvertorException("Can't read currency from ECB sources" ,ex);
        }
    }



}

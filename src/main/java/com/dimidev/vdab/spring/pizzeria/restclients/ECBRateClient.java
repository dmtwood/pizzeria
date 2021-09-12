package com.dimidev.vdab.spring.pizzeria.restclients;

import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class ECBRateClient implements CurrencyRateClient {

// MEMBER VARS

    private final URL ecbRateUrl;

// CONSTRUCTORS

    public ECBRateClient() {
        try {
            this.ecbRateUrl = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        } catch (MalformedURLException e) {
            throw new CurrencyRateConvertorException("ECB url doesn't return a valid currency rate", e);
        }
    }


// GETTERS ( & SETTERS IF MUTABLE)


// METHODS
/*
 Factory design pattern > XMLInputFactory
      creates a XMLStreamReader using static method newInstance & using rest client url as inputstream.

 Iterate sequential through XML tags with hasNext()
 and search for the wanted xml attribute value using .getAttribute(namespace, attributeName) everywhere
        XMLStreamConstant.START_ELEMENT == reader.next()  -> returns int ~ which kind of xml element

 close the reader & catch exceptions
 */

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


// OVERRIDDEN METHODS

}

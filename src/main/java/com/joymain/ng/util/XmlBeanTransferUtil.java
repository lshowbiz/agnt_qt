package com.joymain.ng.util;

import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

/**
 * 
 * xml数据和javaBean相互转换的工具类
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-2 下午4:35:42
 * 
 */
public class XmlBeanTransferUtil<T extends Serializable> {
    private static Logger logger = Logger.getLogger(XmlBeanTransferUtil.class);

    private JAXBContext context;

    public XmlBeanTransferUtil(Class<T> clazz) {
        try {
            context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            logger.error("JAXBContext实例化异常:", e);
        }
    }

    public String bean2Xml(T obj) {
        StringWriter sw = new StringWriter();
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, sw);
            sw.flush();
        } catch (Exception e) {
            logger.error("bean转换成xml异常:", e);
        }
        return sw.toString();
    }

    @SuppressWarnings("unchecked")
    public T Xml2Bean(String xml) {
        T obj = null;
        try {
            StringReader st = new StringReader(xml);
            Unmarshaller jaxbUnmarShaller = context.createUnmarshaller();
            obj = (T) jaxbUnmarShaller.unmarshal(st);
        } catch (Exception e) {
            logger.error("xml转换成bean异常:", e);
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public T Xml2Bean(InputStream inputStream) {
        T obj = null;
        try {
            Unmarshaller jaxbUnmarShaller = context.createUnmarshaller();
            obj = (T) jaxbUnmarShaller.unmarshal(inputStream);
        } catch (Exception e) {
            logger.error("xml转换成bean异常:", e);
        }
        return obj;
    }
}

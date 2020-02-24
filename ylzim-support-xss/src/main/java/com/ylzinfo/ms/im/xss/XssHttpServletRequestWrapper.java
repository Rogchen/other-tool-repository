package com.ylzinfo.ms.im.xss;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/9/12 13:38
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Logger logger = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
        try {
            value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            value = value.replaceAll("\\+", "%2B");
            value = URLDecoder.decode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
         value = StringEscapeUtils.escapeHtml4(value);
         value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
         value = value.replaceAll("\\(", "& #40;").replaceAll("\\)",
         "& #41;");
         value = value.replaceAll("'", "& #39;");
         value = value.replaceAll("eval\\((.*)\\)", "");
         value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
         "\"\"");
         value = value.replaceAll("script", "");
        return value;
    }
}

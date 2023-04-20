package com.algaworks.algafood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.hibernate.engine.jdbc.StreamUtils;

public class ResourceUtils {

	public static String getContentFromResource(String resourceName) {
	    try {
	        InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
	        return org.springframework.util.StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}    
}

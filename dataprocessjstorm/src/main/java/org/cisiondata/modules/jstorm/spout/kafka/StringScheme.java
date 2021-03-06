package org.cisiondata.modules.jstorm.spout.kafka;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

@SuppressWarnings("serial")
public class StringScheme implements Scheme {
	
    private static final Charset UTF8_CHARSET = StandardCharsets.UTF_8;
    public static final String STRING_SCHEME_KEY = "str";
    public static final String BYTE_SCHEME_KEY = "bytes";
    
    public List<Object> deserialize(ByteBuffer bytes) {
        return new Values(deserializeString(bytes));
    }
    
    public Iterable<List<Object>> deserializeIterable(ByteBuffer bytes) {
    	System.out.println(new Values(deserializeString(bytes)));
        return Collections.singletonList(new Values(deserializeString(bytes)));
    }

    public static String deserializeString(ByteBuffer string) {
        if (string.hasArray()) {
            int base = string.arrayOffset();
            return new String(string.array(), base + string.position(), string.remaining(), UTF8_CHARSET);
        } else {
            return new String(Utils.toByteArray(string), UTF8_CHARSET);
        }
    }
    
	public List<Object> deserialize(byte[] ser) {
		return new Values(deserialize(ByteBuffer.wrap(ser)));
	}
    
    public static String deserializeString(byte[] ser) {
//    	System.out.println("3:" + new String(ser, UTF8_CHARSET));
    	return deserializeString(ByteBuffer.wrap(ser));
    }
    
    public Fields getOutputFields() {
        return new Fields(BYTE_SCHEME_KEY);
    }

}

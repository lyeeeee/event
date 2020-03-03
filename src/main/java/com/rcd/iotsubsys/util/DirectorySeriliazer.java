package com.rcd.iotsubsys.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-01-01 22:26
 */
public class DirectorySeriliazer extends StdSerializer<DirectoryUtil.DirectoryNode> {

    protected DirectorySeriliazer(Class<DirectoryUtil.DirectoryNode> t) {
        super(t);
    }
    @Override
    public void serialize(DirectoryUtil.DirectoryNode node, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
//        String
//        gen.writeString();
    }
}

package com.api.mfb.config;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.DeserializationFeature;
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        module.addSerializer(ObjectId.class, new ToStringSerializer());
        module.addDeserializer(ObjectId.class, new FromStringDeserializer<ObjectId>(ObjectId.class) {
            @Override
            protected ObjectId _deserialize(String value, DeserializationContext ctxt) {
                return new ObjectId(value);
            }
        });
        mapper.registerModule(module);
        return mapper;
    }
}




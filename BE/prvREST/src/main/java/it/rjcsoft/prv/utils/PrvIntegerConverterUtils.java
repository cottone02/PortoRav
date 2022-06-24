package it.rjcsoft.prv.utils;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class PrvIntegerConverterUtils implements AttributeConverter<Integer[], String> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Integer[] data) {
        String value = "";
        try {
            value = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("Error during converting attribute data={}, e={}", data, e.getMessage(), e);
        }
        return value;
    }

    @Override
    public Integer[] convertToEntityAttribute(String data) {
        if (data == null) {
            return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
        }
        try {
            data = StringUtils.replace(data, "\"", "");
            data = StringUtils.replace(data, "{", "");
            data = StringUtils.replace(data, "}", "");
            return Stream.of(data.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        } catch (Exception e) {
            log.error("Error during converting attribute data={}, e={}", data, e.getMessage(), e);
            return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
        }
    }

}

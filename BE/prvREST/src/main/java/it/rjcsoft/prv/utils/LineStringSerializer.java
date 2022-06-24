package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.LineString;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LineStringSerializer extends StdSerializer<LineString> {

	private static final long serialVersionUID = 1L;

	public LineStringSerializer() {
		this(null);
	}

	public LineStringSerializer(Class<LineString> t) {
		super(t);
	}

	@Override
	public void serialize(LineString value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(value != null ? value.toString() : null);
	}

}

package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PolygonSerializer extends StdSerializer<Polygon> {

	private static final long serialVersionUID = 1L;

	public PolygonSerializer() {
		this(null);
	}

	public PolygonSerializer(Class<Polygon> t) {
		super(t);
	}

	@Override
	public void serialize(Polygon value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(value != null ? value.toString() : null);
	}

}

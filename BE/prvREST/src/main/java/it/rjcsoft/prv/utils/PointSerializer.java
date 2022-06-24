package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PointSerializer extends StdSerializer<Point> {
	
	private static final long serialVersionUID = 1L;

	public PointSerializer() {
		this(null);
	}

	public PointSerializer(Class<Point> t) {
		super(t);
	}

	@Override
	public void serialize(Point value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(value != null ? value.toString() : null);
	}
	
}

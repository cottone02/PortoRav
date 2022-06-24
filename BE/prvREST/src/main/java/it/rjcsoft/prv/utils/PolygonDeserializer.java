package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class PolygonDeserializer extends StdDeserializer<Polygon> {

	private static final long serialVersionUID = 1L;

	public PolygonDeserializer() {
		this(null);
	}

	public PolygonDeserializer(Class<Polygon> t) {
		super(t);
	}

	@Override
	public Polygon deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node instanceof TextNode) {
			Polygon polygon = PrvGeometryUtils.convertToPolygon(((TextNode) node).textValue());
			if (polygon == null || polygon.isEmpty() || !polygon.isValid()) {
				throw new JsonMappingException(jp, "Invalid geometry");
			}
			return polygon;
		}
		return null;
	}

}

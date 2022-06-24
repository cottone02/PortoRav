package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class PointDeserializer extends StdDeserializer<Point> {

	private static final long serialVersionUID = 1L;

	public PointDeserializer() {
		this(null);
	}

	public PointDeserializer(Class<Point> t) {
		super(t);
	}

	@Override
	public Point deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node instanceof TextNode) {
			return PrvGeometryUtils.convertToPoint(((TextNode) node).textValue());
		}
		return null;
	}

}

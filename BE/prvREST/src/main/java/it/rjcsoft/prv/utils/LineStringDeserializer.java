package it.rjcsoft.prv.utils;

import java.io.IOException;

import org.locationtech.jts.geom.LineString;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class LineStringDeserializer extends StdDeserializer<LineString> {

	private static final long serialVersionUID = 1L;

	public LineStringDeserializer() {
		this(null);
	}

	public LineStringDeserializer(Class<LineString> t) {
		super(t);
	}

	@Override
	public LineString deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node instanceof TextNode) {
			return PrvGeometryUtils.convertToLineString(((TextNode) node).textValue());
		}
		return null;
	}

}

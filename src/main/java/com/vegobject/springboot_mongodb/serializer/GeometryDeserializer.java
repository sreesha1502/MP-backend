package com.vegobject.springboot_mongodb.serializer;

import java.io.IOException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        try {
            return new WKTReader().read(jsonParser.getText()); // Convert WKT back to Geometry
        } catch (Exception e) {
            throw new IOException("Failed to deserialize Geometry", e);
        }
    }

}

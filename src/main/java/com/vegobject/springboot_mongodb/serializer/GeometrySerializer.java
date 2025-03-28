package com.vegobject.springboot_mongodb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

public class GeometrySerializer extends JsonSerializer<Geometry>{

    @Override
     public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(geometry.toString()); // Converts Geometry to WKT format
    }

}

package com.zara.price.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateSerializer extends JsonSerializer<ZonedDateTime> {

  private DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]XXX");

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException {
    gen.writeString(formatZonedDateTime(value));
  }

  private String formatZonedDateTime(ZonedDateTime date) {
    return formatter.format(date);
  }
}



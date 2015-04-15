package de.chkal.todo.ext;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Provider
public class LocalDateParamConverterProvider implements ParamConverterProvider {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

  @Override
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

    if (LocalDate.class.equals(rawType)) {

      return new ParamConverter<T>() {

        @Override
        public T fromString(String value) {
          if (value != null && value.trim().length() > 0) {
            return (T) LocalDate.parse(value, FORMATTER);
          }
          return null;
        }

        @Override
        public String toString(T value) {
          return ((LocalDate) value).format(FORMATTER);
        }

      };

    }

    return null;

  }

}

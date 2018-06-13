package pl.coderslab.snakesprogram.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateConverter implements Converter<String, LocalDate> {


    @Override
    public LocalDate convert(String date) {
        return LocalDate.parse(date);
    }
}

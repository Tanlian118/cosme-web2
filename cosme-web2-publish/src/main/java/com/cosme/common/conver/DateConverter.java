package com.cosme.common.conver;


import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author Tanlian
 * @create 2018-08-25 15:19
 **/
public class DateConverter implements Converter<String, Date> {


    @Override
    public Date convert(String source) {
        Predicate<String> predicate = Pattern.compile("^\\d+$").asPredicate();
        boolean isNumber = predicate.test(source);

        if (!StringUtils.hasText(source) || !isNumber ) {
            return null;
        }
        return new Date(Long.valueOf(source));
    }
}

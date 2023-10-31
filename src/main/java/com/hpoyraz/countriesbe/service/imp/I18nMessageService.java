package com.hpoyraz.countriesbe.service.imp;

import com.hpoyraz.countriesbe.service.interfaces.II18nMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class I18nMessageService implements II18nMessageService {

    private final MessageSource messageSource;
    @Override
    public String getMessage(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}

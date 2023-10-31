package com.hpoyraz.countriesbe.service.interfaces;

import java.util.Locale;

public interface II18nMessageService {

    String getMessage(String code, Locale locale, Object... args);
}

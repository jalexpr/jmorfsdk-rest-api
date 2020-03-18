package ru.textanalysis.tawt.rest.common.exception;

import ru.textanalysis.common.rest.exception.RestCommonException;

public class TawtRestException extends RestCommonException {
    public TawtRestException() {
    }

    public TawtRestException(String s) {
        super(s);
    }

    public TawtRestException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TawtRestException(Throwable throwable) {
        super(throwable);
    }
}

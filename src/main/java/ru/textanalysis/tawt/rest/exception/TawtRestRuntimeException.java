package ru.textanalysis.tawt.rest.exception;

import ru.textanalysis.common.rest.exception.RestCommonRuntimeException;

public class TawtRestRuntimeException extends RestCommonRuntimeException {
    public TawtRestRuntimeException() {
    }

    public TawtRestRuntimeException(String s) {
        super(s);
    }

    public TawtRestRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TawtRestRuntimeException(Throwable throwable) {
        super(throwable);
    }

}

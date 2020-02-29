package ru.textanalysis.tawt.jmorfsdk.rest.exception;

import ru.textanalysis.common.rest.exception.RestCommonException;

public class JMorfSdkRestException extends RestCommonException {
    public JMorfSdkRestException() {
    }

    public JMorfSdkRestException(String s) {
        super(s);
    }

    public JMorfSdkRestException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public JMorfSdkRestException(Throwable throwable) {
        super(throwable);
    }
}

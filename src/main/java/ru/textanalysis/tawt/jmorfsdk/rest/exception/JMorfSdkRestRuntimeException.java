package ru.textanalysis.tawt.jmorfsdk.rest.exception;

import ru.textanalysis.common.rest.exception.RestCommonRuntimeException;

public class JMorfSdkRestRuntimeException extends RestCommonRuntimeException {
    public JMorfSdkRestRuntimeException() {
    }

    public JMorfSdkRestRuntimeException(String s) {
        super(s);
    }

    public JMorfSdkRestRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public JMorfSdkRestRuntimeException(Throwable throwable) {
        super(throwable);
    }

}

package com.lulobank.insurance.clients;

import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import com.lulobank.insurance.validation.ValidationMessages;
import reactor.util.retry.Retry;

import java.util.concurrent.TimeoutException;
import java.time.Duration;

public class RetryPolicy {

    private RetryPolicy() {
    }

    public static Retry defaultRetry() {
        return Retry.backoff(3, Duration.ofSeconds(2))
                .filter(throwable -> throwable instanceof TimeoutException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        new GenericBadRequestException(
                                String.format(ValidationMessages.GENERIC_API_TIMEOUT_ERROR_MESSAGE, retrySignal.failure().getLocalizedMessage()),
                                BuildErrorResponseUtil.build(ValidationMessages.TIMEOUT_ERROR_CODE, ValidationMessages.TIMEOUT_ERROR_MESSAGE)));
    }
}


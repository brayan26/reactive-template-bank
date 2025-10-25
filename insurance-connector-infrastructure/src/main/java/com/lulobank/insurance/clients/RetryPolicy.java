package com.lulobank.insurance.clients;

import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.commons.util.BuildErrorResponseUtil;
import com.lulobank.insurance.validation.ValidationMessages;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public final class RetryPolicy {

    private RetryPolicy() {
    }

    public static Retry defaultRetry(final int maxAttempts, final int duration) {
        return Retry.backoff(maxAttempts, Duration.ofSeconds(duration))
                .filter(throwable -> throwable instanceof TimeoutException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        new GenericBadRequestException(
                                String.format(ValidationMessages.GENERIC_API_TIMEOUT_ERROR_MESSAGE,
                                        retrySignal.failure().getLocalizedMessage()),
                                BuildErrorResponseUtil.build(ValidationMessages.TIMEOUT_ERROR_CODE,
                                        ValidationMessages.TIMEOUT_ERROR_MESSAGE)));
    }
}


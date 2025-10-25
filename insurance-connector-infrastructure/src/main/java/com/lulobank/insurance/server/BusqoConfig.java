package com.lulobank.insurance.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.commons.util.BuildErrorResponseUtil;
import com.lulobank.insurance.validation.ValidationMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Data
@Component
public class BusqoConfig {
    private final ObjectMapper mapper;

    @Value("${busqo.config}")
    private String config;

    @Value("${busqo.offerUrl}")
    private String offerUrl;

    public BusqoConfig(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public BusqoProperties parse() {
        try {
            return mapper.readValue(config, BusqoProperties.class);
        } catch (JsonProcessingException e) {
            throw new GenericBadRequestException(
                    String.format("<BusqoConfig - parse> Error parseando json BusqoConfig: <%s> configJson: %s", e.getMessage(), config),
                    BuildErrorResponseUtil.build(ValidationMessages.JSON_SERIALIZING_ERROR_CODE, ValidationMessages.JSON_SERIALIZING_ERROR_MESSAGE));
        }
    }

    public record DocumentType(
            String id,
            String documentType
    ) {
    }

    public record BusqoProperties(
            String baseUrl,
            List<DocumentType> documentTypeMapping
    ) {
        public String validateAndGetDocumentType(final String documentType) {
            return Optional.ofNullable(documentTypeMapping)
                    .orElse(List.of())
                    .stream()
                    .filter(type ->
                            type.documentType().equalsIgnoreCase(documentType))
                    .findFirst()
                    .orElseThrow()
                    .id();
        }
    }
}


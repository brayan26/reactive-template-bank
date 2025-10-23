package com.lulobank.insurance.validation;

public class ValidationMessages {
    public static final String UNKNOW_ERROR_CODE = "INS_100";
    public static final String UNKNOW_ERROR_DESCRIPTION = "Unknow error, please contact to technical support";

    public static final String ERROR_PARSING_JSON = "{\"error\": \"error serializing json response\"}";
    public static final String PLATE_VALIDATION = "La placa, no coincide con alguno de los siguientes formatos: AAA000, AA000A, AA000, 000AAA";

    public static final String TIMEOUT_ERROR_CODE = "INS_101";
    public static final String TIMEOUT_ERROR_MESSAGE = "Error timeout al intentar comunicarse con el servicio externo";

    public static final String JSON_SERIALIZING_ERROR_CODE = "INS_102";
    public static final String JSON_SERIALIZING_ERROR_MESSAGE = "Error serializando json de configuracion";

    public static final String GENERIC_API_TIMEOUT_ERROR_MESSAGE = "Timeout al comunicarse con el API externo: %s";
    public static final String BUSQO_GENERIC_ERROR_MESSAGE = "Error al intentar comunicarse con el servicio de Busqo";
}

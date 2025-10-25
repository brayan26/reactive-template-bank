package com.lulobank.insurance.mapper;

import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.server.BusqoConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BusqoMapper {

    @Mapping(source = "document.id", target = "identification",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "identificationType",
            expression = "java(properties.validateAndGetDocumentType(dto.document().type()))")
    @Mapping(source = "phone.number", target = "mobilePhone",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    BusqoRequest toDomain(SoatOffersRequestDto dto, @Context BusqoConfig.BusqoProperties properties);
}

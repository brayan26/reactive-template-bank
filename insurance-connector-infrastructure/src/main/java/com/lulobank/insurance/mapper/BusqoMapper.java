package com.lulobank.insurance.mapper;

import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BusqoMapper {

    @Mapping(source = "document.id", target = "identification", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "document.type", target = "identificationType", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "phone.number", target = "mobilePhone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    BusqoRequest toDomain(SoatOffersRequestDto dto);
}

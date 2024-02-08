package com.anik.app.config;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
      typeConversionPolicy = ReportingPolicy.ERROR,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public class CommonMapperConfig {
}
package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;
import lombok.AllArgsConstructor;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses=UpdateUserDTOHelper.class
)
public interface UpdateUserDTOMapper {
    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="imageUrl", target= "attributes", qualifiedByName ="imageUrltoAttributes")
    KeycloakUser partialUpdate(UpdateUserDTO updateUserDTO, @MappingTarget KeycloakUser user);
}

@Component
@AllArgsConstructor
class UpdateUserDTOHelper {

    @Named("imageUrltoAttributes")
    public Map<String, List<String>> handleImageUrlMapping(String imageUrl) {
        return Map.of("imageUrl", List.of(imageUrl));
    }

}

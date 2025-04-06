package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UpdateUserDTOMapper {

    /**
     * Fornece um {@link User} atualizado a partir de {@link UpdateUserDTO}.
     * <br>
     * <br>
     * <b>OBS.:</b> a propriedade imageUrl do usuário não é resolvida por este
     * mapper. Portanto, você mesmo deve gerar a url da imagem para settar
     * no objeto {@link User} que está sendo atualizado. Por exemplo:
     * <pre>
     *  updateUserDTOMapper.partialUpdate(user, updateUserDTO);
     *  user.setImageUrl(fooMediaStorage.saveImage(
     *      updateUserDTO.image()
     *  ));
     * </pre>
     * @return {@link User} atualizado sem o atributo <code>imageUrl</code>
     * definido
     */
    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    User partialUpdate(
        UpdateUserDTO updateUserDTO,
        @MappingTarget User user
    );

}

package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.*;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy=ReportingPolicy.IGNORE
)
public interface CreateUserDTOMapper {

    /**
     * Gera um objeto {@link User} a partir de {@link CreateUserDTO}.
     * <br>
     * <br>
     * <b>OBS.:</b> a propriedade imageUrl do usuário não é resolvida por este
     * mapper. Portanto, você mesmo deve gerar a url da imagem para settar
     * no objeto {@link User} gerado. Por exemplo:
     * <pre>
     *  User user = createUserDTOMapper.toEntity(createUserDTO);
     *  user.setImageUrl(fooMediaStorage.saveImage(
     *      createUserDTO.image()
     *  ));
     * </pre>
     * @return {@link User} sem o atributo <code>imageUrl</code> definido
     */
    User toEntity(CreateUserDTO createUserDTO);

}

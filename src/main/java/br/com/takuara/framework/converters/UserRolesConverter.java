package br.com.takuara.framework.converters;

import br.com.takuara.framework.security.Role;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class UserRolesConverter  implements AttributeConverter<List<Role>, String> {

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        return StringUtils.join(roles, ",");
    }

    @Override
    public List<Role> convertToEntityAttribute(String list) {
        return Stream.of(list.split(","))
                     .map(Role::valueOf)
                     .collect(Collectors.toList());
    }
}

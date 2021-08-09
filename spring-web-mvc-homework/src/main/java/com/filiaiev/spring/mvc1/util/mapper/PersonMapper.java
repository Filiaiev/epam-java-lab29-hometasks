package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.person.PersonDto;
import com.filiaiev.spring.mvc1.dto.person.PersonShortDto;
import com.filiaiev.spring.mvc1.model.Person;
import org.mapstruct.factory.Mappers;

public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonDto dto);

    Person toPerson(PersonShortDto dto);

    PersonShortDto toPersonShortDto(Person person);

    PersonDto toPersonDto(Person person);
}

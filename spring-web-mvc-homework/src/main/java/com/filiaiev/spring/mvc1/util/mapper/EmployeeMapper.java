package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.employee.EmployeeShortDto;
import com.filiaiev.spring.mvc1.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANSE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEmployee(EmployeeShortDto dto);

    EmployeeShortDto toEmployeeShort(Employee employee);
}

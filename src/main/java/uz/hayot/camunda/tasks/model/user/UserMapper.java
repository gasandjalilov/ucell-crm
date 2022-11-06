package uz.hayot.camunda.tasks.model.user;

import liquibase.pro.packaged.M;
import org.mapstruct.*;
import uz.hayot.camunda.tasks.dto.camunda.NibbdResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "patronymic",source = "patronym")
    @Mapping(target = "dateExpiry", source = "dateExpiry")
    @Mapping(target = "inn",source = "inn")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "dateIssue", source = "dateIssue")
    @Mapping(target = "docNumber", source = "documentNumber")
    @Mapping(target = "docSeries", source = "documentSeries")
    @Mapping(target = "nationality", source = "nationality")
    @Mapping(target = "lastname", source = "surname")
    @Mapping(target = "firstname", source = "givenname")
    @Mapping(target = "pinfl", source = "pinfl")
    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "district", ignore = true)
    User update(NibbdResponse response,@MappingTarget User user);
}

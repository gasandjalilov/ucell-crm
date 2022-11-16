package uz.ucell.tasks.model.user;

import org.mapstruct.*;

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
    @Mapping(target = "lastname", source = "surname")
    @Mapping(target = "firstname", source = "givenname")
    @Mapping(target = "pinfl", source = "pinfl")
    @Mapping(target = "birthPlace", source = "birthPlace")
    @Mapping(target = "registrationPlace", source = "givePlace")
    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "district", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    User update(NibbdResponse response,@MappingTarget User user);

    @Mapping(target = "passportDateExpiration",source = "dateExpiry")
    @Mapping(target = "pinfl",source = "pinfl")
    @Mapping(target = "birthday",source = "birthDate")
    @Mapping(target = "patronymic", source = "patronymic")
    @Mapping(target = "phoneMobile",source = "phoneMain")
    @Mapping(target = "phoneHome", source = "phoneAdditional")
    @Mapping(target = "codeCitizenship", source = "country.id")
    @Mapping(target = "codeAdrDistrict", source = "district.id")
    @Mapping(target = "codeAdrRegion",source = "region.id")
    @Mapping(target = "codeCountry", source = "country.id")
    @Mapping(target = "codeNation", source = "nationality.id")
    @Mapping(target = "birthPlace", source = "birthPlace")
    @Mapping(target = "codeBirthDistr", source = "district.id")
    @Mapping(target = "codeBirthRegion", source = "region.id")
    @Mapping(target = "firstName", source = "firstname")
    @Mapping(target = "family", source = "lastname")
    @Mapping(target = "codeGender", source = "sex.id")
    @Mapping(target = "passportSerial", source = "docSeries")
    @Mapping(target = "passportNumber", source = "docNumber")
    @Mapping(target = "typeDocument", source = "docType.id")
    @Mapping(target = "passportDateRegistration", source = "dateIssue")
    @Mapping(target = "postAddress", source = "address")
    @Mapping(target = "codeResident", source = "resident")
    @Mapping(target = "passportPlaceRegistration", source = "registrationPlace")
    OpenIdClientRequest nciMapper(User user);


}

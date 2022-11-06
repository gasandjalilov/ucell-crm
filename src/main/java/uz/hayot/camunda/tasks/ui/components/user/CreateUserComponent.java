package uz.hayot.camunda.tasks.ui.components.user;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import io.camunda.zeebe.client.ZeebeClient;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import uz.hayot.camunda.tasks.configuration.keycloak.SecurityService;
import uz.hayot.camunda.tasks.dto.camunda.NibbdRequest;
import uz.hayot.camunda.tasks.dto.camunda.NibbdResponse;
import uz.hayot.camunda.tasks.model.user.*;
import uz.hayot.camunda.tasks.service.KeycloakService;
import uz.hayot.camunda.tasks.service.UserService;
import uz.hayot.camunda.tasks.ui.MainUserInterface;
import uz.hayot.camunda.tasks.ui.helper.TranslationProvider;

import javax.annotation.security.PermitAll;
import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@PermitAll
@Route(value = "user-create", layout = MainUserInterface.class)
@Component
@Slf4j
@UIScope
@PageTitle("Создать Пользователя")
public class CreateUserComponent extends VerticalLayout implements LocaleChangeObserver {

    private static final String NUMBER_PATTERN="[\\d\\-+()]";
    private final UserService userService;
    private final ZeebeClient zeebeClient;
    private final String nibbd;

    private final SecurityService securityService;

    TextField firstname = new TextField();
    TextField lastname = new TextField();
    TextField patronymic = new TextField();
    ComboBox<DocType> docTypeComboBox = new ComboBox<>();
    TextField docSeries = new TextField();
    TextField docNumber = new TextField();
    TextField phoneMain = new TextField();
    TextField phoneAdditional = new TextField();
    Button validate = new Button();
    Button check = new Button();
    DatePicker birthDate = new DatePicker();

    TextField inn = new TextField();
    TextField pinfl = new TextField();
    ComboBox<SexType> sex = new ComboBox<>();
    TextField address = new TextField();
    ComboBox<Region> region = new ComboBox<>();
    ComboBox<Country> country = new ComboBox<>();
    ComboBox<District> district = new ComboBox<>();
    DatePicker dateIssue = new DatePicker();
    DatePicker dateExpiry = new DatePicker();
    TextField nationality = new TextField();

    ProgressBar progressBar = new ProgressBar();

    Binder<User> binder = new Binder<>(User.class);
    User user = new User();

    UI ui = UI.getCurrent();
    FormLayout formLayout = new FormLayout();
    FormLayout hiddenForm = new FormLayout();

    public CreateUserComponent(ZeebeClient zeebeClient,
                               @Value("${bpm.client.nibbd}") String nibbd,
                               UserService userService,
                               SecurityService securityService) {
        this.userService = userService;
        this.zeebeClient = zeebeClient;
        this.nibbd = nibbd;
        this.securityService = securityService;
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);
        this.add(progressBar);
        formLayout.setWidthFull();
        hiddenForm.setWidthFull();
        hiddenForm.setVisible(false);

        binder.setBean(user);
        docTypeComboBox.setItems(userService.docTypes());
        docTypeComboBox.setItemLabelGenerator(DocType::getNameRu);
        region.setItems(userService.regions());
        region.setItemLabelGenerator(Region::getNameRu);
        district.setItems(userService.districts());
        district.setItemLabelGenerator(District::getNameRu);
        country.setItems(userService.countries());
        sex.setItems(userService.sexTypes());
        sex.setItemLabelGenerator(SexType::getNameRu);
        country.setItemLabelGenerator(Country::getNameRu);

        inn.setAllowedCharPattern(NUMBER_PATTERN);
        pinfl.setAllowedCharPattern(NUMBER_PATTERN);
        docNumber.setAllowedCharPattern(NUMBER_PATTERN);
        phoneMain.setAllowedCharPattern(NUMBER_PATTERN);
        phoneAdditional.setAllowedCharPattern(NUMBER_PATTERN);

        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("yyyy-MM-dd");

        birthDate.setI18n(singleFormatI18n);
        dateExpiry.setI18n(singleFormatI18n);
        dateIssue.setI18n(singleFormatI18n);


        check.addClickListener(buttonClickEvent -> {
            if (binder.validate().isOk()) nibbdRequest();
        });
        validate.addClickListener(buttonClickEvent -> {
            addUser();
        });
        formLayout.add(firstname,
                lastname,
                patronymic,
                docTypeComboBox,
                docSeries,
                docNumber,
                phoneMain,
                phoneAdditional,
                birthDate,
                check,
                validate);
        formLayout.setColspan(check, 2);
        hiddenForm.add(
                inn,
                pinfl,
                sex,
                address,
                region,
                country,
                district,
                nationality,
                dateIssue,
                dateExpiry,
                validate);
        formLayout.setColspan(validate, 2);
        check.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        validate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        this.add(formLayout,hiddenForm);
        binder();
    }


    public void binder() {
        binder.forField(firstname).asRequired().bind(User::getFirstname, User::setFirstname);
        binder.forField(lastname).bind(User::getLastname, User::setLastname);
        binder.forField(patronymic).bind(User::getPatronymic, User::setPatronymic);
        binder.forField(docTypeComboBox).bind(User::getDocType, User::setDocType);
        binder.forField(docSeries).bind(User::getDocSeries, User::setDocSeries);
        binder.forField(docNumber).bind(user1 -> Objects.toString(user1.getDocNumber(),""),(user1, s) -> user1.setDocNumber(Long.valueOf(s)));
        binder.forField(phoneMain).asRequired().bind(user1 -> Objects.toString(user1.getPhoneMain(),""),(user1, s) -> user1.setPhoneMain(Long.valueOf(s)));
        binder.forField(phoneAdditional).bind(user1 -> Objects.toString(user1.getPhoneAdditional(),""),(user1, s) -> user1.setPhoneAdditional(Long.valueOf(s)));
        binder.forField(birthDate).asRequired().bind(User::getBirthDate, User::setBirthDate);
        binder.forField(inn).bind(user1 -> Objects.toString(user1.getInn(), ""),(user1, s) -> user1.setInn(Long.valueOf(s)));
        binder.forField(pinfl).bind(user1 -> Objects.toString(user1.getPinfl(),""),(user1, s) -> user1.setPinfl(Long.valueOf(s)));
        binder.forField(region).bind(User::getRegion,User::setRegion);
        binder.forField(district).bind(User::getDistrict,User::setDistrict);
        binder.forField(country).bind(User::getCountry,User::setCountry);
        binder.forField(dateIssue).bind(User::getDateIssue,User::setDateIssue);
        binder.forField(dateExpiry).bind(User::getDateExpiry,User::setDateExpiry);
        binder.forField(sex).bind(User::getSex,User::setSex);
        binder.forField(address).bind(User::getAddress,User::setAddress);
        binder.forField(nationality).bind(User::getNationality,User::setNationality);
    }


    public void nibbdRequest() {
        progressBar.setVisible(true);
        DefaultOidcUser userData = securityService.getAuthenticatedUser();
        NibbdRequest request = new NibbdRequest(binder.getBean(), UUID.fromString(userData.getAttribute("sub")));
        log.info("Request: {}", request);
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(nibbd)
                .latestVersion()
                .variables(request)
                .withResult()
                .requestTimeout(Duration.ofSeconds(10))
                .send()
                .whenCompleteAsync((processInstanceResult, throwable) -> {
                    try {
                        if (throwable == null) {
                            ui.access(() -> {
                                binder.setBean(userService.update(processInstanceResult.getVariablesAsType(NibbdResponse.class), user));
                                progressBar.setVisible(false);
                                hiddenForm.setVisible(true);
                                ui.push();
                            });

                        } else if (throwable instanceof StatusRuntimeException) {
                            final StatusRuntimeException statusException = (StatusRuntimeException) throwable;
                            final Status.Code code = statusException.getStatus().getCode();
                            log.error("Caught: {}", code);
                            ui.access(() -> {
                                hiddenForm.setVisible(true);
                                progressBar.setVisible(false);
                                Notification.show("Error").open();
                            });

                        }
                    } catch (Exception e) {
                        log.error("Caught Error: {}", e.getMessage());
                    }
                });


    }


    public void addUser(){
        userService.add(binder.getBean());
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        firstname.setLabel(getTranslation("user.create.firstname"));
        lastname.setLabel(getTranslation("user.create.lastname"));
        patronymic.setLabel(getTranslation("user.create.patronymic"));
        docTypeComboBox.setLabel(getTranslation("user.create.doc.type"));
        docSeries.setLabel(getTranslation("user.create.doc.series"));
        docNumber.setLabel(getTranslation("user.create.doc.number"));
        phoneMain.setLabel(getTranslation("user.create.phone.main"));
        phoneAdditional.setLabel(getTranslation("user.create.phone.additional"));
        birthDate.setLabel(getTranslation("user.create.birthdate"));
        validate.setText(getTranslation("user.create.commit"));
        inn.setLabel(getTranslation("user.create.inn"));
        pinfl.setLabel(getTranslation("user.create.pinfl"));
        sex.setLabel(getTranslation("user.create.sex"));
        address.setLabel(getTranslation("user.create.address"));
        region.setLabel(getTranslation("user.create.region"));
        country.setLabel(getTranslation("user.create.country"));
        district.setLabel(getTranslation("user.create.district"));
        nationality.setLabel(getTranslation("user.create.nationality"));
        dateIssue.setLabel(getTranslation("user.create.date.issue"));
        dateExpiry.setLabel(getTranslation("user.create.date.expiry"));
        check.setText(getTranslation("user.create.check"));
    }
}

package uz.ucell.tasks.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.Lumo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import uz.ucell.tasks.configuration.keycloak.SecurityService;
import uz.ucell.tasks.ui.components.TabComponent;

import javax.annotation.security.PermitAll;


@Slf4j
@Component
@PermitAll
@UIScope
@Route("/")
public class MainUserInterface extends AppLayout  {


    private final SecurityService securityService;



    public MainUserInterface(TabComponent tabs, SecurityService securityService) {
        this.securityService = securityService;
        DrawerToggle toggle = new DrawerToggle();
        Image image = new Image("icons/icon.png","Ucell");
        image.setHeight(10, Unit.MM);
        image.setWidth(10, Unit.MM);
        toggle.setIcon(image);
        H1 title = new H1("Ucell");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(toggle, title,user());
        addToDrawer(tabs.getTabs());

    }




    private Div user(){
        Div user = new Div();
        user.getStyle().set("margin-left", "auto");
        user.getStyle().set("padding", "15px");
        MenuBar menuBar = new MenuBar();
        Button colorChanger = new Button(new Icon(VaadinIcon.ADJUST),click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });
        colorChanger.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        colorChanger.setIconAfterText(true);
        MenuItem move = menuBar.addItem(colorChanger);
        MenuItem userAction = menuBar.addItem(new Icon(VaadinIcon.WRENCH));
        SubMenu actionSubMenu = userAction.getSubMenu();
        actionSubMenu.addItem("Выйти", menuItemClickEvent -> {
            getUI().ifPresent(ui -> ui.access(() -> {
                Dialog exitDialog = new Dialog();
                DefaultOidcUser userData = securityService.getAuthenticatedUser();
                exitDialog.setHeaderTitle(String.format("%s,вы уверены что хотите выйти?",userData.getAttribute("sub").toString()));
                Button yes = new Button("Да", buttonClickEvent -> {
                    VaadinSession.getCurrent().close();
                });
                Button no = new Button("Нет", buttonClickEvent -> {
                    exitDialog.close();
                });
                exitDialog.getFooter().add(yes,no);
                exitDialog.open();
            }));
        });

        user.add(menuBar,userAction);
        return user;
    }
}

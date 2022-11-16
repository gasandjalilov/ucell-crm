package uz.ucell.tasks.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import uz.ucell.tasks.ui.components.task.TasksComponent;
import uz.ucell.tasks.ui.components.user.UserComponent;


@org.springframework.stereotype.Component
public class TabComponent {


    public Tabs getTabs(){

        Tabs tabs = new Tabs();
        tabs.add(
                createTab(new Icon(VaadinIcon.GROUP),"Пользователи", UserComponent.class),
                createTab(new Icon(VaadinIcon.TASKS),"Задачи", TasksComponent.class),
                createTab(new Icon(VaadinIcon.SUITCASE),"Продукты", TasksComponent.class),
                createTab(new Icon(VaadinIcon.TABLE),"Заявки", TasksComponent.class),
                createTab(new Icon(VaadinIcon.ENVELOPES),"Чат", TasksComponent.class),
                createTab(new Icon(VaadinIcon.LINE_BAR_CHART),"Отчеты", TasksComponent.class),
                createTab(new Icon(VaadinIcon.USERS),"Контакты", TasksComponent.class),
                createTab(new Icon(VaadinIcon.NEWSPAPER),"Новости", TasksComponent.class),
                createTab(new Icon(VaadinIcon.WRENCH),"Настройки", TasksComponent.class)
                );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private RouterLink routeLink(String label, Class clazz, Icon icon)
    {
        RouterLink link =  new RouterLink(label, clazz);
        link.setHighlightCondition(HighlightConditions.sameLocation());
        link.add(icon);
        return link;
    }


    private static Tab createTab(Icon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(viewClass), icon, title));
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.add(content);
        return tab;
    }

    private static <T extends HasComponents> T populateLink(T a, Icon icon, String title) {
        icon.getElement().getStyle().set("margin-right", "10px");
        a.add(icon);
        a.add(title);
        return a;
    }

}

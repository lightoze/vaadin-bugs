package net.lightoze.vaadin_bugs.spring;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.util.stream.IntStream;

@Route
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() {
        setSizeFull();
        Grid<Person> grid = new Grid<>();

        grid.setDataProvider(DataProvider.fromCallbacks(
                query -> IntStream
                        .range(query.getOffset(),
                                query.getOffset() + query.getLimit())
                        .mapToObj(index -> Person.create(index + 1)),
                query -> 1000));

        grid.addColumn(Person::getName).setHeader("Name");
        grid.addColumn(Person::getAge).setHeader("Age");
        add(grid);

        grid.addSelectionListener(event ->
                event.getFirstSelectedItem().ifPresent(person -> {
                    event.getSource().deselectAll();
                    event.getSource().getUI().ifPresent(ui -> ui.navigate(PersonView.class, person.getId()));
                }));
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content instanceof Dialog) {
            Dialog dialog = (Dialog) content;
            dialog.open();
            dialog.getElement().addEventListener("opened-changed", event -> {
                getUI().ifPresent(ui -> ui.navigate(MainView.class));
            });
        } else if (content != null) {
            throw new IllegalArgumentException();
        }
    }

}

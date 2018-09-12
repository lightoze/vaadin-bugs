package net.lightoze.vaadin_bugs.spring;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = "person", layout = MainView.class)
public class PersonView extends Dialog implements HasUrlParameter<Integer> {
    private final Label label = new Label();

    public PersonView() {
        add(label);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        if (integer != null) {
            label.setText(Person.create(integer).toString());
        }
    }
}

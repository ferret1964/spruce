package com.cognitivemedicine.demo.spruce.views.content;

import com.cognitivemedicine.demo.spruce.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.parameters.P;

//@PageTitle("Disability")
@Route(value = "")
@PermitAll
public class FileDisabilityView extends VerticalLayout {

    private Button backButton;
    private Button nextButton;

    public FileDisabilityView()  {

        /*
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);
        */


        //setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, name, sayHello);

        //add(name, sayHello);

        add(new H1("File for disability compensation"));
        add("Equal to VA Form 21-526EZ");
        add(getProgress(), getFlow());
        add(getHelp());

    }
    VerticalLayout getHelp()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(Alignment.START);
        layout.add(new H3("Need Help?"));
        Hr sep= new Hr();
        layout.add(sep);
        layout.add(new Html("<p>For help filling out this form, or if the form isn't working right, please call VA Benefits and Services at <u>800-827-1000</u></p>"));
        layout.add(new Html("<p>If you have hearing loss, call <u>TTY: 711</u></p>"));
        return layout;
    }
    HorizontalLayout getProgress()
    {
        HorizontalLayout layout = new HorizontalLayout();
        ProgressBar b1 = new ProgressBar();
        String stageWidth = "100px";

        b1.setValue(1.0);
        b1.setWidth(stageWidth);
        layout.add(b1);
        for (int i =0; i<4; i++)
        {
            ProgressBar bar = new ProgressBar();
            bar.setWidth(stageWidth);
            layout.add(bar);
        }
        return layout;
    }
    VerticalLayout getFlow()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setAlignItems(Alignment.START);
        layout.add(new H3("Step 1 of 5: Veteran Details"));
        layout.add("We'll save your application on every change");
        layout.add(getPersonalInfo());
        HorizontalLayout hl = new HorizontalLayout();
        backButton = new Button("<< Back");
        backButton.setEnabled(false);
        nextButton = new Button(" Continue >>");
        nextButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        hl.add(backButton, nextButton);
        layout.add(hl);
        return layout;
    }

    VerticalLayout getPersonalInfo()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.add("This is the personal information we have in file for you.");
        layout.add(getPersonalDetail());
        layout.add(new Html("<p><b>Note:</b> If you need to update your personal information please call Veterans Benefits Assistance at <u>800-827-1000</u>, Monday through Friday, 8:00 a.m. tp 9:00 p.m. ET</p>"));
        layout.add(new Anchor("Finish this application latter"));
        return layout;
    }

    Div getPersonalDetail()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.addClassName("leftborder");
        Html name = new Html("<p><b>JOSE JOAQUIN UMPIERRE<b></p>");
        //name.addClassName("bold");
        layout.add(name);
        layout.add(new Paragraph("Date of birth: July 24, 1973"));
        layout.add(new Paragraph("Gender: Male"));
        Div out = new Div(layout);
        out.addClassName("leftborder");
        return out;
    }
}



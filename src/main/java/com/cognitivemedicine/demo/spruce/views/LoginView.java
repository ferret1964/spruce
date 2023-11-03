package com.cognitivemedicine.demo.spruce.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();
    private static final String OAUTH_URL_GITHUB = "/oauth2/authorization/github";
    public LoginView() {
        //addClassName("login-view");
        setSizeFull();

        StreamResource resource = new StreamResource("VALogo.svg", ()->getClass().getResourceAsStream("/VALogo.svg"));
        Image image = new Image(resource,"VA Logo");
        image.setWidth("400px");
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.START);

        login.setAction("login");
        Icon exitIcon = new Icon(VaadinIcon.CLOSE_CIRCLE_O);
        exitIcon.setSize("12px");
        HorizontalLayout h1 = new HorizontalLayout();
        h1.setJustifyContentMode(JustifyContentMode.END);
        h1.setPadding(true);
        h1.setWidthFull();
        h1.add(exitIcon);

        add(h1,image, new H1("Sign In"),getAccessButtons(), getCreateAccount(), getHavingTrouble());
    }

    public VerticalLayout getHavingTrouble()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.add(new H2("Having trouble signing in?"));
        Html help = new Html("<P>Get answers to common questions about <A href=\"signingin\">signing in</A> and <A href=\"verfiy\">verifying your identity</A>.</P>");
        layout.add(help);
        return layout;
    }
    public VerticalLayout getCreateAccount()
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.add(new H2("Or create an account"));
        layout.add(new Hr());
        HorizontalLayout h1 = new HorizontalLayout();
        h1.setAlignItems(Alignment.CENTER);
        //StreamResource resource = new StreamResource("circle-chevron-right-solid.svg", ()->getClass().getResourceAsStream("/circle-chevron-right-solid.svg"));
        //Image image = new Image(resource,"circle chevron ");
        //image.setHeight("18px");
        Icon icon1 = new Icon(VaadinIcon.CHEVRON_CIRCLE_RIGHT);
        icon1.setSize("18px");
        //Button createLoginDotGov = new Button(image);
        Anchor link1 = new Anchor("", new Html("<P><U><B>Create an</B> account with <B>Login.gov</B></U></P>"));
        h1.add(icon1,link1);
        layout.add(h1);
        layout.add(new Hr());
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setAlignItems(Alignment.CENTER);
        Anchor link2 = new Anchor("", new Html("<P><U><B>Create an</B> account with <B>ID.me</B></U></P>"));
        Icon icon2 = new Icon(VaadinIcon.CHEVRON_CIRCLE_RIGHT);
        icon2.setSize("18px");
        // Button createIDME = new  Button(image);
        h2.add(icon2,link2);
        layout.add(h2);
        layout.add(new Hr());
        return layout;
    }
    public VerticalLayout getAccessButtons()
    {
        setSizeFull();

        StreamResource resource_lg = new StreamResource("logindotgov_logo.svg", ()->getClass().getResourceAsStream("/logindotgov_logo.svg"));
        Image image_lg = new Image(resource_lg,"Login.gov");
        image_lg.setHeight("18px");

        StreamResource resource_idme = new StreamResource("Primary-IDme-Logo-RGB-white.svg", ()->getClass().getResourceAsStream("/Primary-IDme-Logo-RGB-white.svg"));
        Image image_idme = new Image(resource_idme,"Id me");
        image_idme.setHeight("18px");

        VerticalLayout layout = new VerticalLayout();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.START);
        layout.setSpacing(false);
        layout.setWidthFull();
        String buttonWidth = "350px";
        //Create Login Buttons
        //Button loginDotGov = new Button("LOGON.GOV");
        Button loginDotGov = new Button(image_lg);
        loginDotGov.setWidth(buttonWidth);
        loginDotGov.addClassNames("logindotgov");
        loginDotGov.setAriaLabel("Sign on with login.gov");
        loginDotGov.addClickListener(e-> {

                    UI.getCurrent().getPage().setLocation(OAUTH_URL_GITHUB);
            }
        );

        Button idMe = new Button(image_idme);
        idMe.addClassName("idme");
        idMe.setWidth(buttonWidth);
        idMe.setAriaLabel("Sign on with ID me");

        Button dsLogon = new Button("DS Logon");
        dsLogon.addClassName("dslogon");
        dsLogon.setWidth(buttonWidth);
        dsLogon.setAriaLabel("Sign on with DS Logon");
        Button myHealtheVet = new Button("My HealtheVet");
        myHealtheVet.addClassName("myhealthevet");
        myHealtheVet.setWidth(buttonWidth);
        myHealtheVet.setAriaLabel("Sign on with MyHealtheVet");
        layout.add(loginDotGov, idMe, dsLogon, myHealtheVet);
        return layout;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }


}

package App;

import java.util.ArrayList;

public class App {
    private AppContainer appContainer;
    private ArrayList<Controller> controllers;

    public App(AppContainer appContainer) {
        this.controllers = new ArrayList<>();
        this.appContainer = appContainer;
    }

    public void addController(Controller controller) {
        this.controllers.add(controller);
    }

    public void run() {
        appContainer.initialize();

        for (Controller controller : this.controllers) {
            controller.connect(this.appContainer);
        }
    }
}
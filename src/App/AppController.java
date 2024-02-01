package App;

import java.util.ArrayList;

public class AppController {
    private AppContainer appContainer;
    private ArrayList<Controller> controllers;

    public AppController(AppContainer application) {
        this.controllers = new ArrayList<>();
        this.appContainer = application;
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
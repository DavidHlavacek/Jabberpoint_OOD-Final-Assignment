package App;

import Presentation.Presentation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter implements Controller {

    private Presentation presentation;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                presentation.nextSlide();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                presentation.prevSlide();
                break;
            case 'q':
            case 'Q':
                System.exit(0);
                break; //Should not be reached
            default:
                break;
        }
    }
    @Override
    public void connect(AppContainer application) {
        this.presentation = application.getPresentationComponent().getPresentation();
        application.addKeyListener(this);
    }
}

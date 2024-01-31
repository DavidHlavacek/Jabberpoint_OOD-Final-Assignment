package Presentation;

import Application.Window;

import javax.swing.*;
import java.awt.*;

public class PresentationComponent extends JComponent {

    private Font labelFont;
    private Presentation presentation;
    private Style[] styles;
    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    public PresentationComponent() {
        this.setBackground(BGCOLOR);
        this.presentation = new Presentation();
        this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.styles = new Style[5];
        this.createStyles();
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    private void createStyles() {
        this.styles[0] = new Style(0, Color.red, 48, 20);
        this.styles[1] = new Style(20, Color.blue, 40, 10);
        this.styles[2] = new Style(50, Color.black, 36, 10);
        this.styles[3] = new Style(70, Color.black, 30, 10);
        this.styles[4] = new Style(90, Color.black, 24, 10);
    }

    private Style getStyle(int level) {
        if (level < 0 || level >= this.styles.length) {
            level = this.styles.length - 1;
        }
        return styles[level];
    }

    //Draw the slide
    public void paintComponent(Graphics g) {
        this.repaint();
        Slide slide = this.presentation.getCurrentSlide();
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);
        if (presentation.getSlideNumber() < 0 || slide == null) {
            return;
        }
        g.setFont(labelFont);
        g.setColor(COLOR);
        g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                presentation.getSize(), XPOS, YPOS);
        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        this.drawSlide(slide, g, area);
    }

    private void drawSlide(Slide slide, Graphics graphics, Rectangle area) {
        float scale = getScale(area);
        int y = area.y;

        //The title is treated separately
        SlideItem textSlideItem = new TextItem(0, slide.getTitle());
        Style style = this.getStyle(textSlideItem.getLevel());
        textSlideItem.draw(area.x, y, scale, graphics, style);
        y += textSlideItem.getBoundingBox(graphics, scale, style).height;

        for (SlideItem slideItem : slide.getSlideItems()) {
            if (slideItem instanceof BitmapItem) {
                ((BitmapItem) slideItem).setImageObserver(this);
            }
            style = this.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, graphics, style);
            y += slideItem.getBoundingBox(graphics, scale, style).height;
        }
    }

    //Returns the scale to draw a slide
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Window.WIDTH), ((float) area.height) / ((float) Window.HEIGHT));
    }
}

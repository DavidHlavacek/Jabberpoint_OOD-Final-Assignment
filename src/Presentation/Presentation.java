package Presentation;

import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
    private String title;
    //An ArrayList with slides
    private ArrayList<Slide> slides;

    //The number of the current slide
    private int currentSlideNumber;

    public Presentation() {
        this.slides = new ArrayList<>();
        this.currentSlideNumber = 0;
    }
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    //Returns the number of the current slide
    public int getSlideNumber() {
        return this.currentSlideNumber;
    }

    private boolean isSlideNumberOutOfBounds(int slideNumber) {
        return slideNumber < 0 || slideNumber >= this.slides.size();
    }

    // Change the current slide number and report it the window
    //Bug fixed by checking if out of bounds
    public void setSlideNumber(int number) {
        if (!this.isSlideNumberOutOfBounds(number)) {
            currentSlideNumber = number;
        }
    }
    // Return a slide with a specific number
    public Slide getSlide(int number) {
        if (this.isSlideNumberOutOfBounds(number)) {
            return null;
        }

        return this.slides.get(number);
    }

    // Navigate to the previous slide unless we are at the first slide
    public void prevSlide() {
        setSlideNumber(this.currentSlideNumber - 1);
    }

    // Navigate to the next slide unless we are at the last slide
    public void nextSlide() {
        setSlideNumber(this.currentSlideNumber + 1);
    }

    // Clears the presentation
    public void clear() {
        this.slides.clear();
    }

    public int getSize() {
        return this.slides.size();
    }


    // Add a slide to the presentation
    public void append(Slide slide) {
        this.slides.add(slide);
    }

    // Return the current slide
    public Slide getCurrentSlide() {
        return getSlide(this.currentSlideNumber);
    }

}
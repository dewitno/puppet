import ecs100.*;
/**
 * user interface to command the characters
 *
 * @author dewitno
 * @version 4/05/21
 */
public class PuppetMaster
{
    // fields to store values between events/method calls
    private static final int CANVASWIDTH = 1000;
    private static final int CANVASHEIGHT = 500;
    
    // create cxartoon characters
    private CartoonCharacter cc1 = new CartoonCharacter(200, 100, "casey");
    private CartoonCharacter cc2 = new CartoonCharacter(500, 100, "bob");
    private CartoonCharacter selectedCC = cc1;      // the selected one
    
    private double walkDist = 20;
    
    // methods 
    /** 
     * Check which object has been clicked on and select it
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("clicked")) {
            // check the location of the x and y against loc of obj
            if ((x >= this.cc1.getLocX()) && (x <= this.cc1.getLocX() + this.cc1.getWidth()) 
            && (y>= this.cc1.getLocY()) && (y <= this.cc1.getLocY() + this.cc1.getHeight())) {
                this.selectedCC = this.cc1;
            } else if ((x >= this.cc2.getLocX()) && (x <= this.cc2.getLocX() + this.cc2.getWidth()) 
            && (y>= this.cc2.getLocY()) && (y <= this.cc2.getLocY() + this.cc2.getHeight())) {
                this.selectedCC = this.cc2;
            }
        }
    }

    /**
     * switch to bob
     */
    public void doBob() {
        this.selectedCC = cc2;
    }
    
    /**
     * switch to casey
     */
    public void doCasey() {
        this.selectedCC = cc1;
    }
    
    /**
     * Tell the cartooncharacter to smile
     */
    public void doSmile() {
        this.selectedCC.smile();
    }
    
    /**
     * Tell the cartooncharacter to frown
     */
    public void doFrown() {
        this.selectedCC.frown();
    }
    
    /**
     * Tell the cartooncharacter turn left
     */
    public void doLeft() {
        this.selectedCC.lookLeft();
    }
    
    /**
     * Tell the cartooncharacter turn right
     */
    public void doRight() {
        this.selectedCC.lookRight();
    }
    
    /**
     * Tell the cartooncharacter to speak
     */
    public void doSpeak(String words) {
        this.selectedCC.speak(words);
    }
    
    /**
     * Tell the cartooncharacter to think
     */
    public void doThink(String words) {
        this.selectedCC.think(words);
    }
    
    /**
     * set distance to walk
     */
    public void setDist(double dist) {
        this.walkDist = dist;
    }
    
    /**
     * tell cartooncharacter to walk
     */
    public void doWalk() {
        this.selectedCC.walk(this.walkDist);
    }
    
    /**
     * clear graphics pane
     */
    private void clear() {
        UI.clearPanes();        // clear the graphics pane
        UI.setDivider(0.0);     // hide the text pane
    }
    
    // main setup GUI
    public static void main(String[] args) {
        // make a PuppetMaster object
        PuppetMaster pm = new PuppetMaster();
        
        final int DISTMIN = 1;
        final int DISTMAX = 100;
        final int DISTINIT = 20;
        
        // setup the buttons, slider, textField, to call methods on the object
        UI.initialise();
        UI.setWindowSize(CANVASWIDTH, CANVASHEIGHT);
        
        // button to switch selected character
        UI.addButton("Bob", pm::doBob);
        UI.addButton("Casey", pm::doCasey);
        
        UI.setMouseListener(pm::doMouse);
        UI.addButton("Smile", pm::doSmile);
        UI.addButton("Frown", pm::doFrown);
        UI.addButton("Left", pm::doLeft);
        UI.addButton("Right", pm::doRight);
        UI.addTextField("Say", pm::doSpeak);
        UI.addTextField("Think", pm::doThink);
        UI.addButton("Walk", pm::doWalk);
        UI.addSlider("Distance", DISTMIN, DISTMAX, DISTINIT, pm::setDist);
        
        // add standard buttons
        UI.addButton("Clear", pm::clear);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);     // must come after setting up button etc.
        
    }
   
}

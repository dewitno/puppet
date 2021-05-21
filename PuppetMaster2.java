import ecs100.*;
/**
 * user interface to command the characters
 *
 * @author dewitno
 * @version 4/05/21
 */
public class PuppetMaster2
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
     * set distance to walk
     */
    public void setDist(double dist) {
        this.walkDist = dist;
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
        PuppetMaster2 pm = new PuppetMaster2();
        
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
        UI.addButton("Smile", ()->{pm.selectedCC.smile();});
        UI.addButton("Frown", ()->{pm.selectedCC.frown();});
        UI.addButton("Left", ()->{pm.selectedCC.lookLeft();});
        UI.addButton("Right", ()->{pm.selectedCC.lookRight();});
        UI.addTextField("Say", (String words)->{pm.selectedCC.speak(words);});
        UI.addTextField("Think", (String words)->{pm.selectedCC.think(words);});
        UI.addButton("Walk", ()->{pm.selectedCC.walk(pm.walkDist);});
        UI.addSlider("Distance", DISTMIN, DISTMAX, DISTINIT, 
                                    (double val)->{pm.walkDist = val;});
        
        // add standard buttons
        UI.addButton("Clear", pm::clear);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);     // must come after setting up button etc.
        
    }
   
}

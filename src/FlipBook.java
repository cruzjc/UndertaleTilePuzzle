
public class FlipBook {

	private int numFrames;		// Keeps track of how many frames are in the animation sequence
	private long duration;		// duration to play the animation over (in milliseconds)
	private long starttime;		// keep track of starting time of animation

	private boolean loopIt;		// determine whether animation should loop or not
	private boolean starting;	// Flag to indicate that you are starting animation from frame zero
	private boolean stopped;	// Flag to indicate if the animation has stopped.
	private boolean visibility;	// Flag to determine if the images should be visible or not
	
	private EZImage[] frames;	// Hold all the animation frames
	private EZGroup group;		// Holds the EZGroup
	
	// Constructor accepts the following parameters
	// filename - contains an array of filenames of images to read.
	// dur - duration over which animation frames should play.
	// posX, posY - location of the animated object.
	
	
	FlipBook(String[] filenames, long dur, int posX, int posY) {
		duration = dur;
		
		// Make an EZgroup to gather up all the individual EZimages.
		group = EZ.addGroup();
		numFrames = filenames.length;
		
		// Make an array to hold the EZImages
		frames = new EZImage[numFrames];
		
		// Load each image
		for(int i = 0; i < numFrames; i++){
			frames[i] = EZ.addImage(filenames[i], 0, 0);
			frames[i].hide();
			group.addElement(frames[i]);
		}
		group.translateTo(posX, posY);
		
		setLoop(true);
		starting = true;
		stopped = false;
		visibility = true;
	}
	void translateTo(int posX, int posY) {
			group.translateTo(posX,posY);
	}
	
	//custom code================
	void translateBy(int x,int y){
		group.translateBy(x,y);
	}
	int posx(){
		return group.getXCenter();
	}
	int posy(){
		return group.getYCenter();
	}
	void remove(){
		EZ.removeEZElement(group);
	}
	void pullToFront(){
		group.pullToFront();
	}
	
	void scaleBy(double scale){
		group.scaleBy(scale);
	}
	
	int getWidth(int type){
		if(type==0)
		return group.getWidth();
		if(type==1)
		return group.getWorldHeight();
		return EZ.getWindowWidth();
	}
	int getHeight(int type){
		if(type==0)
		return group.getHeight();
		if(type==1)
		return group.getWorldHeight();
		return EZ.getWindowHeight();
	}
	
	int getWidth(){
		return group.getWidth();
	}
	int getHeight(){
		return group.getHeight();
	}
	
	void rotateBy(int degrees){
		group.rotateBy(degrees);
	}
	
	double getRotation(){
		return group.getRotation();
	}
	
	EZGroup ezGroup(){
		return group;
	}
	//=============================
	
	void rotateTo(double angle){
		group.rotateTo(angle);
	}
	
	void scaleTo(double scale){
		group.scaleTo(scale);
	}
	void setLoop(boolean loop){
		loopIt = loop;
	}
	void restart(){
		starting = true;
	}
	
	void stop(){
		stopped = true;
	}
	
	// Hide each animation frame
	void hide(){
		visibility = false;
		for(int i =0; i < numFrames; i++) frames[i].hide();
	}
	
	void show() {
		visibility = true;
	}
	boolean go(){
		if (stopped) return false;
		
		// If the animation is starting for the first time save the starttime
		if (starting){
			starttime = System.currentTimeMillis();
			starting = false;
		}

		// If the duration for the animation is exceeded and if looping is enabled
		// then restart the animation from the beginning.
		if ((System.currentTimeMillis() - starttime) > duration) {
			if (loopIt) {
				restart();
				return true;
			}
			
			// Otherwise there is no more animation to play so stop.
			return false;
		}
		
		// Based on the current frame and elapsed time figure out what frame to show.
		float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) duration;

		int currentFrame = (int) (((float) numFrames) *  normTime);
		if (currentFrame > numFrames-1) currentFrame = numFrames-1;
		
		// Hide all the frames first
		for (int i=0; i < numFrames; i++) {
			if (i != currentFrame) frames[i].hide();
		}
		
		// Then show all the frame you want to see.
		if (visibility)
			frames[currentFrame].show();
		else 
			frames[currentFrame].hide();
		return true;

	}

}

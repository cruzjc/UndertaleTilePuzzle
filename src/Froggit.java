
public class Froggit extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_froggit_2.png";//froggit_1 sprite is not similarly sized
	//create string called file1 initialized as amalgam
	static String file2="./Sprites/spr_froggit_2.png";
	//create string called file2 initialized as amalgam
	static String file3="./Sprites/spr_froggit_3.png";
	
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2,file3,file2,file1,file3,file3,file1,
			file3,file2,file2};
	
	
	//constructor
	
	static int animationDuration=1000*5;
	public Froggit(int posx,int posy,double size) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size,animationDuration);
		// TODO Auto-generated constructor stub
		
	}
//method to initialized 

	void updateFrame(){
		//moveTowardsPosition(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(), speed);
		//imageSwitching();
		fbMonster.go();
	}
	//method to create bulletpattern
	void bulletPattern(){
		
	}



}


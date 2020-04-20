import controller.GameControl;

public class CarDriveApplication {
	public static void main(String args[]) {
		GameControl game = null;
		if(args.length>=1) {
			 game= new GameControl(args);
		}else {
			System.exit(0);
		}
		if(args[1].equals("t")) {
			game.tsetlinStart(args);
		}else {
			game.qLearningStart(args);
		}
		//
		
	}
}

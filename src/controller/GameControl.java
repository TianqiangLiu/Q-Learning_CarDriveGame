package controller;

import java.util.ArrayList;

import javax.xml.ws.handler.MessageContext.Scope;

import Utilities.Utilities;
import laModel.QLearning;
import laModel.Tsetlin;

public class GameControl {
	ArrayList<String> board;

	Tsetlin tsetlin;
	QLearning qLearning;
	final int experienmentTime = 10000000;
	final int groupNum = 1000;

	public GameControl(String[] args) {
		Utilities.BOARD_WIDE_INTEGER = Integer.parseInt(args[0]);
		board = Utilities.initialBoard();
		
		qLearning = new QLearning();
		tsetlin = new Tsetlin();
		
	}

	public void tsetlinStart(String[] args) {
		Integer carLocation = 0;
		for (int experientTimes = 0; experientTimes < experienmentTime; experientTimes++) {
			ArrayList<Integer> path = new ArrayList<Integer>();
			while (carLocation < Utilities.BOARD_SIZE - 1) {
				int oldPosition = carLocation;
				path.add(oldPosition);
				int direction = tsetlin.goWhere(carLocation);
				int reward;
				boolean bound = false;

				if (direction == 0 && (carLocation + Utilities.BOARD_WIDE_INTEGER < Utilities.BOARD_SIZE)) {
					carLocation = carLocation + Utilities.BOARD_WIDE_INTEGER;

				} else if (direction == 1) {
					carLocation = carLocation + 1;
				} else {
					bound = true;
				}

				if ((board.get(carLocation).equals("R") || board.get(carLocation).equals("G")) && !bound) {
					reward = 0;
				} else {
					reward = 1;
				}
				if (bound || reward == 0)
					tsetlin.rewardSystem(reward, oldPosition);
				else if (reward == 1) {
					tsetlin.rewardSystem(reward, path);
				}

			}
			carLocation = 0;
		}
		double finalScore = 0;
		for (int groupNumber = 0; groupNumber < groupNum; groupNumber++) {
			double score = 100;
			while (carLocation < Utilities.BOARD_SIZE - 1) {
				int oldPosition = carLocation;
				int direction = tsetlin.goWhere(carLocation);
				int reward;
				boolean bound = false;

				if (direction == 0 && (carLocation + Utilities.BOARD_WIDE_INTEGER < Utilities.BOARD_SIZE)) {
					carLocation = carLocation + Utilities.BOARD_WIDE_INTEGER;

				} else if (direction == 1) {
					carLocation = carLocation + 1;
				} else {
					bound = true;
				}

				if ((board.get(carLocation).equals("R") || board.get(carLocation).equals("G")) && !bound) {
					reward = 0;
				} else {
					reward = 1;
				}
				tsetlin.rewardSystem(reward, oldPosition);

				if (bound || reward == 0) {

				}

				else if (reward == 1) {
					score = score - (score / (Utilities.BOARD_WIDE_INTEGER - 1));
				}

			}
			System.out.println("Score is " + score);
			finalScore  = finalScore+score;
		}
		System.out.println("Average score is :" + finalScore/groupNum);

	}

	public void qLearningStart(String[] args) {
		Utilities.printBoard(board, 0);
		Utilities.printUtilities();
		double finalScore = 0;
		for (int group = 0; group < groupNum; group++) {
			
		
		qLearning.initial(board, Utilities.BOARD_WIDE_INTEGER, Utilities.BOARD_SIZE);
		qLearning.qLearning();
//		qLearning.printQ();
//		qLearning.printPolicy();
		ArrayList<Integer> result = qLearning.getResult();
		double score = 100;
		for (int i = 0; i < result.size(); i++) {
			if(board.get(result.get(i)).equals("T")) {
				score = score - (score / (Utilities.BOARD_WIDE_INTEGER - 1));
			}
		}
		finalScore = finalScore + score;
		}
		System.out.println("\nAverage score is :" + finalScore/groupNum);
		System.out.println(qLearning.getResult());
	}

}

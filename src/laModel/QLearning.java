package laModel;

import java.util.ArrayList;
import java.util.Random;

import Utilities.Utilities;

public class QLearning {
	private final double alpha = 0.1;
	private final double gamma = 0.9;
	private final int reward = 100;
	private final int penalty = -10;
	private int[][] rewardsTable;
	
	//Q-table
	private double[][] qLearningTable;
	
	private ArrayList<String> board;

	public void initial(ArrayList<String> board, int wide, int boardSize) {
		rewardsTable = new int[boardSize][boardSize];
		
		//Initial Q-table
		qLearningTable = new double[boardSize][boardSize];
		
		this.board = board;
		for (int i = 0; i < rewardsTable.length; i++) {
			for (int j = 0; j < rewardsTable[0].length; j++) {
				rewardsTable[i][j] = -1;
				int y = (int) (i / wide);
				int x = i % wide;

				int left = x - 1;
				if (left >= 0) {
					int target = y * wide + left;
					if (board.get(target) == "R") {
						rewardsTable[i][target] = 0;
					} else if (board.get(target) == "G") {
						rewardsTable[i][target] = reward;
					} else {
						rewardsTable[i][target] = penalty;
					}
				}

				int right = x + 1;
				if (right < wide) {
					int target = y * wide + right;
					if (board.get(target) == "R") {
						rewardsTable[i][target] = 0;
					} else if (board.get(target) == "G") {
						rewardsTable[i][target] = reward;
					} else {
						rewardsTable[i][target] = penalty;
					}
				}
				
				int up = y - 1;
				if (up >= 0) {
					int target = up * wide + x;
					if (board.get(target) == "R") {
						rewardsTable[i][target] = 0;
					} else if (board.get(target) == "G") {
						rewardsTable[i][target] = reward;
					} else {
						rewardsTable[i][target] = penalty;
					}
				}
				
				int down = y + 1;
				if (down<wide) {
					int target = down * wide + x;
					if (board.get(target) == "R") {
						rewardsTable[i][target] = 0;
					} else if (board.get(target) == "G") {
						rewardsTable[i][target] = reward;
					} else {
						rewardsTable[i][target] = penalty;
					}
				}
				
				qLearningTable[i][j]=rewardsTable[i][j];
			}
		}
		//printR(rewardsTable,boardSize);

	}
	void printR(int[][] matrix,int statesCount) {
        System.out.printf("%25s", "States: ");
        for (int i = 0; i <= statesCount; i++) {
            System.out.printf("%4s", i);
        }
        System.out.println();

        for (int i = 0; i < statesCount; i++) {
            System.out.print("Possible states from " + i + " :[");
            for (int j = 0; j < statesCount; j++) {
                System.out.printf("%4s", matrix[i][j]);
            }
            System.out.println("]");
        }
    }
	
    public void qLearning() {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            
            int position = 0;

            while (!isFinished(position)) {
                int[] actions = possibleActionsFromState(position);

                
                int index = random.nextInt(actions.length);
                int nextState = actions[index];

                // Q(state,action)= Q(state,action) + alpha * (R(state,action) + gamma * Max(next state, all actions) - Q(state,action))
                double q = qLearningTable[position][nextState];
                double maxQ_Value = maxQ(nextState);
                int reward = rewardsTable[position][nextState];

                double value = q + alpha * (reward + gamma * maxQ_Value - q);
                qLearningTable[position][nextState] = value;

                position = nextState;
            }
        }
    }
    public ArrayList<Integer> getResult(){
    	int position = 0;
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	arr.add(position);
    	while(position<Utilities.BOARD_SIZE-1) {
    		position = getPolicyFromState(position);
    		arr.add(position);
    	}
    	return arr;
    }
    double maxQ(int nextState) {
        int[] actionsFromState = possibleActionsFromState(nextState);
        //the learning rate and eagerness will keep the W value above the lowest reward
        double maxValue = -10;
        for (int nextAction : actionsFromState) {
            double value = qLearningTable[nextState][nextAction];

            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }
    boolean isFinished( int position) {
        
        return board.get(position).equals("G");
    }
    
    int[] possibleActionsFromState(int state) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < Utilities.BOARD_SIZE; i++) {
            if (rewardsTable[state][i] != -1) {
                result.add(i);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
    
    public void printPolicy() {
        System.out.println("\nPrint policy");
        for (int i = 0; i < Utilities.BOARD_SIZE; i++) {
            System.out.println("From state " + i + " goto state " + getPolicyFromState(i));
        }
    }

    int getPolicyFromState(int state) {
        int[] actionsFromState = possibleActionsFromState(state);

        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state;

        // Pick to move to the state that has the maximum Q value
        for (int nextState : actionsFromState) {
            double value = qLearningTable[state][nextState];

            if (value > maxValue) {
                maxValue = value;
                policyGotoState = nextState;
            }
        }
        return policyGotoState;
    }

    public void printQ() {
        System.out.println("Q matrix");
        for (int i = 0; i < qLearningTable.length; i++) {
            System.out.print("From state " + i + ":  ");
            for (int j = 0; j < qLearningTable[i].length; j++) {
                System.out.printf("%6.2f ", (qLearningTable[i][j]));
            }
            System.out.println();
        }
    }

}

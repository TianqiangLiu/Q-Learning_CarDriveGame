package laModel;

import java.util.ArrayList;

import Utilities.Utilities;

public class Tsetlin {
	
	private ArrayList<Integer> states;
	public ArrayList<Integer> getStates() {
		return states;
	}
	public void setStates(ArrayList<Integer> states) {
		this.states = states;
	}

	private Integer nInteger;
	public Tsetlin() {
		states = new ArrayList<Integer>();
		for (int i = 0; i < Utilities.BOARD_SIZE; i++) {
			states.add(1);
		}
		nInteger=10;
	}
	public Integer goWhere(int position) {
		
		if(states.get(position)<=nInteger) {
			return 0;
		}else if(states.get(position)<=2*nInteger) {
			return 1;
		}else {
			System.out.println(states);
		}
		return -1;

	}
	public void rewardSystem(int reward, ArrayList<Integer> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			int position = arrayList.get(i);
			int state = states.get(position);
			if(reward == 0) {
				if(state!=1&&state!=nInteger+1) {
					
					state=state-1;
				}
			}else {
				if(state!=nInteger&&state!=(2*nInteger)
						) {
						
						state=state+1;
					}
				else if(state==nInteger) {
					state = 2*nInteger;
				}
				else if(state==2*nInteger) {
					state = nInteger;
				}
			}
//			System.out.println("position:"+position+"\nstate:"+state);
			states.set(position, state);
		}
		
	}
	public void rewardSystem(int reward, Integer position) {

			int state = states.get(position);
			if(reward == 0) {
				if(state!=1&&state!=nInteger+1) {
					
					state=state-1;
				}
			}else {
				if(state!=nInteger&&state!=(2*nInteger)
						) {
						
						state=state+1;
					}
				else if(state==nInteger) {
					state = 2*nInteger;
				}
				else if(state==2*nInteger) {
					state = nInteger;
				}
			}
//			System.out.println("position:"+position+"\nstate:"+state);
			states.set(position, state);
		
		
	}
	
	public void reNew() {
		states = new ArrayList<Integer>();
		for (int i = 0; i < Utilities.BOARD_SIZE; i++) {
			states.add(1);
		}
		nInteger=10;
		
	}

}

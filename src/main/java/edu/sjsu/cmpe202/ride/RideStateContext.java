package edu.sjsu.cmpe202.ride;

/**
 * @author rwatsh on 8/15/16.
 */
public class RideStateContext {

    private RideState state;

    public void setState(RideState s) {
        this.state = s;
    }

    public void handleInput() {
        state.handleInput(this);
    }
}

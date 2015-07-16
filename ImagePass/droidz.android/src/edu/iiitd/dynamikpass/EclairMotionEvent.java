/* ** determines the motion of the images used as background image ** */
package edu.iiitd.dynamikpass;

import android.view.MotionEvent;


public class EclairMotionEvent extends WrapMotionEvent {

    protected EclairMotionEvent(MotionEvent event) {
            super(event);
    }

    public float getX(int pointerIndex) {
            return event.getX(pointerIndex);
    }

    public float getY(int pointerIndex) {
            return event.getY(pointerIndex);
    }

    public int getPointerCount() {
            return event.getPointerCount();
    }

    public int getPointerId(int pointerIndex) {
            return event.getPointerId(pointerIndex);
    }
}

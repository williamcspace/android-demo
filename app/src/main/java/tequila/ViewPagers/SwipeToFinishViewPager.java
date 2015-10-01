package tequila.ViewPagers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 8/19/15.
 */
public class SwipeToFinishViewPager extends BaseViewPager {
    public SwipeToFinishViewPager(Context context) {
        super(context);
    }

    public SwipeToFinishViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("Viewpager","X: " + event.getX());
        if (inNeutralArea(event.getX(), event.getY())) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (inNeutralArea(event.getX(), event.getY())) {
            return false;
        }
        return super.onInterceptTouchEvent(event);

    }

    private boolean inNeutralArea(float x, float y) {
        if (x > 100) {
            return false;
        }
        return true;
    }
}
package tequila.PageTransformers;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by williamc1986 on 8/18/15.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
            // 页面在最左边 [-Infinity,-1)
            view.setAlpha(0);
        } else if (position <= 0) {
            // [-1,0]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else if (position <= 1) { // (0,1]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else {
            // 页面在最右边 (1,+Infinity]
            view.setAlpha(0);
        }
    }
}
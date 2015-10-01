package tequila.events;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class ToggleLoading {
    private boolean isShown = false;

    public ToggleLoading(boolean isShown) {
        this.isShown = isShown;
    }

    public boolean isShown() {
        return isShown;
    }
}

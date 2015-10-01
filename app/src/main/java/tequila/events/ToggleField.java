package tequila.events;

import com.tangxinli.android.tequila.dao.models.Field;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class ToggleField {
    private Field mField;

    public ToggleField(Field field) {
        mField = field;
    }

    public Field getField() {
        return mField;
    }
}

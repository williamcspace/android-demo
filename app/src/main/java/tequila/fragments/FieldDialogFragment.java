package tequila.fragments;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.adapters.FieldListAdapter;
import com.tangxinli.android.tequila.dao.models.Field;
import com.tangxinli.android.tequila.dao.models.FieldDao;
import com.tangxinli.android.tequila.providers.DaoProvider;

import java.util.List;

/**
 * Created by williamc1986 on 8/3/15.
 */
public class FieldDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Field> mFields;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fields, null);
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Double dWidth = size.x * 0.8;
        Double dHeight = size.y * 0.8;

        dialog.getWindow().setLayout(dWidth.intValue(), dHeight.intValue());
        dialog.getWindow().setGravity(Gravity.CENTER);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fieldList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFields = loadFields();
        mAdapter = new FieldListAdapter(this.getActivity(), mFields);
        mRecyclerView.setAdapter(mAdapter);

        return dialog;
    }

    private List<Field> loadFields() {
        return DaoProvider.getSession()
                .getFieldDao()
                .queryBuilder()
                .orderAsc(FieldDao.Properties.Id)
                .list();
    }
}

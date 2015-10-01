package tequila.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.tangxinli.android.tequila.R;

/**
 * Created by williamc1986 on 8/3/15.
 */
public class PaymentDialogFragment extends DialogFragment {
    public static PaymentDialogFragment newInstance(int title) {
        PaymentDialogFragment frag = new PaymentDialogFragment();
        return frag;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(inflater.inflate(R.layout.dialog_payment, null))
                .create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}

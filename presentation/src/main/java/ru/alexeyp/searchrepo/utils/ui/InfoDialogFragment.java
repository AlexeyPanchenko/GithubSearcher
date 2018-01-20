package ru.alexeyp.searchrepo.utils.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.alexeyp.searchrepo.R;

public class InfoDialogFragment extends DialogFragment {

    private static final String TAG = InfoDialogFragment.class.getSimpleName();
    private static final String ARG_MESSAGE = "message";

    @BindView(R.id.btn_close)
    Button btnCancel;

    @BindView(R.id.tv_message)
    TextView tvMessage;

    private CloseListener _closeListener;

    public interface CloseListener {
        void onDialogClosed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _closeListener = (CloseListener) context;
    }

    public static void showDialog(FragmentManager fm, String message) {
        if (fm.findFragmentByTag(TAG) == null) {
            InfoDialogFragment fragment = new InfoDialogFragment();
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, message);
            fragment.setArguments(args);
            fragment.show(fm, TAG);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View container = getActivity().getLayoutInflater().inflate(R.layout.info_dialog, null);
        ButterKnife.bind(this, container);
        return fillDialog(container);
    }

    private AlertDialog fillDialog(View container) {
         String message = getArguments().getString(ARG_MESSAGE, "");
         tvMessage.setText(message);
         AlertDialog dialog = new AlertDialog.Builder(getActivity())
                 .setView(container)
                 .create();
         if(dialog.getWindow()!=null){
             dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
         }
         return dialog;
    }

    @OnClick(R.id.btn_close)
    public void onCancelClick(){
        if(getDialog() != null){
            getDialog().dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        _closeListener.onDialogClosed();
    }
}

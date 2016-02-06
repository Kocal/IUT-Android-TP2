package fr.kocal.android.iut_tp2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by kocal on 04/02/16.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private final EditText inputUserBirthday;

    public DatePickerFragment(EditText inputUserBirthday) {
        super();

        this.inputUserBirthday = inputUserBirthday;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (inputUserBirthday != null) {
            String str = year + "/"
                    + String.format("%02d", monthOfYear) + "/"
                    + String.format("%02d", dayOfMonth);

            this.inputUserBirthday.setText(str);
        }
    }
}

package fr.kocal.android.iut_tp2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CODE_AVATAR = 1;

    public LayoutInflater layoutInflater;
    public View layoutToast;

    public ImageView inputUserAvatar;
    public Uri userAvatarUri;
    public EditText inputUserFirstname;
    public EditText inputUserLastname;
    public EditText inputUserBirthday;
    public RadioGroup inputUserGenderGroup;
    public RadioButton inputUserGender;
    public EditText inputUserEmail;
    public EditText inputUserZipCode;
    public EditText inputUserAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.layoutInflater = getLayoutInflater();
        this.layoutToast = this.layoutInflater.inflate(R.layout.toast,
                (LinearLayout) findViewById(R.id.layout_toast_root));

        this.inputUserAvatar = (ImageView) findViewById(R.id.input_avatar);
        this.inputUserFirstname = (EditText) findViewById(R.id.input_firstname);
        this.inputUserLastname = (EditText) findViewById(R.id.input_lastname);
        this.inputUserBirthday = (EditText) findViewById(R.id.input_birthday);
        this.inputUserGenderGroup = (RadioGroup) findViewById(R.id.input_gender);
        this.inputUserEmail = (EditText) findViewById(R.id.input_email);
        this.inputUserZipCode = (EditText) findViewById(R.id.input_zipcode);
        this.inputUserAddress = (EditText) findViewById(R.id.input_address);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            Toast toast;
            ImageView viewUserAvatar;
            TextView viewUserInfos;
            String userInfos;

            @Override
            public void onClick(View view) {

                if (this.toast == null) {
                    this.toast = new Toast(getApplicationContext());
                    this.toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    this.toast.setDuration(Toast.LENGTH_LONG);
                    this.toast.setView(layoutToast);

                    this.viewUserAvatar = (ImageView) layoutToast.findViewById(R.id.user_avatar);
                    this.viewUserInfos = (TextView) layoutToast.findViewById(R.id.user_infos);
                }

                inputUserGender = (RadioButton) findViewById(inputUserGenderGroup.getCheckedRadioButtonId());

                if (userAvatarUri != null && userAvatarUri.toString() != "") {
                    this.viewUserAvatar.setImageURI(userAvatarUri);
                }

                this.userInfos = "Prénom : " + inputUserFirstname.getText().toString().trim() + "\n" +
                        "Nom : " + inputUserLastname.getText().toString().trim() + "\n" +
                        "Anniversaire : " + inputUserBirthday.getText().toString().trim() + "\n" +
                        "Sexe : " + (inputUserGender == null ? "Non renseigné" : inputUserGender.getText()) + "\n" +
                        "Email : " + inputUserEmail.getText().toString().trim() + "\n" +
                        "Code postal : " + inputUserZipCode.getText().toString().trim() + "\n" +
                        "Addresse : " + inputUserAddress.getText().toString().trim();


                this.viewUserInfos.setText(userInfos);
                this.toast.show();
            }
        });
    }

    public void showAvatarChooserView(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CODE_AVATAR);
    }

    public void showBirthdayChooserFragment(View v) {
        DialogFragment newFragment = new DatePickerFragment(this.inputUserBirthday);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_AVATAR && resultCode == Activity.RESULT_OK) {
            userAvatarUri = data.getData();
            this.inputUserAvatar.setImageURI(userAvatarUri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

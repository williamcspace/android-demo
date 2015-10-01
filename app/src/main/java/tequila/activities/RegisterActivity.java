package tequila.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.tangxinli.android.tequila.R;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    protected EditText mEditFirstName;
    protected EditText mEditLastName;
    protected EditText mEditEmailAddress;
    protected EditText mEditPassword;
    protected EditText mEditPasswordConfirm;
    protected Button mRegisterAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mEditFirstName = (EditText) findViewById(R.id.etFirstName);
        mEditLastName = (EditText) findViewById(R.id.etLastName);
        mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
        mEditPassword = (EditText) findViewById(R.id.etPassword);
        mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
    }

    public void registerAccount(View view) {
        if (validateFields()) {
            if (validatePasswordMatch()) {
                processSignup(view);
            } else {
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "没填写完", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: 用text listeners 来处理
    private boolean validateFields() {
        if (mEditFirstName.getText().length() > 0 && mEditLastName.getText().length() > 0
                && mEditEmailAddress.length() > 0 && mEditPassword.getText().length() > 0
                && mEditPasswordConfirm.getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validatePasswordMatch() {
        if (mEditPassword.getText().toString().equals(mEditPasswordConfirm.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public void processSignup(View view) {
        Toast.makeText(this, "创建中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}

package com.example.nchhina.canadiankeybd;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends Activity {
    final static String TAG = "CustKbd";
    CustomKeyboard mAlphaKbd;
    CustomKeyboard mNumbericKbd;

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            Log.d(TAG,s.toString()+" length: "+s.length());
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG,s.toString()+" length: "+s.length());

        }

        public void afterTextChanged(Editable s) {
            Log.d(TAG,s.toString()+" length: "+s.length()+ " selection end: "+editTextView.getSelectionEnd());
            if (s.length() == 6) {
                mNumbericKbd.hideCustomKeyboard();
                mAlphaKbd.registerEditText(R.id.editText);
                editTextView.setSelection(editTextView.length());
            }
            else if (s.length() % 2 == 0) {
                mNumbericKbd.hideCustomKeyboard();
                mAlphaKbd.registerEditText(R.id.editText);
                mAlphaKbd.showCustomKeyboard(editTextView);

            } else {
                mAlphaKbd.hideCustomKeyboard();
                mNumbericKbd.registerEditText(R.id.editText);
                mNumbericKbd.showCustomKeyboard(editTextView);
            }
        }
    };

    EditText editTextView ;
    private int editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = R.id.editText;

        mAlphaKbd = new CustomKeyboard(this, R.id.alphakbdview, R.xml.aplha_kbd);

        mNumbericKbd = new CustomKeyboard(this, R.id.numerickbdview, R.xml.numeric_kbd);

        editTextView = (EditText) findViewById(R.id.editText);
        editTextView.addTextChangedListener(passwordWatcher);

        mAlphaKbd.registerEditText(editTextId);

//        editTextView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.d(TAG, "KeyEvent:"+event.getKeyCode());
//                if (keyCode == KeyEvent.KEYCODE_DEL) {
//                    //this is for backspace
//                }
//                return false;
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        if (mNumbericKbd.isCustomKeyboardVisible()) mNumbericKbd.hideCustomKeyboard();
        else if (mAlphaKbd.isCustomKeyboardVisible()) mAlphaKbd.hideCustomKeyboard();
        else this.finish();
    }

}

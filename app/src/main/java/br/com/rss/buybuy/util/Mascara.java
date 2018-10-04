package br.com.rss.buybuy.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class Mascara {

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

    public static View.OnFocusChangeListener onBlurValidaMascara(final String mask, final EditText ediTxt) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (ediTxt.length() != mask.length()) {
                        ediTxt.getText().clear();
                    }
                }
            }
        };
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            String oldString;
            String newString;
            boolean isUpdating;
            String old = "";
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                newString = s.toString();
                String str = Mascara.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (newString.length() > oldString.length()) {

                    int i = 0;
                    for (char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > 0) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldString = s.toString();
            }
            public void afterTextChanged(Editable s) {}
        };
    }

}
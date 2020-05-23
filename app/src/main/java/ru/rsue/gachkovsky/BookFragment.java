package ru.rsue.gachkovsky;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.zip.DataFormatException;

public class BookFragment extends Fragment {

    private Book mBook;
    private EditText mTitleField;

    private Button mDataButton;
    private CheckBox mReadedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBook = new Book();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_book,container,false);

        mTitleField = (EditText)v.findViewById(R.id.editText);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mBook.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });

        mDataButton = (Button)v.findViewById(R.id.book_date);
        mDataButton.setText(DateFormat.getDateInstance(DateFormat.FULL).format(mBook.getDate()));
        mDataButton.setEnabled(false);

        mReadedCheckBox = (CheckBox)v.findViewById(R.id.book_readed);
        mReadedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBook.setReaded(isChecked);
            }
        });

        return v;
    }
}

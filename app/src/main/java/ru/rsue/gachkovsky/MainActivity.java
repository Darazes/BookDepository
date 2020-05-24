package ru.rsue.gachkovsky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity
{

    private static final String EXTRA_BOOK_ID = "ru.rsue.gachkovsky.bookdepository.book_id";

    @Override
    protected Fragment createFragment()
    {
        //return new BookFragment();
        UUID bookId = (UUID) getIntent().getSerializableExtra(EXTRA_BOOK_ID);
        return BookFragment.newInstance(bookId);
    }

    public static Intent newIntent (Context packageContext, UUID bookID)
    {
        Intent intent = new Intent(packageContext,MainActivity.class);
        intent.putExtra(EXTRA_BOOK_ID,bookID);
        return intent;
    }

}

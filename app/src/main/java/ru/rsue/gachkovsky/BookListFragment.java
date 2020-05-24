package ru.rsue.gachkovsky;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BookListFragment extends Fragment {

    private RecyclerView mBookRecyclerView;

    private BookAdapter mAdapter;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_book_list,container,false);
        mBookRecyclerView = (RecyclerView) view.findViewById(R.id.book_recycler_view);
        mBookRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //public TextView mTitleTextView;



        private Book mBook;

        public void bindBook(Book book)
        {
            mBook = book;
            mTitleTextView.setText(mBook.getTitle());
            mDateTextView.setText(mBook.getDate().toString());
            mReadedCheckBox.setChecked(mBook.isReaded());
        }

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mReadedCheckBox;

        public BookHolder(View itemview)
        {
            super(itemview);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemview;
            mTitleTextView = (TextView) itemview.findViewById(R.id.list_item_book_title_text_view);
            mDateTextView = (TextView) itemview.findViewById(R.id.list_item_book_date_text_view);
            mReadedCheckBox= (CheckBox) itemview.findViewById(R.id.list_item_book_readed_check_box);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(),mBook.getTitle() + " нажата!",Toast.LENGTH_SHORT).show();

            //Intent intent = new Intent(getActivity(),MainActivity.class);
            Intent intent = MainActivity.newIntent(getActivity(),mBook.getId());
            startActivity(intent);

        }
    }

    private class BookAdapter extends RecyclerView.Adapter<BookHolder>
    {
        private List<Book> mBooks;
        public BookAdapter(List<Book> books)
        {
            mBooks = books;
        }

        @NonNull
        @Override
        public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_book,parent,false);
            return new BookHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            Book book = mBooks.get(position);
            //holder.mTitleTextView.setText(book.getTitle());
            holder.bindBook(book);
        }

        @Override
        public int getItemCount() {
            return mBooks.size();
        }
    }

    private void updateUI()
    {
        BookLab bookLab = BookLab.get(getActivity());
        List<Book> books = bookLab.getBooks();
        mAdapter = new BookAdapter(books);
        mBookRecyclerView.setAdapter(mAdapter);
    }

}

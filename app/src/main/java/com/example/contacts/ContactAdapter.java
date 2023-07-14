package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Itemview>{
    private final ArrayList<Contact> contactList;

    ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public Itemview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact,parent,false);
        return new Itemview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Itemview holder, int position) {

        Contact contact = contactList.get(position);
        holder.tvContactName.setText(contact.getContactName());
        holder.tvContactNumber.setText(contact.getContactNumber());

    }

    @Override
    public int getItemCount() {
       return contactList.size();
    }

    public static class Itemview extends RecyclerView.ViewHolder {
        private final CardView cvContact;
        private final TextView tvContactName;
        private final TextView tvContactNumber;

        public Itemview(@NonNull View itemView) {
            super(itemView);
            cvContact = itemView.findViewById(R.id.contactcard);
            tvContactName = itemView.findViewById(R.id.contactname);
            tvContactNumber = itemView.findViewById(R.id.contactnumber);

            cvContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

}

package com.example.contacts;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Itemview> {

    private final ArrayList<Contact> contactList;
    private static final int REQUEST_CALL_PERMISSION = 1;
    private Context context;

    ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public Itemview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact, parent, false);
        context = parent.getContext();
        return new Itemview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Itemview holder, int position) {

        Contact contact = contactList.get(position);
        holder.tvContactName.setText(contact.getContactName());
        holder.tvContactNumber.setText(contact.getContactNumber());
        holder.cvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(contact.getContactNumber());
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class Itemview extends RecyclerView.ViewHolder {
        public final CardView cvContact;
        public final TextView tvContactName;
        public final TextView tvContactNumber;

        public Itemview(@NonNull View itemView) {
            super(itemView);
            cvContact = itemView.findViewById(R.id.contactcard);
            tvContactName = itemView.findViewById(R.id.contactname);
            tvContactNumber = itemView.findViewById(R.id.contactnumber);

        }
    }

    private void call(String contact) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            // Permission is already granted, make the call
            makeCall(contact);
        }

    }

    public void makeCall(String contact) {
        Log.e("number", contact);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" +contact));
        context.startActivity(callIntent);
    }

}


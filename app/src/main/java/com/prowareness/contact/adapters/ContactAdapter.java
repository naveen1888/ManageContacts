package com.prowareness.contact.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prowareness.contact.R;
import com.prowareness.contact.bean.Result;
import com.prowareness.contact.interfaces.Callbacks;

import java.util.ArrayList;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Result> list;
    private Callbacks callbacks;

    public ContactAdapter(Context context, ArrayList<Result> list, Callbacks callbacks) {
        this.context = context;
        this.list = list;
        this.callbacks = callbacks;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = layoutInflater.inflate(R.layout.view_contact, parent, false);
        return new ViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(final ContactAdapter.ViewHolder holder, int position) {

        final Result result = list.get(position);
        holder.name.setText(result.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private Button delete;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            delete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}

package com.example.copyproject;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<Eventdetails> profiles;
    ArrayList<Eventdetails>exampleListFull;


    public MyAdapter(Context c , ArrayList<Eventdetails> p)
    {
        context = c;
        profiles = p;
        exampleListFull=new ArrayList<>(profiles);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(""+profiles.get(position).getEventname());
        holder.date.setText(""+profiles.get(position).getEventdate());
      //  holder.location.setText("Event Location : " +profiles.get(position).getEventlocation());
       // holder.time.setText("Event Time : "+profiles.get(position).getEventtime());
        Picasso.with(context).load(profiles.get(position).getmImageUrl()).resize(300,400).centerCrop().into(holder.image);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, eventpage_activity.class);
               // intent.putExtra("eventid", profiles.get(position).getEventid());
                intent.putExtra("Name", profiles.get(position).getEventname());
                intent.putExtra("Date", profiles.get(position).getEventdate());
                intent.putExtra("Location", profiles.get(position).getEventlocation());
                intent.putExtra("time", profiles.get(position).getEventtime());
                intent.putExtra("url", profiles.get(position).getmImageUrl());

                context.startActivity(intent);
            }
        });
        //  Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);
       // if(profiles.get(position).getPermission()) {
            //holder.btn.setVisibility(View.VISIBLE);
          //  holder.onClick(position);
       // }
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,date;
        ImageView image;
        CardView card;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            //location = itemView.findViewById(R.id.location);
           // time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.card_image);
            card = itemView.findViewById(R.id.card);
        }

    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Eventdetails> filteredList = new ArrayList<>();

            if(constraint==null||constraint.length()==0)
            {
                filteredList.addAll(exampleListFull);
            }
            else
            {
                String filterpattern =constraint.toString().toLowerCase().trim();
                for(Eventdetails item :exampleListFull)
                {
                    if(item.getEventname().toLowerCase().contains(filterpattern))
                    {
                        filteredList.add(item);
                    }
                }

            }
            FilterResults results =new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            profiles.clear();
            profiles.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };
}
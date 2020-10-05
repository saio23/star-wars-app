package com.sioukas.starwarsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sioukas.starwarsapp.Activities.CharacterDetailsActivity;
import com.sioukas.starwarsapp.Model.SWPeopleDetailModel;
import com.sioukas.starwarsapp.R;

import java.util.List;

public class PeopleListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<PeopleListAdapter.PeopleListAdapterViewHolder> {
    public List<SWPeopleDetailModel> peopleList;
    Context mContext;


    public PeopleListAdapter(Context context, List<SWPeopleDetailModel> peopleList) {
        this.peopleList = peopleList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PeopleListAdapter.PeopleListAdapterViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
//        Inflate the view for each item to correspond to recycler_item_people layout
        View view = android.view.LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_people, parent, false);
        return new PeopleListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleListAdapter.PeopleListAdapterViewHolder holder, int position) {
//        set the name of the character as retrieved from the data API
        holder.tvName.setText(peopleList.get(position).getName());
//        Set on click listener for all the items
        holder.itemView.setOnClickListener(v -> {
//            Start the CharacterDetailsActivity and pass an extra data the corresponding character at that location.
            Intent showDetailsIntent = new Intent(mContext, CharacterDetailsActivity.class);
            showDetailsIntent.putExtra("CHARACTER", peopleList.get(position));
            mContext.startActivity(showDetailsIntent);
        });
    }

    @Override
    public int getItemCount() {
//        returns the size of the list, how many items contains in the adapter.
        return peopleList.size();
    }

    public class PeopleListAdapterViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private TextView tvName;


        public PeopleListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
//            Initialize ViewHolder item with the layout Text View from the recycler_item_people layout
            tvName = itemView.findViewById(R.id.tvPeopleName);
        }

    }


}
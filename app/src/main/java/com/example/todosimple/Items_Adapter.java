package com.example.todosimple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Items_Adapter extends RecyclerView.Adapter<Items_Adapter.ViewHolder>{

    public interface Onlongclicklistener{
        void onItemLongClicked(int position);
    }

    List<String> items_myapp;
    Onlongclicklistener longclicklistener;

    public Items_Adapter(List<String> items_myapp, Onlongclicklistener longclicklistener) {
        this.items_myapp = items_myapp;
        this.longclicklistener = longclicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items_myapp.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return items_myapp.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tv_item.setText(item);
            tv_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view){
                    longclicklistener.onItemLongClicked(getAdapterPosition());
                    return true;

                }
            });
        }
    }
}

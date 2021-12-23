package id.iyas.timi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.MyViewHolder> {

    private ArrayList<List> items;
    private Context context;
    private DBHelper dbHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtDescription;
        private ImageView menu;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title);
            txtDescription = itemView.findViewById(R.id.description);
            menu = itemView.findViewById(R.id.menu);
        }
    }

    public AdapterList(ArrayList<List> items, Context context) {
        this.items = items;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @Override
    public AdapterList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterList.MyViewHolder holder, final int position) {
        List obj = items.get(position);
        holder.txtTitle.setText(obj.getTitle());
        holder.txtDescription.setText(obj.getDescription());

        final String title = obj.getTitle();
        final String description = obj.getDescription();

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.menu);
                popup.inflate(R.menu.menu_rv);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                Intent i_input = new Intent(context, InputActivity.class);
                                i_input.putExtra("MODE", "EDIT");
                                i_input.putExtra("TITLE", title);
                                i_input.putExtra("DESC", description);
                                context.startActivity(i_input);
                                break;
                            case R.id.menu_hapus:
                                dbHelper.dml("delete from list where title = '" + title + "';");
                                items.remove(position);
                                notifyDataSetChanged();

                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

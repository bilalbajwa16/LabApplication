package lab.pak.com.glacialappportal.UsersData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lab.pak.com.glacialappportal.Logs.log;
import lab.pak.com.glacialappportal.R;


public class CustomAdapteruser extends RecyclerView.Adapter<CustomAdapteruser.MyViewHolder> {

    private final ArrayList<user> mainob;
   Context context;
    private MyViewHolder vh;

    public CustomAdapteruser(Context context, ArrayList<user> mainob) {
        this.context = context;
        this.mainob=mainob;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.log, parent, false);

        vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

    View vl;
try {
    holder.name.setText(mainob.get(position).getName());

}catch (Exception e){String ee= String.valueOf(e);
//Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
}
// implement setOnClickListener event on item view.

}





    @Override
    public int getItemCount() {
        return mainob.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



     //   private final TextView status;
        // init the item view's

    TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);

        }
    }
}
package me.firmannizammudin.workshopparsing.adapter;

import android.app.ListActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.firmannizammudin.workshopparsing.R;
import me.firmannizammudin.workshopparsing.model.GithubModel;

/**
 * Created by Firman on 03-Jun-16.
 */
public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {
    private List<GithubModel> githubList;

    public GithubAdapter(List<GithubModel> githubList) {
        this.githubList = githubList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubModel github = githubList.get(position);
        holder.txtId.setText(String.valueOf(github.getId()));
        holder.txtName.setText(github.getName());
        holder.txtFullname.setText(github.getFull_name());
    }

    @Override
    public int getItemCount() {
        return githubList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtFullname;

        public ViewHolder(View view) {
            super(view);
            txtId = (TextView) view.findViewById(R.id.main_txt_id);
            txtName = (TextView) view.findViewById(R.id.main_txt_name);
            txtFullname = (TextView) view.findViewById(R.id.main_txt_fullname);
        }
    }
}

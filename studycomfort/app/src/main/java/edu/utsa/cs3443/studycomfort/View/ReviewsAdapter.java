package edu.utsa.cs3443.studycomfort.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3443.studycomfort.Model.Review;
import edu.utsa.cs3443.studycomfort.R;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.VH> {

    private final List<Review> items;

    public ReviewsAdapter(List<Review> items) {
        this.items = items;
    }

    static class VH extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvAuthor, tvStars, tvText;
        VH(@NonNull View itemView) {
            super(itemView);
            tvTitle  = itemView.findViewById(R.id.rowTitle);
            tvAuthor = itemView.findViewById(R.id.rowAuthor);
            tvStars  = itemView.findViewById(R.id.rowStars);
            tvText   = itemView.findViewById(R.id.rowText);
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_review, parent, false);
        return new VH(row);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Review r = items.get(position);
        h.tvTitle.setText(r.getRoomTitle());
        h.tvAuthor.setText(r.getAuthor());
        h.tvStars.setText(r.getStars() + " Stars");
        h.tvText.setText("\"" + r.getText() + "\"");
    }

    @Override public int getItemCount() { return items.size(); }
}

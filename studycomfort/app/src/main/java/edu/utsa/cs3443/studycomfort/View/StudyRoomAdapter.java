package edu.utsa.cs3443.studycomfort.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3443.studycomfort.Model.StudyRoom;
import edu.utsa.cs3443.studycomfort.R;

public class StudyRoomAdapter extends RecyclerView.Adapter<StudyRoomAdapter.Holder> {

    private final List<StudyRoom> items;

    public StudyRoomAdapter(List<StudyRoom> items) {
        this.items = items;
    }

    @NonNull
    @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_room, parent, false);
        return new Holder(v);
    }

    @Override public void onBindViewHolder(@NonNull Holder h, int position) {
        StudyRoom r = items.get(position);

        // Title = room name ")
        h.tvTitle.setText(r.getName());

        StringBuilder sb = new StringBuilder();
        sb.append(r.getBuilding()).append('\n');
        sb.append("Seating Capacity: ").append(r.getSeatingCapacity()).append('\n');
        sb.append("Avg Noise Level: low").append('\n'); // no noise data in model; label only
        sb.append("WiFi: yes");                         // no wifi data in model; label only
        h.tvDetails.setText(sb.toString());

        // Try a photo ; otherwise fallback
        int resId = resolveDrawableId(h.itemView.getContext(), r.getPhotoKey());
        if (resId == 0) resId = android.R.drawable.ic_menu_gallery;
        h.ivPhoto.setImageResource(resId);
    }

    @Override public int getItemCount() { return items == null ? 0 : items.size(); }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView ivPhoto; TextView tvTitle; TextView tvDetails;
        Holder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }

    private int resolveDrawableId(Context ctx, String key) {
        if (key == null || key.trim().isEmpty()) return 0;
        String k1 = key.trim();
        String k2 = k1.toLowerCase();
        String k3 = k2.replace("-", "_");
        int id = ctx.getResources().getIdentifier(k1, "drawable", ctx.getPackageName());
        if (id == 0) id = ctx.getResources().getIdentifier(k2, "drawable", ctx.getPackageName());
        if (id == 0) id = ctx.getResources().getIdentifier(k3, "drawable", ctx.getPackageName());
        return id;
    }
}


package com.test.santiago.condorlabs.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.santiago.condorlabs.R;
import com.test.santiago.condorlabs.model.Album;

import java.util.List;

/**
 * Created by Santiago on 10/26/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {

    public Context mContext;
    private List<Album> albums;

    public AlbumAdapter(Context mContext, List<Album> albums) {
        this.mContext = mContext;
        this.albums = albums;
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_album, parent, false);
        AlbumHolder albumHolder = new AlbumHolder(view);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        holder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {

        private TextView tvAlbumName;
        private ImageView ivAlbumImage;
        private LinearLayout lyAvailableCountries;

        public AlbumHolder(View itemView) {
            super(itemView);

            tvAlbumName = itemView.findViewById(R.id.tvAlbumName);
            ivAlbumImage = itemView.findViewById(R.id.ivAlbumImage);
            lyAvailableCountries = itemView.findViewById(R.id.lyAvailableCountries);
        }

        public void bind(final Album album) {
            Glide.with(mContext)
                    .load(album.getImages().get(0).getUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.load)
                    .into(ivAlbumImage);
            tvAlbumName.setText(album.getName());
            availableCountries(album);
        }

        private void availableCountries(Album album) {
            if (album.getAvailableCountries().size() < 5) {
                for (String country : album.getAvailableCountries()) {
                    TextView textView = new TextView(mContext);
                    textView.setText(country);
                    textView.setTextColor(mContext.getResources().getColor(R.color.gray));
                    lyAvailableCountries.addView(textView);
                }
            }

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(album.getExternalUrls().getUrl()));
                Intent chooserIntent = Intent.createChooser(intent, mContext.getString(R.string.select_app_open));
                mContext.startActivity(chooserIntent);
            });
        }
    }
}

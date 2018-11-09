package br.com.bitocean.cardview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.bitocean.cardview.R;
import br.com.bitocean.cardview.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder> {
   private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.postagem_card,viewGroup,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        if(posts != null && posts.size() >0){
            Post post = posts.get(position);
            myHolder.tvNome.setText(post.getUsuario());
            myHolder.tvText.setText(post.getText());
            myHolder.ivImage.setImageResource(post.getImagem());
            myHolder.btGostei.setTag(post);
            myHolder.btComentar.setTag(post);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvNome,tvText;
        private ImageView ivImage;
        private Button btGostei,btComentar;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = (TextView)itemView.findViewById(R.id.tvNome);
            tvText = (TextView)itemView.findViewById(R.id.tvText);
            ivImage = (ImageView)itemView.findViewById(R.id.ivImage);
            btComentar = (Button)itemView.findViewById(R.id.btComentar);
            btGostei = (Button)itemView.findViewById(R.id.btGostei);

            btComentar.setOnClickListener(this);
            btGostei.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Post post = (Post) view.getTag();
            switch (view.getId()){
                case R.id.btComentar:
                    Toast.makeText(view.getContext(),"Comentar "+post.getUsuario(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btGostei:
                    Toast.makeText(view.getContext(),"Gostei "+post.getUsuario(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}

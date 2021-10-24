package com.example.mockchalladmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.Viewholder> {

    private List<QuestionModel> list;
    private DeleteListener listener;

    public QuestionAdapter(List<QuestionModel> list, DeleteListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        String question = list.get(position).getQuestion();
        String answer = list.get(position).getAnswer();

        holder.setData(question, answer, position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends  RecyclerView.ViewHolder{

        private TextView question , answer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            question= itemView.findViewById(R.id.question);
            answer= itemView.findViewById(R.id.answer);
        }
        private void setData(String question, String answer, final int position){
            this.question.setText(position+1+". "+question);
            this.answer.setText("Ans. "+answer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editIntent = new Intent(itemView.getContext(), AddQuestionActivity.class);
                    editIntent.putExtra("position", position);
                    itemView.getContext().startActivity(editIntent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLondClick(position, list.get(position).getId());

                    return false;
                }
            });

        }
    }

    public interface DeleteListener{
        void onLondClick(int position, String id);
    }
}
